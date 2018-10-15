package com.savehelper;

import java.io.BufferedWriter;
import java.io.File;
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
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Saveas {
	
	private static Saveas single_instance = null; 
	public static Saveas getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new Saveas(); 
  
        return single_instance; 
    } 
	Notepad notepad = Notepad.getInstance();
	public void saveas()
	{
		
		JFileChooser j = new JFileChooser("f:"); 
	
		 j.setFileFilter(new FileNameExtensionFilter(".txt files","txt"));
		 j.addChoosableFileFilter(new FileNameExtensionFilter(".pdf files","pdf"));
		 j.addChoosableFileFilter(new FileNameExtensionFilter(".html files","html"));
		 j.addChoosableFileFilter(new FileNameExtensionFilter(".xml files","xml"));
		 j.addChoosableFileFilter(new FileNameExtensionFilter(".docx files","docx"));
		 
           // Invoke the showsSaveDialog function to show the save dialog 
           int r = j.showSaveDialog(null); 
           
           if (r == JFileChooser.APPROVE_OPTION) { 
 
               // Set the label to the path of the selected directory 
           	String name = j.getSelectedFile().getAbsolutePath();
           	// if user choose saving as .txt
           	if(j.getFileFilter().getDescription().equals(".txt files"))
           	{
           		saveastxt(name); 
            } 
           
           else
           {
        	   if(j.getFileFilter().getDescription().equals(".pdf files"))
        	   {
        		   saveaspdf(name);	       
        	   }
        	   else
        	   {
        		   if(j.getFileFilter().getDescription().equals(".html files"))
        		   {
        			  saveashtml(name);
        			   }
        		   else {
        			   if(j.getFileFilter().getDescription().equals(".xml files"))
        			   {
        				   saveasxml(name);   
        			   }
        			   else {
        				   if(j.getFileFilter().getDescription().equals(".docx files"))
        				   {
        					  saveasdoc(name);
        				   }
        			   }
        		   }
        		   }
        	   
          
           }
           	}
           
	}
	public void saveastxt(String name)
	{
		if(!name.toLowerCase().endsWith(".txt"))
     	{
    		name +=".txt";
    	}
    	notepad.path = name;
        File fi = new File(name); 
        if (fi.exists()) {
            int response = JOptionPane.showConfirmDialog(null, //
                    "Do you want to replace the existing file?", //
                    "Confirm", JOptionPane.YES_NO_OPTION, //
                    JOptionPane.QUESTION_MESSAGE);
            if (response != JOptionPane.YES_OPTION) {
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
        } 
         catch (Exception evt) { 
            JOptionPane.showMessageDialog(notepad.mframe.jf, evt.getMessage()); 
        }
	}
	public void saveaspdf(String name)
	{
		if(!name.toLowerCase().endsWith(".pdf"))
    	{
   		name +=".pdf";
   	    }
   	   notepad.path = name;
       File f = new File(name); 
       if (f.exists()) {
           int response = JOptionPane.showConfirmDialog(null, //
                   "Do you want to replace the existing file?", //
                   "Confirm", JOptionPane.YES_NO_OPTION, //
                   JOptionPane.QUESTION_MESSAGE);
           if (response != JOptionPane.YES_OPTION) {
               return;
           } 
       }
       try
       {
    	  Document pdf = new Document();
    	  PdfWriter pdfwr = PdfWriter.getInstance(pdf, new FileOutputStream(notepad.path));
    	  pdf.open();
    	  pdf.add(new Paragraph(notepad.mframe.txtArea.getText()));
    	  pdf.close();
    	  pdfwr.close();
    	  notepad.mframe.jf.setTitle(f.getName()+ notepad.suffix);
       }
       catch (Exception e)
       {
    		   e.printStackTrace();
       }
	}
	public void saveashtml(String name)
	{
		 if(!name.toLowerCase().endsWith(".html"))
     	   {
			   name +=".html";
    	   }
		   notepad.path = name;
		   File fh = new File(name);
		   if (fh.exists()) {
			   int response = JOptionPane.showConfirmDialog(null, //
                    "Do you want to replace the existing file?", //
                    "Confirm", JOptionPane.YES_NO_OPTION, //
                    JOptionPane.QUESTION_MESSAGE);
			   if (response != JOptionPane.YES_OPTION) {
				   return;
				   } 
			   }
		   try { 
             	
             // Create a file writer 
              FileWriter wr = new FileWriter(fh, false); 

             // Create buffered writer to write 
              BufferedWriter w = new BufferedWriter(wr); 

            // Write 
             w.write(notepad.mframe.txtArea.getText()); 
             notepad.mframe.jf.setTitle(fh.getName()+ notepad.suffix);
             w.flush(); 
             w.close(); 
         } 
          catch (Exception evt) { 
             JOptionPane.showMessageDialog(notepad.mframe.jf, evt.getMessage()); 
         } 
	}
	public void saveasxml(String name)
	{
		if(!name.toLowerCase().endsWith(".xml"))
     	{
    		name +=".xml";
    	}
    	notepad.path = name;
        File fxml = new File(name); 
        if (fxml.exists()) {
            int response = JOptionPane.showConfirmDialog(null, //
                    "Do you want to replace the existing file?", //
                    "Confirm", JOptionPane.YES_NO_OPTION, //
                    JOptionPane.QUESTION_MESSAGE);
            if (response != JOptionPane.YES_OPTION) {
                return;
            } 
        }
        try { 
       	
            // Create a file writer 
             FileWriter wr = new FileWriter(fxml, false); 

            // Create buffered writer to write 
             BufferedWriter w = new BufferedWriter(wr); 

           // Write 
            w.write(notepad.mframe.txtArea.getText()); 
            notepad.mframe.jf.setTitle(fxml.getName()+ notepad.suffix);
            w.flush(); 
            w.close(); 
        } 
         catch (Exception evt) { 
            JOptionPane.showMessageDialog(notepad.mframe.jf, evt.getMessage()); 
        }
	}
	public void saveasdoc(String name)
	{
		 if(!name.toLowerCase().endsWith(".docx"))
     	{
    		name +=".docx";
    	    }
    	   notepad.path = name;
        File fd = new File(name); 
        if (fd.exists()) {
            int response = JOptionPane.showConfirmDialog(null, //
                    "Do you want to replace the existing file?", //
                    "Confirm", JOptionPane.YES_NO_OPTION, //
                    JOptionPane.QUESTION_MESSAGE);
            if (response != JOptionPane.YES_OPTION) {
                return;
            } 
        }
        try {
     	   XWPFDocument docfile = new XWPFDocument();
			   FileOutputStream fout = new FileOutputStream(fd);
			   
			   XWPFParagraph docParagraph = docfile.createParagraph();
			   XWPFRun run = docParagraph.createRun();
			   run.setText(notepad.mframe.txtArea.getText());
			   docfile.write(fout);
			   fout.close();
			   docfile.close();
			   notepad.mframe.jf.setTitle(fd.getName()+ notepad.suffix);
        }
        catch (Exception e)
        {
     	   e.printStackTrace();
        }
	}
	
}
