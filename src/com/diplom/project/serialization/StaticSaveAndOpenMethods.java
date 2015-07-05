package com.diplom.project.serialization;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import com.diplom.project.table.model.MyTableModel;


public class StaticSaveAndOpenMethods {
	public static boolean setCancel;
	
	private String extension = ".mtm"; // расширение файлов
	private    int    length = extension.length();
	
	
	// **** метод чтения с файла ******
	public DataTableSaver openMethods() {
		DataTableSaver man = null;
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return "Model files *" + extension;
			}
			
			@Override
			public boolean accept(File f) {
				char[] ch  = f.getName().toCharArray();
				String ext = new String(ch, ch.length-length, length);
				if(ext.equals(extension)) return true;
				return false;
			}
		});
		
		int returnVal = fc.showOpenDialog(fc);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try{
				man = (DataTableSaver) ReadSerialization.openTableModel(file);
				return man;
			} catch(Exception e) {}
		} else setCancel = true;

		return null;

	}

	// **** метод записи в файл *****
	public void saveMethods(MyTableModel model) {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return "Model files *" + extension;
			}
			
			@Override
			public boolean accept(File f) {
				char[] ch  = f.getName().toCharArray();
				String ext = new String(ch, ch.length-length, length);
				if(ext.equals(extension)) return true;
				return false;
			}
		});
		
		DataTableSaver data = new DataTableSaver(model);	
		int returnVal = fc.showSaveDialog(fc);
		if( returnVal == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			
			try {
				WriteSerialization.saveTableModel(data, file);
			} catch(Exception e){
				JOptionPane.showMessageDialog( fc ,
					    "This file not model",
					    "error",
					    JOptionPane.ERROR_MESSAGE);
				
			}
		}

	}

}
