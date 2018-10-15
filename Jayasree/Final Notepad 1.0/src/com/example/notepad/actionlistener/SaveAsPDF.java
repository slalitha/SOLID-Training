package com.example.notepad.actionlistener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class SaveAsPDF {
	public void saveaspdf(JMenuItem save, JTextArea area){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");

		int userSelection = fileChooser.showSaveDialog(save);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			saveasTextToPDF(fileChooser.getSelectedFile().toString(),area);
		}
	}
	private void saveasTextToPDF(String desc, JTextArea area) {
    	Document doc=new Document();
    	  try {
			PdfWriter.getInstance(doc, new FileOutputStream(desc));
    	  } catch (FileNotFoundException e) {
			e.printStackTrace();
    	  } catch (DocumentException e) {
			e.printStackTrace();
		  }
    	  doc.open();
    	  for (String line : area.getText().split("\\n")){
     		try {
				doc.add(new Paragraph(line));
			} catch (DocumentException e) {
				e.printStackTrace();
			}
    	  }
    	  doc.close();
		
	}
}
