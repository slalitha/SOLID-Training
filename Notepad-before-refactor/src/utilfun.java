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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class utilfun extends Notepad {

	public void save()
	{
		save_flag = 0;
		if(jf.getTitle()!=untitled)
		{
			File falready = new File(path); 
			
			if(path.endsWith(".pdf"))
			{
				try
                {
             	  Document pdf = new Document();
             	  PdfWriter pdfwr = PdfWriter.getInstance(pdf, new FileOutputStream(path));
             	  pdf.open();
             	  pdf.add(new Paragraph(txtArea.getText()));
             	  pdf.close();
             	  pdfwr.close();
             	 jf.setTitle(falready.getName()+ suffix);
             	 save_flag = 1;
     
                }
                catch (DocumentException e)
                {
             		   e.printStackTrace();
                }	
                catch (FileNotFoundException e)
                {
             	       e.printStackTrace();
                }
				return;
			}
			if(path.endsWith(".docx"))
			{
				try {
					XWPFDocument docfile = new XWPFDocument();
					   FileOutputStream fout = new FileOutputStream(falready);
					   
					   XWPFParagraph docParagraph = docfile.createParagraph();
					   XWPFRun run = docParagraph.createRun();
					   run.setText(txtArea.getText());
					   docfile.write(fout);
					   fout.close();
					   docfile.close();
					   jf.setTitle(falready.getName()+ suffix);
					   save_flag = 1;
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				return;
			}
			
			try { 
            	// Also work for HTML files
                // Create a file writer 
                FileWriter wr = new FileWriter(falready, false); 

                // Create buffered writer to write 
                BufferedWriter w = new BufferedWriter(wr); 

                // Write 
                w.write(txtArea.getText()); 
                jf.setTitle(falready.getName()+ suffix);
                w.flush(); 
                w.close(); 
                save_flag =1;
            } 
            catch (Exception evt) { 
                JOptionPane.showMessageDialog(jf, evt.getMessage()); 
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
            	path = name;
            	
                File fi = new File(name); 
                if (fi.exists()) {
                    int response = JOptionPane.showConfirmDialog(null, //
                            "Do you want to replace the existing file?", //
                            "Confirm", JOptionPane.YES_NO_OPTION, //
                            JOptionPane.QUESTION_MESSAGE);
                    if (response != JOptionPane.YES_OPTION) {
                    	save_flag = 1;
                       return;
                    } 
                }
                try { 
                	
                    // Create a file writer 
                    FileWriter wr = new FileWriter(fi, false); 
  
                    // Create buffered writer to write 
                    BufferedWriter w = new BufferedWriter(wr); 
  
                    // Write 
                    w.write(txtArea.getText()); 
                    jf.setTitle(fi.getName()+ suffix);
                    w.flush(); 
                    w.close(); 
                    save_flag = 1;
                } 
                catch (Exception evt) { 
                    JOptionPane.showMessageDialog(jf, evt.getMessage()); 
                } 
            } 
            return;

	}
}
