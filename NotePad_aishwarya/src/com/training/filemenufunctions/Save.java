package com.training.filemenufunctions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

import com.training.functions.NotePad;

public class Save {
	
	public void saveCurrFile() {
		NotePad notePad = NotePad.getInstance();
		int result = notePad.chooser.showSaveDialog(notePad);
		if (result == JFileChooser.APPROVE_OPTION) {
			notePad.fileName = notePad.chooser.getSelectedFile().getPath();
			writeToFile(notePad.fileName);
			notePad.askToSave = false;
		}
	}
	
	/**
	 * Save the contents of the text area to a file
	 */
	private void writeToFile(String filename) {
		NotePad notePad = NotePad.getInstance();
		try {
			notePad.text.selectAll();
			String input = notePad.text.getSelectedText();
			PrintWriter out = new PrintWriter(new FileOutputStream(filename, false));
			int start = 0;
			String temp = "";
			String chars = "";
			if (input.indexOf("\n") == -1)
				out.println(input);
			else {
				for (int i = 0; i < input.length(); i++) {
					chars = chars + input.charAt(i);
					if (chars.equals("\n")) {
						out.println(temp);
						start = i + 1;
						temp = "";
					} else
						temp = temp + input.charAt(i);
					chars = "";
				}
			}
			out.close();
		} catch (IOException f) {
		}
	}

}
