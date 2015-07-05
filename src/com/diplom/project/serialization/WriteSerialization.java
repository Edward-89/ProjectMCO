package com.diplom.project.serialization;

import java.io.*;

public class WriteSerialization {

	public static void saveTableModel(DataTableSaver data, File file) {

		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(data);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveTableModel(DataTableSaver data, String fileNAme) {

		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileNAme));
			out.writeObject(data);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
