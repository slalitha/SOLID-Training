package com.training.filemenufunctions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

import com.training.functions.NotePad;

public class Open {

	public void openFile() {
		NotePad notePad = NotePad.getInstance();
		int result = notePad.chooser.showOpenDialog(notePad);
		if (result == JFileChooser.APPROVE_OPTION) {
			notePad.fileName = notePad.chooser.getSelectedFile().getPath();
			notePad.setTitle(notePad.fileName);
			notePad.text.selectAll();
			int begin = notePad.text.getSelectionStart();
			int end = notePad.text.getSelectionEnd();
			notePad.text.replaceRange("", begin, end);
			putTextIntoTextArea();
			notePad.openForTheFirstTime = true;
			notePad.askToSave = false;
			if (notePad.findDialog != null)
				notePad.findDialog.initToZero();
			if (notePad.repDialog != null)
				notePad.repDialog.initToZero();
			notePad.text.setCaretPosition(0);
		
		}
	}
	
	/**
	 * Open a requested file and display the content of file in the text area
	 */
	private void putTextIntoTextArea() {
		NotePad notePad = NotePad.getInstance();
		try {
			FileReader file = new FileReader(notePad.fileName);
			BufferedReader in = new BufferedReader(file);
			boolean done = false;
			while (!done) {
				String line = in.readLine();
				if (line == null)
					done = true;
				else
					notePad.text.append(line + "\n");
			}
		} catch (FileNotFoundException e) {
			notePad.text.append(notePad.fileName + " not found \n");
		} catch (IOException e) {
			notePad.text.append("Error when trying to read " + notePad.fileName + " \n");
		}
	}
		
}
