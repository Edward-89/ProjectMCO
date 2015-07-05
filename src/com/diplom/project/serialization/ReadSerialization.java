package com.diplom.project.serialization;

import java.io.*;

public class ReadSerialization {

	public static DataTableSaver openTableModel(File file) {

		ObjectInputStream in = null;
		DataTableSaver data = null;

		try {
			in = new ObjectInputStream(new FileInputStream(file));
			data = (DataTableSaver) in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return data;

	}

	public static DataTableSaver openTableModel(String fileName) {

		ObjectInputStream in = null;
		DataTableSaver model = null;

		try {
			in = new ObjectInputStream(new FileInputStream(fileName));
			model = (DataTableSaver) in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return model;

	}
}
