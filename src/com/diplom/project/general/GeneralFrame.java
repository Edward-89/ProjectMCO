package com.diplom.project.general;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import java.util.*;

import com.diplom.project.addenum.ActivPanel;
import com.diplom.project.panels.ControlPanel;
import com.diplom.project.panels.HelpDialog;
import com.diplom.project.panels.PersentPanel;
import com.diplom.project.panels.PriorityPanel;
import com.diplom.project.serialization.DataTableSaver;
import com.diplom.project.serialization.StaticSaveAndOpenMethods;
import com.diplom.project.sort.SortedMan;
import com.diplom.project.table.model.*;


public class GeneralFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * +------------------------------------+
	 * |  general elements of GeneralFrame  |
	 * +------------------------------------+
	 */

	// ***** Panels *****
	private JPanel contentPane = null;
	private ControlPanel panel = null;
	private PriorityPanel priorityPanel = null;
	private PersentPanel persentPanel = null;
	private JPanel evaluatePanel = null;
	private JPanel northPanel = null;
	private JPanel flowRight = null;
	private JPanel resultPanel = null;

	// ***** ScrollPane *****
	private JScrollPane scrollPane;

	// ***** Slider *****
	private JSlider slider;

	// ***** Table & TableModel *****
	private JTable table;
	private MyTableModel model;

	// ***** Buttons *****
	private JButton btnRun;
	private JButton btnLoad;
	private JButton btnReturn;
	private JButton btnPriority;
	private JButton btnCreateTable;
	private JButton btnOpen;
	private JButton btnReset;
	private JButton btnPersent;
	private JButton btnLoadEP;
	private JButton btnRunEP;
	private JButton btnReturnEP;
	private JButton btnNormalize;

	// ***** Labels *****
	private JLabel resultLabel = new JLabel("This is result!");
	private JLabel variants = new JLabel("Variants");
	private JLabel controlButtons = new JLabel("Control Buttons");

	// ***** TextField *****
	private JTextField textField;

	// ***** Items menu File *****
	private JMenuItem mntmOpen;
	private JMenuItem mntmSave;
	private JMenuItem mntmExit;
	
	// *****  indicator adding methods panel *****
	private ActivPanel activPanel = ActivPanel.NOT_ACTIVE;
	
	// ***** special collections for data of table *****
	private static ArrayList<Double[]> firstData; 	 
	private static ArrayList<Double[]> evaluateData; 

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				JFrame frame = new GeneralFrame();  
				frame.setVisible(true);			
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Constructor 
	 */
	public GeneralFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,(Toolkit.getDefaultToolkit().getScreenSize().width * 4/7 + 100),
							Toolkit.getDefaultToolkit().getScreenSize().height * 2/3+ 100);
		setLocationRelativeTo(null); 
		setTitle("Multiple Criteria Optimization"); 
		setResizable(true); 
		
		//******************************************************************************
		
		/**
		 * +-------------------+
		 * | Creation MenuBar  |
		 * +-------------------+
		 */
		JMenuBar menuBar = new JMenuBar();
		
		/**
		 * File
		 */
		JMenu mnFile = new JMenu("File");
		mnFile.setIcon(new ImageIcon(GeneralFrame.class
				.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		menuBar.add(mnFile);
		
			/**
			 * File
			 * 	  |
			 * 	  Open
			 */
			mntmOpen = new JMenuItem("Open");
			mntmOpen.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if (mntmOpen.isEnabled()) {
						DataTableSaver dat = new StaticSaveAndOpenMethods().openMethods();
						if(dat != null){
							model = new MyTableModel(dat.getData(), dat.getColName());
							refreshToNewTable(model);
							setEnableFalse(btnCreateTable, btnOpen, slider, textField, btnOpen);
							setEnableTrue(btnReset, btnLoad);

						} else if(StaticSaveAndOpenMethods.setCancel == true){
								StaticSaveAndOpenMethods.setCancel = false;
							} else 
							JOptionPane.showMessageDialog( GeneralFrame.this,
							    "This file not model",
							    "error",
							    JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
			mntmOpen.setIcon(new ImageIcon(GeneralFrame.class
				.getResource("/com/sun/java/swing/plaf/windows/icons/TreeClosed.gif")));
			mnFile.add(mntmOpen);
		
			/**
			 * File
			 * 	  |
			 * 	  Save	
			 */
			mntmSave = new JMenuItem("Save");
			mntmSave.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					new StaticSaveAndOpenMethods().saveMethods(model);
				}
			});
			mntmSave.setIcon(new ImageIcon(GeneralFrame.class
				.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
			mnFile.add(mntmSave);
		
			/**
			 * File
			 * 	  |
			 * 	  Exit
			 */
			mntmExit = new JMenuItem("Exit");
			mntmExit.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					System.exit(EXIT_ON_CLOSE);
				}
			});
			mntmExit.setIcon(new ImageIcon(GeneralFrame.class
				.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
			mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		mnHelp.setIcon(new ImageIcon(GeneralFrame.class
				.getResource("/com/sun/java/swing/plaf/motif/icons/Question.gif")));
		menuBar.add(mnHelp);
		
		/**
		 * Help
		 * 	  |
		 * 	  HELP
		 */
		
		JMenuItem mntmHelp = new JMenuItem("HELP");
		mntmHelp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HelpDialog help = new HelpDialog();
				help.setLocationRelativeTo(null);
				help.setVisible(true);
				
			}
		});
		mnHelp.add(mntmHelp);
		
		/**
		 * Help
		 * 	  |
		 * 	  About program
		 */
		JMenuItem mntmAbout = new JMenuItem("About MCO");
		mntmAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final String message = "Multiple Criteria Optimization (MCO) v1.15\n\nAutor: Voronkov Eduard & Tsiluiko Elena";
				JOptionPane.showMessageDialog(null, message, "About MCO", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnHelp.add(mntmAbout);

		setJMenuBar(menuBar);
		
		//*******************
		//*	Creation Panels	*
		//*******************

		
		northPanel = new JPanel(); 
		northPanel.setLayout(new FlowLayout());
		northPanel.setBorder(new TitledBorder(new LineBorder(Color.GREEN.darker()), "Control Panel", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.BLACK));

		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 255)); 
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0)); 
		contentPane.add(northPanel, BorderLayout.NORTH);


		panel = new ControlPanel(); 
		northPanel.add(panel); 
				
		flowRight = new JPanel();
		flowRight.setLayout(panel.getLayout()); 
		northPanel.add(flowRight); 

		slider = new JSlider(0, 100, 1); 
		addComponentToControlPanel(slider, 12, 1); 
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setPaintTrack(true);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(10);
		slider.setForeground(Color.blue);
		
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(slider.getValue() == 0) slider.setValue(1); 
				textField.setText(String.valueOf(slider.getValue()));
			}
		});

		textField = new JTextField("1");
		addComponentToControlPanel(textField, 18, 1); 
		textField.setColumns(4); 
		
		textField.addActionListener(new ActionListener() {
			String defaultInt = "1";
			@Override
			public void actionPerformed(ActionEvent e) {
				//Setting in the range from 1 to 100
				try {
					int value = Integer.valueOf(textField.getText());
					slider.setValue(value);
				} catch (NumberFormatException exeption) {
					textField.setText(defaultInt);
				}
			}
		});
					
		/**
		 * +---------------------------+
		 * | Buttons control processes |
		 * +---------------------------+
		 */

		// ******* add Button "Load" to flowRight panel ********
		btnLoad = new JButton("Load"); 
		addComponentToControlPanel(btnLoad, 12, 4); 
		btnLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setEnableFalse(btnLoad); 
				setEnableTrue(btnNormalize); 
				panel.setEnableQualityIndices(false); 
			}
		});

		// ******* add Button "Create table" *******
		btnCreateTable = new JButton("Create table");
		addComponentToControlPanel(btnCreateTable, 12, 2);
		btnCreateTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model = new MyTableModel(); 
				model.testDataZero(Integer.valueOf(textField.getText())); 
				refreshToNewTable(model); 
				setEnableFalse(btnCreateTable, btnOpen, slider, textField, btnOpen);
				setEnableTrue(btnReset, btnLoad); 

			}
		});

		// ******* add button "RESET" *****
		btnReset = new JButton("Reset");
		addComponentToControlPanel(btnReset, 15, 5);
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the table?", null, JOptionPane.YES_NO_OPTION);
				//if "OK" then reset table
				if (reply == JOptionPane.YES_OPTION){
					model.testDataZero(0); 
					refreshToNewTable(model);
					setEnableTrue(btnCreateTable, btnOpen, slider, textField); 
					setEnableFalse(btnLoad,  btnReturn,  btnRun,
								   btnReset, btnPersent, btnPriority, btnNormalize); 
					panel.setEnableQualityIndices(true); 
					getResultInfo(false); 
					
					if(priorityPanel != null)
						priorityPanel.setVisible(false);
					if(evaluatePanel != null)
						evaluatePanel.setVisible(false);
					if(persentPanel != null)
						persentPanel.setVisible(false);
				}
			}
		});

		// ******* add Button "Open" to flowRight panel *****
		btnOpen = new JButton("Open");
		addComponentToControlPanel(btnOpen, 14, 2);
		//For press this button opening   dialog "Open File"  
		btnOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// deserialization saved TableModel
				DataTableSaver dat = new StaticSaveAndOpenMethods().openMethods();
				if(dat != null){
					model = new MyTableModel(dat.getData(), dat.getColName());
					refreshToNewTable(model);
					setEnableFalse(btnCreateTable, btnOpen, slider, textField, btnOpen);
					setEnableTrue(btnReset, btnLoad);

				} else if(StaticSaveAndOpenMethods.setCancel == true){
						StaticSaveAndOpenMethods.setCancel = false;
					} else 
					JOptionPane.showMessageDialog( GeneralFrame.this,
					    "This file not model",
					    "error",
					    JOptionPane.ERROR_MESSAGE);
				
			}
		});

		// ******* add Button "Run" to flowRight panel *****
		btnRun = new JButton("RUN");
		addComponentToControlPanel(btnRun, 15, 4);
		btnRun.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setEnableTrue(btnReturn);
				setEnableFalse(btnRun);
				boolean[] enabled = ((ControlPanel) panel).getEnable(); 
				Double[][] data = model.getEvaluateData(enabled); 
				data = SortedMan.sortedOptimal(data); 
				String[] colName = model.getEnabledColumn(enabled); 
				refreshToNewTable(new MyTableModel(data, colName));
				if(table.getRowCount() > 1){
					setEnableTrue(btnPersent, btnPriority);
				}
				else {
					getResultInfo(true);
				}
			}
		});
		
		
		// ***** add button Normalize to flowRight panel ********
		btnNormalize = new JButton("Normalize");
		addComponentToControlPanel(btnNormalize, 14, 4); 
		btnNormalize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				firstData = model.getData(); 
				evaluateData = cloneArray(firstData);
				setEnableTrue(btnRun); 
				setEnableFalse(btnNormalize); 
				setEnableTrue(btnReturn); 
				model.getMaxOperation(panel); 
				boolean[] enabled = ((ControlPanel) panel).getEnable(); 
				Double[][] data = model.getEvaluateData(enabled);
				String[] colName = model.getEnabledColumn(enabled);
				refreshToNewTable(new MyTableModel(data, colName));
				
			}
		});


		// ***** add Button "Return" to flowRight panel *****
		btnReturn = new JButton("Return");
		addComponentToControlPanel(btnReturn, 16, 4);
		btnReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setEnableTrue(btnLoad); 
				setEnableFalse(btnReturn, btnPriority, btnPersent); 
				setEnableFalse(btnRun, btnNormalize); 
				panel.setEnableQualityIndices(true); 
				model.setData(evaluateData); 
				refreshToNewTable(model);
				getResultInfo(false); 
				if (priorityPanel != null)
					priorityPanel.setVisible(false);
				if(evaluatePanel != null)
					evaluatePanel.setVisible(false);
				if(persentPanel != null)
					persentPanel.setVisible(false);
				SwingUtilities.updateComponentTreeUI(contentPane);
			}
		});

		// ***** add Button "Priority" to Control Panel *******
		btnPriority = new JButton("Priority");
		addComponentToControlPanel(btnPriority, 12, 5);
		btnPriority.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setEnableFalse(btnPersent, btnPriority); 
				activPanel = ActivPanel.PRIORITY_PANEL;
				priorityPanel = new PriorityPanel(table); 
				createEvaluatePanel(); 
				contentPane.add(priorityPanel, BorderLayout.WEST); 
				SwingUtilities.updateComponentTreeUI(contentPane); 
				
			}
		});
		
		// ***** add Button "Persent%" to Control Panel *******
		btnPersent = new JButton("Persent %");
		addComponentToControlPanel(btnPersent, 14, 5);
		btnPersent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setEnableFalse(btnPersent, btnPriority);
				activPanel = ActivPanel.PERSENT_PANEL;
				persentPanel = new PersentPanel(table);
				createEvaluatePanel();
				contentPane.add(persentPanel, BorderLayout.WEST);
				SwingUtilities.updateComponentTreeUI(contentPane);
			}
		});

		// ***** create and add scrollPane to ContentPane *****
		scrollPane = new JScrollPane(); 
		contentPane.add(scrollPane, BorderLayout.CENTER);

		// ***** first position ******
		setEnableFalse(btnLoad, btnReturn, btnRun, btnReset, btnPersent, btnPriority, btnNormalize);
		
		addComponentToControlPanel(new JLabel(" or "), 13, 2);
		addComponentToControlPanel(new JLabel(" or "), 13, 5);
		addComponentToControlPanel(variants, 12, 0);
		addComponentToControlPanel(controlButtons, 12, 3);

	} // end constructor
	
//===============================================================================================	
	
	/**
	 * +----------------------------------------+
	 * | Methods for :							|
	 * |	activation | deactivation elements; |
	 * |	deployment of elements;				|
	 * |	operation with data of table.		|
	 * +----------------------------------------+
	 */
	
	
	// **** method activation/deactivation label "This is result" ****
		private  void getResultInfo(boolean flag){
			
			if(resultPanel == null){
				resultPanel = new JPanel();
				resultPanel.setBackground(Color.green);
				resultLabel.setFont(new Font("Arial", Font.ITALIC, 25));
				resultPanel.add(resultLabel);
				contentPane.add(resultPanel, BorderLayout.SOUTH);
			}
			resultPanel.setVisible(flag);
			SwingUtilities.updateComponentTreeUI(contentPane);
			
			
		}
	

	// ******** return copy argument of method ********
		
	private static ArrayList<Double[]> cloneArray(Collection<?> collection) {
		ArrayList<Double[]> copy = new ArrayList<Double[]>();
		for (Object x : collection) {
			Double[] s = (Double[]) x;
			Double[] z = new Double[s.length];
			for (int i = 0; i < z.length; i++) {
				z[i] = s[i];
			}
			copy.add(z);
		}
		return copy;
	}

	// ******** return evaluate data of Table ********
	public static ArrayList<Double[]> getEvaluateData() {
		return firstData;
	}

	// *** additional Component to ControlPanel ***
	private void addComponentToControlPanel(JComponent component, int x, int y) {
		GridBagConstraints gbc_btnOpen = new GridBagConstraints();
		gbc_btnOpen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpen.insets = new Insets(0, 0, 5, 5);
		if (component.getClass().equals(JSlider.class) || component.equals(variants) || component.equals(controlButtons)){
			gbc_btnOpen.gridwidth = 6; 
			variants.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
			controlButtons.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
		}
		else if(component.getClass().equals(JButton.class))
				if(component.equals(btnReset)) component.setBackground(Color.red);
				else component.setBackground(Color.green.darker());
		gbc_btnOpen.gridx = x;
		gbc_btnOpen.gridy = y;  
		flowRight.add(component, gbc_btnOpen);
	}
	
	// to evaluatePanel
	private void addComponentToEvaluatePanel(JComponent component, int x, int y) {
		GridBagConstraints gbc_btnOpen = new GridBagConstraints();
		gbc_btnOpen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOpen.insets = new Insets(0, 0, 5, 5);
		gbc_btnOpen.gridx = x;
		gbc_btnOpen.gridy = y;  
		evaluatePanel.add(component, gbc_btnOpen);
	}

	// ************* methods true or false enabled components *********
	private static void setEnableTrue(Component... components) {
		for (Component component : components)
			component.setEnabled(true);
	}
	private static void setEnableFalse(Component... components) {
		for (Component component : components)
			component.setEnabled(false);
	}

	// ************* refresh table with new data ***************
	private void refreshToNewTable(MyTableModel model) {
		
		scrollPane.setVisible(false);
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		SwingUtilities.updateComponentTreeUI(contentPane);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
	}
	
	
	/*
	 * This 3 methods for evaluate optimal criterion
	 */
	
	
	// ***** search element poition in array ******
	private int searchIndexArray(int[] array, int element){
		int step = 0;
		for(int s : array){
			if(s == element) return step;
			step++;
		}
		return -1;
	}
	
	// **** search number dataRow independence selected ****
	private double getNumberWinner(){
		int priority[] = new int[table.getColumnCount()-1];
		if(activPanel == ActivPanel.NOT_ACTIVE){
			return -1;
		} else {
				if(activPanel == ActivPanel.PRIORITY_PANEL){
					priority = priorityPanel.getPriority();
				} else  priority = persentPanel.getPriority();
		}				
		MyTableModel myModel = (MyTableModel)table.getModel();
		ArrayList<Double[]> list = myModel.getData();
		ArrayList<Double[]> delList = myModel.getData();
		Double[] min = list.get(0);
		for(int pr = 1; pr < priority.length; pr++){
			int index = searchIndexArray(priority, pr);
			list.removeAll(list); 
			list.addAll(delList); System.out.println(list.size()); 
			if(index == -1) continue;
			else {
				int size = list.size();
				if(size == 1) break;
				for(int i = 0; i < size; i++){
					Double[] x = list.get(i);
					if(min[index+1] > x[index+1]){
						min = x;
						for( int j = 0; j < i; j++)
							delList.remove(list.get(j));
					} else if(min[index+1] < x[index+1]) delList.remove(x);	
				} //end for
			} // end else
		} // end for
		
		
		return min[0];
		
	}
	
	//***** search number row independence #id
	private int getRowPositionInTable(double id){
		MyTableModel myModel = (MyTableModel) table.getModel();
		int rows = myModel.getRowCount();
		for(int i = 0; i < rows; i++){
			double idT = Double.valueOf(String.valueOf(myModel.getValueAt(i, 0)));
			if(Math.abs(idT - id) < 1) return i;
		}
		
		return -1; 
		
	}
	
	// ******** create Evaluate Panel **************
	private void createEvaluatePanel(){
		evaluatePanel = new JPanel();
		GridBagLayout gbc = new GridBagLayout();
		gbc.columnWidths  = new int[]{0, 0, 0, 0, 0,
									  0, 0, 0, 0, 0,
									  0, 0, 0, 0, 0,
									  0};
		gbc.rowHeights    = new int[]{0, 0, 0, 0, 0,
									  0, 0, 0, 0, 0,
									  0, 0, 0, 0};
		gbc.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0,
										 0.0, 0.0, 0.0, 0.0, 0.0,
										 0.0, 0.0, 0.0, 0.0, 0.0,
										 Double.MIN_VALUE};
		gbc.rowWeights    = new double[]{0.0, 0.0, 0.0, 0.0, 0.0,
										 0.0, 0.0, 0.0, 0.0, 0.0,
										 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
		evaluatePanel.setLayout(gbc);
		
		btnLoadEP = new JButton("Load");
		addComponentToEvaluatePanel(btnLoadEP, 1, 1);
		btnLoadEP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					setEnableTrue(btnRunEP, btnReturnEP);
					setEnableFalse(btnLoadEP);
					if(activPanel == ActivPanel.PRIORITY_PANEL)	priorityPanel.setEnabledRadioButtons(false);
					if(activPanel == ActivPanel.PERSENT_PANEL) 	persentPanel.setEnabledTextFields(false);
					
			}
		});
	
		btnRunEP = new JButton("Run");
		addComponentToEvaluatePanel(btnRunEP, 1, 2);
		btnRunEP.setEnabled(false);
		btnRunEP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double id = getNumberWinner(); System.out.println(id);
				final int rowIndex = getRowPositionInTable(id); System.out.println(rowIndex);
			
				for(int i = 0; i < table.getColumnCount(); i++){
					table.getColumnModel().getColumn(i).setCellRenderer(new TableCellRenderer() {
						
						@Override
						public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
								int row, int column) {
						
							Component cell = new DefaultTableCellRenderer().getTableCellRendererComponent(table, value, isSelected,
									hasFocus, row, column);
							if(row == rowIndex) cell.setBackground(Color.green);
							
							return cell;
						}
					});
					
				}
				setEnableFalse(btnRunEP);
				getResultInfo(true);
				SwingUtilities.updateComponentTreeUI(contentPane);
			}
		});
	
		btnReturnEP = new JButton("Return");
		addComponentToEvaluatePanel(btnReturnEP, 1, 3);
		btnReturnEP.setEnabled(false);
		btnReturnEP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setEnableTrue(btnLoadEP);
				setEnableFalse(btnRunEP, btnReturnEP);
				if(activPanel == ActivPanel.PRIORITY_PANEL)	priorityPanel.setEnabledRadioButtons(true);
				if(activPanel == ActivPanel.PERSENT_PANEL) 	persentPanel.setEnabledTextFields(true);
				MyTableModel model = (MyTableModel) table.getModel();
				scrollPane.setVisible(false);
				scrollPane = new JScrollPane(table = new JTable(model));
				getResultInfo(false);
				contentPane.add(scrollPane);
				SwingUtilities.updateComponentTreeUI(contentPane);
				
				
			}
		});
		contentPane.add(evaluatePanel, BorderLayout.EAST);
		SwingUtilities.updateComponentTreeUI(contentPane);
	}

}