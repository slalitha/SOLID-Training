package com.savehelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.function.Notepad;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Save {

	private static Save single_instance = null; 
	public static Save getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new Save(); 
  
        return single_instance; 
    } 
	Notepad notepad = Notepad.getInstance();
	
	public void save()
	{
		notepad.save_flag = 0;
		if(notepad.mframe.jf.getTitle()!=notepad.untitled)
		{
			File falready = new File(notepad.path); 
			
			if(notepad.path.endsWith(".pdf"))
			{
				savetopdf(falready);
				return;
			}
			if(notepad.path.endsWith(".docx"))
			{
				savetodoc(falready);
				return;
			}
			try { 
            	// Also work for HTML files
                // Create a file writer 
                FileWriter wr = new FileWriter(falready, false); 

                // Create buffered writer to write 
                BufferedWriter w = new BufferedWriter(wr); 

                // Write 
                w.write(notepad.mframe.txtArea.getText()); 
                notepad.mframe.jf.setTitle(falready.getName()+ notepad.suffix);
                w.flush(); 
                w.close(); 
                notepad.save_flag =1;
            } 
            catch (Exception evt) { 
                JOptionPane.showMessageDialog(notepad.mframe.jf, evt.getMessage()); 
            }
			return ;
			
		}
		 JFileChooser j = new JFileChooser("f:"); 
		  
		 j.setFileFilter(new FileNameExtensionFilter(".txt files","txt"));
		 
            // Invoke the showsSaveDialog function to show the save dialog 
            int r = j.showSaveDialog(null); 
  
            if (r == JFileChooser.APPROVE_OPTION) { 
  
                // Set the label to the path of the selected directory 
            	String name = j.getSelectedFile().getAbsolutePath();
            	if(!name.toLowerCase().endsWith(".txt"))
            	{
            		name +=".txt";
            	}
            	
            	// Saving path for later use
            	notepad.path = name;
            	
                File fi = new File(name); 
                if (fi.exists()) {
                    int response = JOptionPane.showConfirmDialog(null, //
                            "Do you want to replace the existing file?", //
                            "Confirm", JOptionPane.YES_NO_OPTION, //
                            JOptionPane.QUESTION_MESSAGE);
                    if (response != JOptionPane.YES_OPTION) {
                    	notepad.save_flag = 1;
                       return;
                    } 
                }
                try { 
                	
                    // Create a file writer 
                    FileWriter wr = new FileWriter(fi, false); 
  
                    // Create buffered writer to write 
                    BufferedWriter w = new BufferedWriter(wr); 
  
                    // Write 
                    w.write(notepad.mframe.txtArea.getText()); 
                    notepad.mframe.jf.setTitle(fi.getName()+ notepad.suffix);
                    w.flush(); 
                    w.close(); 
                    notepad.save_flag = 1;
                } 
                catch (Exception evt) { 
                    JOptionPane.showMessageDialog(notepad.mframe.jf, evt.getMessage()); 
                } 
            } 
            return;

	}
	public void savetopdf(File falready)
	{
		try
        {
     	  Document pdf = new Document();
     	  PdfWriter pdfwr = PdfWriter.getInstance(pdf, new FileOutputStream(notepad.path));
     	  pdf.open();
     	  pdf.add(new Paragraph(notepad.mframe.txtArea.getText()));
     	  pdf.close();
     	  pdfwr.close();
     	  notepad.mframe.jf.setTitle(falready.getName()+ notepad.suffix);
     	  notepad.save_flag = 1;

        }
        catch (DocumentException e)
        {
     		   e.printStackTrace();
        }	
        catch (FileNotFoundException e)
        {
     	       e.printStackTrace();
        }
	}
	public void savetodoc(File falready)
	{
		try {
			XWPFDocument docfile = new XWPFDocument();
			   FileOutputStream fout = new FileOutputStream(falready);
			   
			   XWPFParagraph docParagraph = docfile.createParagraph();
			   XWPFRun run = docParagraph.createRun();
			   run.setText(notepad.mframe.txtArea.getText());
			   docfile.write(fout);
			   fout.close();
			   docfile.close();
			   notepad.mframe.jf.setTitle(falready.getName()+ notepad.suffix);
			   notepad.save_flag = 1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		}
}
