package com.example.notepad;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;  

import com.example.notepad.actionlistener.OpenFile;
import com.example.notepad.actionlistener.ReplaceListener;
import com.example.notepad.actionlistener.SaveAsDocument;
import com.example.notepad.actionlistener.SaveAsHTML;
import com.example.notepad.actionlistener.SaveAsPDF;
import com.example.notepad.actionlistener.SaveListener;
import com.example.notepad.actionlistener.SearchListener;
import com.example.notepad.billing.Billing;
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
    		OpenFile openfile=new OpenFile();
    		openfile.openfile(open,area);
    	}
    	else if(e.getSource()==save){
    		SaveListener savelistener=new SaveListener();
    		savelistener.save(area,save);
    	}
    	else if(e.getSource()==close){
    		long endTime   = System.currentTimeMillis();
    		long totalTime = endTime - startTime;
    		double seconds = totalTime / 1000;
    		double hours=seconds/3600;
    		
    		Billing billing=new Billing();
    		double billamount=billing.CalculateBill(hours, 0);
    		String msg="Your Bill amount is: "+billamount+"$";
    		JOptionPane.showMessageDialog(null, msg);
    		
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
    		SearchListener searchlistener=new SearchListener();
    		searchlistener.searchtext(search,area);
    	}
    	else if(e.getSource()==replace){
    		ReplaceListener replacelistener=new ReplaceListener();
    		replacelistener.replacetext(area);
    	}
    	else if(e.getSource()==doc){
    		SaveAsDocument saveasdocument=new SaveAsDocument();
    		saveasdocument.saveDocument(area,save);
    	}
    	else if(e.getSource()==pdf){
    		SaveAsPDF saveaspdf=new SaveAsPDF();
    		saveaspdf.saveaspdf(save,area);
    	}
    	else if(e.getSource()==html){
    		SaveAsHTML saveasHTML=new SaveAsHTML();
    		saveasHTML.saveasHTML(area);
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
    
}