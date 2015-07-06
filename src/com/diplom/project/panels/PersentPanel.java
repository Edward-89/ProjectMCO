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
		String[] str = model.getColumn(); // ïîëó÷åíèå 
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
		}
		
	}
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
	 
	 private void addKeyFilter(final JTextField field, final int index) {
	        field.addKeyListener(new KeyListener() {
	            public void keyTyped(KeyEvent e) {}
	            public void keyPressed(KeyEvent e) {}
	            public void keyReleased(KeyEvent e) {
	                field.setText(digitsOnlyFilter(field.getText()));
	                try{
	                	setValue(index, Double.valueOf(field.getText()));
	                	if(Double.valueOf(field.getText()) > 1.0){
		                	setValue(index, 1.0);
		                } else	if(getSumValue() > 1.0) 
		                			field.setText(String.valueOf(1.0 - (getSumValue() - Double.valueOf(field.getText()))));
	                	
	                } catch(NumberFormatException exc){
	                	setValue(index, 0d);
	                }
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
	 
	
	 private void addNewComponent(JComponent component, int x, int y){
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = x;
		gbc.gridy = y;
		add(component, gbc);
	}
	
	 private void setValue(int index, double value){
	    	this.value[index] = value;
	 }
	
	
	 public void setEnabledTextFields(boolean bool){
		for(JTextField jtf : textFilds)
			jtf.setEnabled(bool);
	 }
	
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
	
	 public int[] getPriority(){
		return persentToPriority(value);
	 }
	

}
