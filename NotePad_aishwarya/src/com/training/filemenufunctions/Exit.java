package com.training.filemenufunctions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.training.functions.NotePad;

public class Exit {
	
	public void exitFile() {
		saveBeforeExit();
		
	}
	
	/**
	 * Ask user whether they want to save the texts to a file before exiting
	 */
	private void saveBeforeExit() {
		NotePad notePad = NotePad.getInstance();
		notePad.text.selectAll();
		if (notePad.text.getSelectedText() != null && notePad.askToSave) {
			int select = JOptionPane.showConfirmDialog(notePad, "Save the texts to a file before exiting?",
					"JavaNotePad", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if (select == JOptionPane.YES_OPTION) {
				int result = notePad.chooser.showSaveDialog(notePad);
				if (result == JFileChooser.APPROVE_OPTION) {
					notePad.fileName = notePad.chooser.getSelectedFile().getPath();
					writeToFile(notePad.fileName);
				}
			} else if (select == JOptionPane.NO_OPTION)
				System.exit(0);
			else if (select == JOptionPane.CANCEL_OPTION)
				notePad.text.setCaretPosition(0);
		} else
			System.exit(0);
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
