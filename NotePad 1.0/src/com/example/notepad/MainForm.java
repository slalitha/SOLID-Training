package com.example.notepad;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.*;  
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
public class MainForm  implements ActionListener
{  
	JMenu menu,saveas,edit,format;  
    JMenuItem open, save,close, doc, pdf, html,xml,copy,paste,cut,search,replace;  
    JMenuItem trimleading,trimtrailing,trim,splitwords;
    JTextArea area;
    JScrollPane jsp;
    JPanel jp=new JPanel();
    long startTime;
    
    MainForm(){  
    	startTime = System.currentTimeMillis();
        JFrame f= new JFrame("Notepad");  
        JMenuBar mb=new JMenuBar(); 
        menu=new JMenu("File");
        edit=new JMenu("Edit");
        saveas=new JMenu("Save as");
        format=new JMenu("Format");
        
        open=new JMenuItem("Open Ctrl+O");
        open.addActionListener(this);
        
        save=new JMenuItem("Save Ctrl+S"); 
        save.addActionListener(this);
        
        close=new JMenuItem("Close ");
        close.addActionListener(this);
        
        cut=new JMenuItem("Cut  Ctrl+X");
        cut.addActionListener(this);
        
        copy=new JMenuItem("Copy  Ctrl+C");
        copy.addActionListener(this);
        
        paste=new JMenuItem("Paste Ctrl+V");
        paste.addActionListener(this);
        
        search=new JMenuItem("Search ");
        search.addActionListener(this);
        
        replace=new JMenuItem("Replace");
        replace.addActionListener(this);
        
        doc=new JMenuItem("Text to Doc");
        doc.addActionListener(this);
        
        pdf=new JMenuItem("Text to Pdf");
        pdf.addActionListener(this);
        
        html=new JMenuItem("Text to Html");
        html.addActionListener(this);
        
        xml=new JMenuItem("Text to XML");
        
        trimleading=new JMenuItem("Trim Leading");
        trimleading.addActionListener(this);
        
        trimtrailing=new JMenuItem("Trim Trailing");
        trimtrailing.addActionListener(this);
        
        trim=new JMenuItem("Trim leading & Trailing spaces");
        trim.addActionListener(this);
        
        splitwords=new JMenuItem("Split Words");
        splitwords.addActionListener(this);
        
        menu.add(open); menu.add(save); menu.add(close);
        format.add(trimleading);
        format.add(trimtrailing);
        format.add(trim);
        format.add(splitwords);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(search);
        edit.add(replace);
        saveas.add(doc);
        saveas.add(pdf);
        saveas.add(html);
        saveas.add(xml);
        
        mb.add(menu);
        mb.add(format);
        mb.add(edit);
        mb.add(saveas);
        
        area=new JTextArea(100,100);  
        area.setBounds(10,30, 1350,1000); 
      
        f.add(area);
        f.setJMenuBar(mb);  
        f.setSize(400,400);  
        f.setLayout(null);  
        f.setVisible(true); 
        
    }  
    public static void main(String args[])  
    {  
    	new MainForm();  
    }
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource()==open){
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
    	else if(e.getSource()==save){
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
    	else if(e.getSource()==close){
    		long endTime   = System.currentTimeMillis();
    		long totalTime = endTime - startTime;
    		double seconds = totalTime / 1000;
    		double hours=seconds/3600;
    		String msg="Your bill amount: "+hours +"$";
    		JOptionPane.showMessageDialog(close, msg);
    		System.exit(0);
    	}
    	else if(e.getSource()==copy){
    		area.copy();
    	}
    	else if(e.getSource()==paste){
    		area.paste();
    	}
    	else if(e.getSource()==cut){
    		area.cut();
    	}
    	else if(e.getSource()==search){
    		String text=JOptionPane.showInputDialog(search,"Enter a word to search","Search");
    		if(area.getText().contains(text)){
    			Highlighter highlighter = area.getHighlighter();
    		    HighlightPainter painter=new DefaultHighlighter.DefaultHighlightPainter(Color.blue);
    		      int p0 = area.getText().indexOf(text);
    		      int p1 = text.length();
    		      try {
					highlighter.addHighlight(p0, p1, painter );
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
    		}
    		else{
    			JOptionPane.showMessageDialog(search,"Text not found");
    		}
    	}
    	else if(e.getSource()==replace){
    		JTextField searchtext=new JTextField(5);
    		JTextField replacetext=new JTextField(5);
    		JPanel mypanel=new JPanel();
    		mypanel.add(new JLabel("Enter search text:"));
    		mypanel.add(searchtext);
    		mypanel.add(Box.createHorizontalStrut(15));
    		mypanel.add(new JLabel("Enter replace text:"));
    		mypanel.add(replacetext);
    		int result=JOptionPane.showConfirmDialog(null, mypanel,"Replace",JOptionPane.OK_CANCEL_OPTION);
    		if(result==JOptionPane.OK_OPTION){
    			if(area.getText().contains(searchtext.getText())){
      		      area.setText(area.getText().replaceAll(searchtext.getText(), replacetext.getText()));
    			}
    		}
    	}
    	else if(e.getSource()==doc){
    		JFileChooser fileChooser = new JFileChooser();
    		fileChooser.setDialogTitle("Specify a file to save");

    		int userSelection = fileChooser.showSaveDialog(save);
    		if (userSelection == JFileChooser.APPROVE_OPTION) {
    			String text = area.getText();
    			writeDoc(fileChooser.getSelectedFile().toString(), text);
    			area.setText("");
    		}
    	}
    	else if(e.getSource()==pdf){
    		JFileChooser fileChooser = new JFileChooser();
    		fileChooser.setDialogTitle("Specify a file to save");

    		int userSelection = fileChooser.showSaveDialog(save);
    		if (userSelection == JFileChooser.APPROVE_OPTION) {
    			saveasTextToPDF(fileChooser.getSelectedFile().toString());
    		}
    	}
    	else if(e.getSource()==html){
    		try{
    			  FileOutputStream fs = new FileOutputStream("C:\\Users\\jpattab\\Documents\\Notepad\\TextToHTML1.html");
    			  OutputStreamWriter out = new OutputStreamWriter(fs);
    			  out.write("<html>");  
    			  out.write("<head>"); 
    			  out.write("<title>");  
    			  out.write("saveas text to html");
    			  out.write("</title>");  
    			  out.write("</head>");
    			  out.write("<body>");
    			  for (String line : area.getText().split("\\n")){
    				  out.write(line);
    				  out.write("<br>");
    			  }
    			  out.write("</body>");
    			  out.write("</html>");
    			  out.close();
    			  }
    			  catch (IOException ex){
    			  System.err.println(ex);
    			  }
    	}
    	else if(e.getSource()==trimleading){
    		String text=area.getText();
    		area.setText("");
    		 for (String line : text.split("\\n")){
    			    line = line.replaceAll("^\\s+", "");
    			    area.append(line);
    			    area.append("\n");
        	 }
    	}
    	else if(e.getSource()==trimtrailing){
    		String text=area.getText();
    		area.setText("");
    		 for (String line : text.split("\\n")){
    			    line = line.replaceAll("\\s+$", "");
    			    area.append(line);
    			    area.append("\n");
        	 }
    	}
    	else if(e.getSource()==trim){
    		String text=area.getText();
    		area.setText("");
    		 for (String line : text.split("\\n")){
    			area.append(line.trim());
    			area.append("\n");
        	 }
    	}
    	else if(e.getSource()==splitwords){
    		String text=area.getText();
    		area.setText("");
    		 for (String line : text.split(" ")){
    			area.append(line);
    			area.append("\n");
        	 }
    	}
    	
    }
    private void saveasTextToPDF(String desc) {
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