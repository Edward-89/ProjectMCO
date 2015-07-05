package com.diplom.project.panels;

import java.awt.*;

import javax.swing.*;

import java.util.*;
import java.awt.event.*;

import com.diplom.project.table.model.MyTableModel;

public class PersentPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private static ArrayList<JTextField> textFilds = new ArrayList<JTextField>();
	private double[] value;

	/**
	 * Create the panel.
	 */
	public PersentPanel(JTable table) {
		
		this.value = new double[table.getColumnCount()-1];
		
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
	
	// *** метод размещающий компоненты на панеле ***
	private void createComponentsOfPanel(JTable table){
		
		MyTableModel model = (MyTableModel) table.getModel();
		String[] str = model.getColumn(); // получение 
		int len = str.length-1;
		final JLabel label = new JLabel("Persent method %");
		addNewComponent(label, 2, 0);
		for(int row = 1; row <= len; row++){
			final int index = row-1;
			addNewComponent(new JLabel(str[row]), 1, row+1);
			final JTextField textField = new JTextField(6);
			addNewComponent(textField, 2, row + 1);
			addKeyFilter(textField, index);
			textFilds.add(textField);
			textField.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
//						setValue(index, Double.valueOf(textField.getText()));
						System.out.println(Arrays.toString(value));
				}
			});
		}
		
	}
	 //****** фильтр ввода чисел и точки ********
	 private String digitsOnlyFilter (String value) {
	       int len = value.length();
	       String result = "";
	       boolean oneget = false;

	        for(int i = 0; i < len; i++) {
	            char ch = value.charAt(i);

	            if(Character.isDigit(ch)) {
	                result += value.substring(i, i+1);
	            } else if ((ch == '.') && !oneget) {
	                oneget = true;
	                result += value.substring(i, i+1);
	            }
	        }
	       return result;
	    }
	 
	 // ***** обработчик нажатия кнопок при вводе в текстовое поле ******
	 private void addKeyFilter(final JTextField field, final int index) {
	        field.addKeyListener(new KeyListener() {
	            public void keyTyped(KeyEvent e) {}
	            public void keyPressed(KeyEvent e) {}
	            public void keyReleased(KeyEvent e) {
	                field.setText(digitsOnlyFilter(field.getText()));
	                //блок проверки, исключающий ошибку при пустом поле ввода. 
	                // По умолчанию пустое поле равно 0
	                try{
	                	setValue(index, Double.valueOf(field.getText()));
	                	if(Double.valueOf(field.getText()) > 1.0){
		                	setValue(index, 1.0);
		                } else	if(getSumValue() > 1.0) 
		                			field.setText(String.valueOf(1.0 - (getSumValue() - Double.valueOf(field.getText()))));
	                	
	                } catch(NumberFormatException exc){
	                	setValue(index, 0d);
	                }
	                
	                
					System.out.println(Arrays.toString(value));
	            }
	        });
	    }
	 
	 private double getSumValue(){
		 double sum = 0.0;
		 for(double val : value){
			 sum += val;
		 }
		 return sum;
	 }
	 
	
	 // *** добавление нового компонента на панель в заданную ячейку сетки разбиения ****
	 private void addNewComponent(JComponent component, int x, int y){
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = x;
		gbc.gridy = y;
		add(component, gbc);
	}
	
	 // *** установка значения элемента массива value *****
	 private void setValue(int index, double value){
	    	this.value[index] = value;
	 }
	
	
	 // *** установка активности/неактивности  полей ввода ***
	 public void setEnabledTextFields(boolean bool){
		for(JTextField jtf : textFilds)
			jtf.setEnabled(bool);
	 }
	
	 //*** преобразование значений введенных данных в приоритеты ***
	 private int[] persentToPriority(double[] value){
		double[] sortValue = Arrays.copyOf(value, value.length);
		Arrays.sort(sortValue);
		int[] priority = new int[sortValue.length];
		for(int index = sortValue.length - 1; index >= 0; index--){
			int step = 0;
			double item = sortValue[index];
			for(double v : value){
				if( v == item){
					priority[step] = sortValue.length - index; break;
				} else step++;
			}
		}
		return priority;
	 }
	
	 // *** открытый доступ к получению массива priority ***
	 public int[] getPriority(){
		return persentToPriority(value);
	 }
	

}
