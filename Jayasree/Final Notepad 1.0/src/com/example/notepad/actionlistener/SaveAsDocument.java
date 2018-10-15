package com.example.notepad.actionlistener;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class SaveAsDocument {
	public void saveDocument(JTextArea area, JMenuItem save){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");

		int userSelection = fileChooser.showSaveDialog(save);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			String text = area.getText();
			writeDoc(fileChooser.getSelectedFile().toString(), text);
			area.setText("");
		}
	}

		private static void writeDoc(String FileName, String content) {
			try {
				POIFSFileSystem fs = new POIFSFileSystem();
				DirectoryEntry directory = fs.getRoot();
				directory.createDocument("WordDocument", new ByteArrayInputStream(
						content.getBytes()));
				FileOutputStream out = new FileOutputStream(FileName);
				fs.writeFilesystem(out);
				out.close();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
}
