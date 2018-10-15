package com.example.notepad.actionlistener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class OpenFile {
	public void openfile(JMenuItem open, JTextArea area){
		JFileChooser fileChooser = new JFileChooser();
  		 fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
  		 int result = fileChooser.showOpenDialog(open);
  		 if (result == JFileChooser.APPROVE_OPTION) {
  		     File selectedFile = fileChooser.getSelectedFile();
  		     BufferedReader bufferedReader = null;
				try {
					bufferedReader = new BufferedReader(new FileReader(selectedFile));
				} catch (FileNotFoundException ev) {
					ev.printStackTrace();
				}
  				String line;
  				try {
						while((line=bufferedReader.readLine())!=null){
							area.append(line);
							area.append("\n");
						}
					} 
  				catch (IOException ev) {
						ev.printStackTrace();
					}
  		 	}
	}
}
