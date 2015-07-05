package com.diplom.project.serialization;

import java.io.Serializable;

import com.diplom.project.table.model.MyTableModel;

public class DataTableSaver implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] colName;
	private Double[][] data;

	public DataTableSaver(MyTableModel model) {
		this.colName = model.getColumn();
		this.data = model.toDoubleData();
	}

	public String[] getColName() {
		return colName;
	}

	public Double[][] getData() {
		return data;
	}

}
