package com.example.notepad.actionlistener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class SaveListener {
	public void save(JTextArea area, JMenuItem save){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");

		int userSelection = fileChooser.showSaveDialog(save);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			 BufferedWriter out = null;
			try {
				out = new BufferedWriter(new FileWriter(fileToSave, false));
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
	         try {
	        	 for (String line : area.getText().split("\\n")){
	        		 out.append(line);
	        		 out.newLine();
	        	 }
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	         try {
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
		}
	}
}
