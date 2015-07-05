package com.diplom.project.panels;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import com.diplom.project.table.model.MyTableModel;

public class PriorityPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private static ArrayList<ButtonGroup> buttonGroups = new ArrayList<ButtonGroup>();
	private static ArrayList<JRadioButton> radioButtons = new ArrayList<JRadioButton>();
	private int[] priority;

	/**
	 * Create the panel.
	 */
	public PriorityPanel(JTable table) {
		
		this.priority = new int[table.getColumnCount()-1];
		
		//разбиение панели на сетку
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths  = new int[]{0, 0, 0, 0, 0,
												0, 0, 0, 0, 0,
												0, 0, 0, 0, 0,
												0};
		gridBagLayout.rowHeights    = new int[]{0, 0, 0, 0, 0,
												0, 0, 0, 0, 0,
												0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0,
												   0.0, 0.0, 0.0, 0.0, 0.0,
												   0.0, 0.0, 0.0, 0.0, 0.0,
												   Double.MIN_VALUE};
		gridBagLayout.rowWeights    = new double[]{0.0, 0.0, 0.0, 0.0, 0.0,
												   0.0, 0.0, 0.0, 0.0, 0.0,
												   0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		
		createComponentsOfPanel(table);
		

	}
	
	
	private void createComponentsOfPanel(JTable table){
		
		MyTableModel model = (MyTableModel) table.getModel();
		String[] str = model.getColumn();
		int len = str.length-1;
		for(int col = 1; col <= len; col++){
			addNewComponent(new JLabel(str[col]), col+1, 1);
			addNewComponent(new JLabel(String.valueOf(col)), 1, col+1);
			createRadioButtons(col, len);
			
		}
	}
	
	private void createRadioButtons(final int col, int len) {
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		for(int row = 1; row <= len; row++){
			final int prior = row;
			final JRadioButton radio = new JRadioButton();
			addNewComponent(radio, col+1, row+1);
			buttonGroup.add(radio);
			buttonGroups.add(buttonGroup);
			radioButtons.add(radio);
			radio.setToolTipText(String.valueOf(row));
			radio.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(radio.isSelected()) setPriority(col-1, prior);
				}
			});
		}
			
	}
	
	private void addNewComponent(JComponent component, int x, int y){
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = x;
		gbc.gridy = y;
		add(component, gbc);
	}
	
	private void setPriority(int index, int prior){
		priority[index] = prior;
	}
	
	public int[] getPriority(){
		
		return priority;
		
	}
	
	public void setEnabledRadioButtons(boolean bool){
		for(JRadioButton radio : radioButtons)
			radio.setEnabled(bool);
	}
	

}
