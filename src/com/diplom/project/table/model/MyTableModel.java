package com.diplom.project.table.model;

import java.util.*;

import javax.swing.table.AbstractTableModel;

import com.diplom.project.addenum.Variant;
import com.diplom.project.panels.ControlPanel;

public class MyTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Double[]> dataModel;  
	private String[] colName;				
	
	
	public MyTableModel() {
		dataModel = new ArrayList<Double[]>();
		for (int i = 0; i < dataModel.size(); i++) {
			dataModel.add(new Double[getColumnCount()]);
		}
		colName = new String[] { "#id", "K1", "K2", "K3", "K4", "K5",
										"K6", "K7", "K8", "K9", "K10" };
	}

	public MyTableModel(Double[][] data, String[] colName) {
		dataModel = new ArrayList<Double[]>();
		for (Double[] s : data) {
			dataModel.add(s);
		}
		this.colName = colName;
	}
	
	/**
	 *  AbstractTableModel
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 0)
			return false;
		return true;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0)
			return Double.class;
		return Double.class;
	}

	@Override
	public int getRowCount() {
		return dataModel.size();
	}

	@Override
	public int getColumnCount() {
		return colName.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Double[] preData = dataModel.get(rowIndex);
		return preData[columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Double[] preData = dataModel.get(rowIndex);
		preData[columnIndex] = Double.valueOf(String.valueOf(aValue));
		dataModel.remove(rowIndex);
		dataModel.add(rowIndex, preData);
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colName[columnIndex];
	}
	
	public void addDataToLastRow(Double[] row) {
		Double[] rowTable = new Double[getColumnCount()];
		rowTable = row;
		dataModel.add(rowTable);
	}

	/**
	 * testing data for program
	 */
	
	public void testDataRandom(int rowCount) {
		dataModel.removeAll(dataModel);
		int id = 1;
		for (int i = 0; i < rowCount; i++) {
			Double[] doubly = new Double[getColumnCount()];
			for (int j = 0; j < getColumnCount(); j++) {
				if (j == 0)
					doubly[j] = (double) id++;
				else
					doubly[j] = Math.random();
			}
			addDataToLastRow(doubly);
		}
	}

	public void testDataZero(int rowCount) {
		dataModel.removeAll(dataModel);
		int id = 1;
		for (int i = 0; i < rowCount; i++) {
			Double[] doubly = new Double[getColumnCount()];
			for (int j = 0; j < getColumnCount(); j++) {
				if (j == 0)
					doubly[j] = (double) id++;
				else
					doubly[j] = 0d;
			}
			addDataToLastRow(doubly);
		}
	}
	
	/**
	 * methods for get data or convert dataTable to array
	 */
	
	public ArrayList<Double[]> getData() {
		ArrayList<Double[]> copy = new ArrayList<Double[]>();
		for (int i = 0; i < dataModel.size(); i++) {
			Double[] x = dataModel.get(i);
			copy.add(x);
		}

		return copy;
	}
	
		public Double[][] toDoubleData() {
			int rows = this.getRowCount();
			int cols = this.getColumnCount();
			Double[][] result = new Double[rows][cols];
			result = dataModel.toArray(result);
			
			return result;
		}

	
	public void setData(Collection<? extends Double[]> data) {
		dataModel.removeAll(dataModel);
		dataModel.addAll(data);
	}
	
	public void setColumnName(String[] str) {
		this.colName = str;
	}
	
	public String[] getColumn() {
		return colName;
	}

	
	/**
	 * Methods for operation with data of table
	 */
	
	public Double[] getMax() {
		Double[] max = new Double[this.getColumnCount() - 1];
		for (int j = 0; j < max.length; j++) {
			max[j] = (double) 0;
			for (int k = 0; k < this.getRowCount(); k++) {
				Double maximum = Double.valueOf(String.valueOf(this.getValueAt(k, j + 1)));
				if (max[j] < maximum)
					max[j] = maximum;
			}
		}
		return max;
	}
	
	public void getMaxOperation(ControlPanel panel) {
		Variant set[] = panel.getVariant();
		Double[] sum = this.getMax(); 
		for (int i = 1; i < this.getColumnCount(); i++)
			for (int j = 0; j < this.getRowCount(); j++) {
				Double cell = Double.valueOf(String.valueOf(this.getValueAt(j, i)));
				if (set[i - 1] != Variant.NOT_ENABLE) {
					if (set[i - 1] == Variant.MIN) {
						cell = cell / sum[i - 1];
					} else {
						cell = 1 - cell / sum[i - 1];
					}
				}

				this.setValueAt(cell, j, i);
			}
	}

	public String[] getEnabledColumn(boolean[] bool) {
		ArrayList<Boolean> listBool = new ArrayList<Boolean>();
		for (boolean b : bool) {
			if (b)
				listBool.add(b);
		}
		int enebledCount = listBool.size();
		int columnCount = this.getColumnCount();
		String[] columnName = new String[columnCount];
		for (int i = 0; i < columnCount; i++)
			columnName[i] = this.getColumnName(i);
		String[] enabled = new String[enebledCount + 1];
		int step = 0;
		for (int i = 0; i < columnCount; i++) {
			if (i == 0 || bool[i - 1]) {
				enabled[step++] = columnName[i];
			}
		}
		return enabled;
	}
	
	public Double[][] getEvaluateData(boolean[] bool) {
		ArrayList<Boolean> listBool = new ArrayList<Boolean>();
		for (boolean b : bool) {
			if (b)
				listBool.add(b);
		}
		int enebledCount = listBool.size();
		int rowCount = this.getRowCount();
		Double[][] result = new Double[rowCount][enebledCount + 1];
		for (int i = 0; i < rowCount; i++) {
			int step = 0;
			for (int j = 0; j < this.getColumnCount(); j++) {
				if (j == 0 || bool[j - 1]) {
					result[i][step++] = Double.valueOf(String.valueOf(this.getValueAt(i, j)));
				}
			}
		}
		return result;
	}
}
