
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;




public class Notepad implements ActionListener, DocumentListener, MouseListener, WindowListener {

	public static void main(String[] args)
	{
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 new Notepad();
					 
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	JTextArea txtArea = new JTextArea();
	JScrollPane scroll = new JScrollPane(txtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	JFrame jf = new JFrame();
	
	JMenuBar jmb = new JMenuBar();
	JMenu fileMenu = new JMenu("File");
	JMenuItem fOpenMenu = new JMenuItem("Open ");
	JMenuItem fSaveMenu = new JMenuItem("Save ");
	JMenuItem fSaveasMenu = new JMenuItem("Save As");
	JMenuItem fSavecloud = new JMenuItem("Save to Cloud");
	JMenu EditMenu = new JMenu("Edit");
	JMenuItem cut = new  JMenuItem("Cut  ");
	JMenuItem copy=new   JMenuItem("Copy ");
	JMenuItem paste= new JMenuItem("Paste ");
	JMenuItem SMenu = new JMenuItem("Search");
	JMenuItem RMenu = new JMenuItem("Replace");
	JMenuItem FormatMenu = new JMenuItem("Format");
	
	JMenuItem fNew = new JMenuItem("New");
	JFrame frmFindAndReplace = new JFrame();
	JTextField textField = new JTextField();
	JButton btnFindNext = new JButton("Find Next");
	JLabel lblFindWhat = new JLabel("Find what :");
	JLabel lblReplaceWith = new JLabel("Replace with :");
	JTextField textField_1 = new JTextField();
	JButton btnReplace = new JButton("Replace");
	JButton btnReplaceAll = new JButton("Replace All");
	JCheckBox chckbxCaseInsensitive = new JCheckBox("Case insensitive");
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Clipboard clipboard = toolkit.getSystemClipboard();
	
	int counter = 0;
	int counter_1 = 0;
	
	protected find f = new find();
	protected Format format = new Format();
	protected Billingpage bill = new Billingpage();
	
	protected String path= null;
	protected String untitled = "Untitled - NotePad";
	protected String suffix = " - NotePad";
	protected int save_flag = 1;
	protected int replaced_flag = 0;
	protected double TotatCost = 0;
	protected long startTime = System.nanoTime();
	//Font for text area
	Font font = new Font("Arial", Font.ITALIC, 14);
	
	public Notepad() {
		
		
 // Main Frame
		//Adding all actions to listen
	
		jf.addWindowListener(this);
		fNew.addActionListener(this);
		fOpenMenu.addActionListener(this);
		fSaveMenu.addActionListener(this);
		fSaveasMenu.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		SMenu.addActionListener(this);
		RMenu.addActionListener(this);
		FormatMenu.addActionListener(this);
	    EditMenu.addActionListener(this);
		
		// Adding document to listen change in text
		txtArea.getDocument().addDocumentListener(this);
		txtArea.getDocument().putProperty("name", "txtArea");
		txtArea.addMouseListener(this);
		f.textFindField.getDocument().addDocumentListener(this);
		f.textFindField.getDocument().putProperty("name", "textFindField");
		textField.getDocument().addDocumentListener(this);
		textField_1.getDocument().addDocumentListener(this);
		textField.getDocument().putProperty("name", "textField");
		textField_1.getDocument().putProperty("name", "textField_1");
		
		//Binding short key
	    fOpenMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
	    fSaveMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
	    fSaveasMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
	    cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
	    copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
	    paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
		
		// FileMenu items adding
		fileMenu.add(fNew);
		fileMenu.add(fOpenMenu);
		fileMenu.add(fSaveMenu);
		fileMenu.add(fSaveasMenu);
		fileMenu.add(fSavecloud);
		
		// EditMenu items adding
		EditMenu.add(cut);
		EditMenu.add(copy);
		EditMenu.add(paste);
		EditMenu.add(RMenu);
		EditMenu.add(SMenu);
		EditMenu.add(FormatMenu);
		
		txtArea.setFont(font);
		Border border = txtArea.getBorder();
	    Border margin = new EmptyBorder(10,10,10,10);
	    txtArea.setBorder(new CompoundBorder(border, margin));
		// Add Menu Items to menu bar
		scroll.setSize(800, 400);
		jmb.add(fileMenu);
		jmb.add(EditMenu);
		jf.add(scroll);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jf.setSize((int)( screenSize.width/1.5),(int)(screenSize.height/1.5));
		jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		jf.setVisible(true);
		jf.setLocationRelativeTo(null);
		jf.setTitle(untitled);
		jf.setJMenuBar(jmb);
		
		if(txtArea.getText().isEmpty())
		{
		   cut.setEnabled(false);
		   copy.setEnabled(false);
			Transferable clipdata = clipboard.getContents(clipboard);
			if(clipdata.toString().isEmpty())
				paste.setEnabled(false);
		}
//FInd frame
		
		
		f.frmFind.addWindowListener(this);
		f.btnFindFindNext.addActionListener(this);
		f.chckbxCaseInsensitive.addActionListener(this);
		f.frmFind.setLocationRelativeTo(jf);
		f.frmFind.setAlwaysOnTop(true);
		f.frmFind.setLocation(600, 200);
		f.frmFind.setResizable(false);
		
		
		
//Find and Replace Frame 
		
		frmFindAndReplace.setAlwaysOnTop(true);
		frmFindAndReplace.setResizable(false);
		
		frmFindAndReplace.addWindowListener(this);
		
		btnFindNext.addActionListener(this);
		btnReplace.addActionListener(this);
		btnReplaceAll.addActionListener(this);
		btnReplace.setEnabled(false);
		
		frmFindAndReplace.setTitle("Replace");
		frmFindAndReplace.setBounds(100, 100, 397, 152);
		frmFindAndReplace.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmFindAndReplace.getContentPane().setLayout(null);
		frmFindAndReplace.setResizable(false);
		textField.setBounds(109, 14, 112, 20);
		frmFindAndReplace.getContentPane().add(textField);
		textField.setColumns(10);
		frmFindAndReplace.setVisible(false);

		btnFindNext.setBounds(247, 11, 102, 23);
		frmFindAndReplace.getContentPane().add(btnFindNext);
		lblFindWhat.setBounds(10, 17, 74, 14);
		frmFindAndReplace.getContentPane().add(lblFindWhat);
		lblReplaceWith.setBounds(10, 59, 74, 20);
		frmFindAndReplace.getContentPane().add(lblReplaceWith);
		
		
		textField_1.setBounds(109, 59, 112, 20);
		frmFindAndReplace.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		btnReplace.setBounds(247, 39, 102, 23);
		frmFindAndReplace.getContentPane().add(btnReplace);
		
		chckbxCaseInsensitive.setBounds(85, 90, 146, 23);
		chckbxCaseInsensitive.setSelected(false);
		frmFindAndReplace.getContentPane().add(chckbxCaseInsensitive);
		
		
		btnReplaceAll.setBounds(247, 67, 102, 23);
		frmFindAndReplace.getContentPane().add(btnReplaceAll);
		frmFindAndReplace.setLocationRelativeTo(jf);
		frmFindAndReplace.setLocation(600, 200);

// Format frame
		
		
		format.frame.addWindowListener(this);
		format.frame.setLocationRelativeTo(jf);
		format.frame.setAlwaysOnTop(true);
		format.frame.setLocation(600, 200);
		format.frame.setResizable(false);
		format.btnNewButton.addActionListener(this);
		format.btnNewButton_1.addActionListener(this);
		format.btnNewButton_2.addActionListener(this);
		format.btnSplitWords.addActionListener(this);
		
// Billing page
		
		bill.btnOk.addActionListener(this);
		bill.frame.setResizable(false);
		format.frame.setLocationRelativeTo(null);
		format.frame.setAlwaysOnTop(true);
		

	}
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
	public void fnew()
	{
		if(save_flag== 1||(jf.getTitle()==untitled&&txtArea.getText().length()==0))
		{
			path = null;
			txtArea.setText("");
			jf.setTitle(untitled);
			save_flag = 1;
			return;
		}
		int dialogResult = JOptionPane.showConfirmDialog(jf,"Do you want to save changes to "+jf.getTitle().substring(0, jf.getTitle().indexOf(suffix))); 
		if(dialogResult == JOptionPane.CANCEL_OPTION){
			// do nothing
			}
		else {
			if(dialogResult == JOptionPane.YES_OPTION)
			{
				save();
				if(save_flag==1)
				{	
				  path = null;
				  txtArea.setText("");
				  jf.setTitle(untitled);
				  save_flag = 0;
				  return;
				}
				return;
			}
			else
			{
				if(dialogResult == JOptionPane.NO_OPTION)
				{
					path = null;
					txtArea.setText("");
					jf.setTitle(untitled);
					return;
				}
			}
		}
		return;
		
	}
	public void open()
	{
		  // Create an object of JFileChooser class 
		if(save_flag==0)
		{
			int dialogResult = JOptionPane.showConfirmDialog(jf,"Do you want to save changes to "+jf.getTitle().substring(0, jf.getTitle().indexOf(suffix))); 
			if(dialogResult == JOptionPane.CANCEL_OPTION){
				return;
				}
			else
			{
				if(dialogResult==JOptionPane.YES_OPTION)
				{
					save();
					if(save_flag==0)
						return;
				}
				else
				{
					//Do nothing user don't want to save the changes
				}
			}
			
		}
        JFileChooser j = new JFileChooser("f:"); 
         
        j.setFileFilter(new FileNameExtensionFilter(".txt files","txt"));
        // Invoke the showsOpenDialog function to show the save dialog 
        int r = j.showOpenDialog(null); 

        // If the user selects a file 
        if (r == JFileChooser.APPROVE_OPTION) { 
            // Set the label to the path of the selected directory 
            File fi = new File(j.getSelectedFile().getAbsolutePath()); 
            path = j.getSelectedFile().getAbsolutePath();
            try { 
                // String 
                String s1 = "", sl = ""; 

                // File reader 
                FileReader fr = new FileReader(fi); 

                // Buffered reader 
                BufferedReader br = new BufferedReader(fr); 

                // Initialize sl 
                sl = br.readLine(); 

                // Take the input from the file 
                while ((s1 = br.readLine()) != null) { 
                    sl = sl + "\n" + s1; 
                } 

                // Set the text 
                txtArea.setText(sl); 
                jf.setTitle(fi.getName()+ suffix);
                save_flag = 1;
                br.close();
            } 
            catch (Exception evt) { 
                JOptionPane.showMessageDialog(jf, evt.getMessage()); 
            } 
        }
	}
	public void copy()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String selection = txtArea.getSelectedText();
		if(selection==null){
            return;
        }
		StringSelection strSel = new StringSelection(selection);
		clipboard.setContents(strSel, strSel);
	}
	public void paste()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		Transferable clipdata = clipboard.getContents(clipboard);
		try {
			if(clipdata.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				String s = (String) (clipdata.getTransferData(DataFlavor.stringFlavor));
				txtArea.replaceSelection(s);
			}
		}
		catch (Exception er){
			
		}
	}
	public void cut()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String selection = txtArea.getSelectedText();
		if(selection==null){
            return;
        }
		StringSelection strSel = new StringSelection(selection);
		clipboard.setContents(strSel, strSel);
		txtArea.replaceRange("", txtArea.getSelectionStart(), txtArea.getSelectionEnd());
	}
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
           		if(!name.toLowerCase().endsWith(".txt"))
             	{
            		name +=".txt";
            	}
            	path = name;
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
                    w.write(txtArea.getText()); 
                    jf.setTitle(fi.getName()+ suffix);
                    w.flush(); 
                    w.close(); 
                } 
                 catch (Exception evt) { 
                    JOptionPane.showMessageDialog(jf, evt.getMessage()); 
                } 
            } 
           
           else
           {
        	   if(j.getFileFilter().getDescription().equals(".pdf files"))
        	   {
        		   if(!name.toLowerCase().endsWith(".pdf"))
                	{
               		name +=".pdf";
               	    }
               	   path = name;
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
                	  PdfWriter pdfwr = PdfWriter.getInstance(pdf, new FileOutputStream(path));
                	  pdf.open();
                	  pdf.add(new Paragraph(txtArea.getText()));
                	  pdf.close();
                	  pdfwr.close();
                	  jf.setTitle(f.getName()+ suffix);
                   }
                   catch (Exception e)
                   {
                		   e.printStackTrace();
                   }	
                   
        	   }
        	   else
        	   {
        		   if(j.getFileFilter().getDescription().equals(".html files"))
        		   {
        			   if(!name.toLowerCase().endsWith(".html"))
                   	   {
        				   name +=".html";
                  	   }
        			   path = name;
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
                           w.write(txtArea.getText()); 
                           jf.setTitle(fh.getName()+ suffix);
                           w.flush(); 
                           w.close(); 
                       } 
                        catch (Exception evt) { 
                           JOptionPane.showMessageDialog(jf, evt.getMessage()); 
                       } 
        			   }
        		   else {
        			   if(j.getFileFilter().getDescription().equals(".xml files"))
        			   {
        				 //  DocumentBuilderFactory dbFactory =  DocumentBuilderFactory.newInstance();
        				   if(!name.toLowerCase().endsWith(".xml"))
        	             	{
        	            		name +=".xml";
        	            	}
        	            	path = name;
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
        	                    w.write(txtArea.getText()); 
        	                    jf.setTitle(fxml.getName()+ suffix);
        	                    w.flush(); 
        	                    w.close(); 
        	                } 
        	                 catch (Exception evt) { 
        	                    JOptionPane.showMessageDialog(jf, evt.getMessage()); 
        	                } 
        				   
        			   }
        			   else {
        				   if(j.getFileFilter().getDescription().equals(".docx files"))
        				   {
        					   if(!name.toLowerCase().endsWith(".docx"))
        	                	{
        	               		name +=".docx";
        	               	    }
        	               	   path = name;
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
            					   run.setText(txtArea.getText());
            					   docfile.write(fout);
            					   fout.close();
            					   docfile.close();
            					   jf.setTitle(fd.getName()+ suffix);
        	                   }
        	                   catch (Exception e)
        	                   {
        	                	   e.printStackTrace();
        	                   }
        					   
        				   }
        			   }
        		   }
        		   }
        	   
          
           }
           	}
           
	}
	
	public void find(String pattern, Boolean selected)
	{
		//f.textFindField.setText(Integer.toString(txtArea.getDocument().getLength()));
		String doc = txtArea.getText();
		if(selected==true)
		{
			doc = doc.toLowerCase();
			pattern = pattern.toLowerCase();
		}
		int pos = doc.indexOf(pattern, counter);
		if(pos<0)
		{
			
			JOptionPane.showMessageDialog(jf,"Cannot find \""+pattern +"\"");
			
			return;
		}
		counter = pos + pattern.length();
		txtArea.setSelectionStart(pos);
		txtArea.setSelectionEnd(pos+pattern.length());
		//txtArea.setText(txtArea.getText().replaceAll(pattern, "Vimal"));

		
	}
	public void findNext(String pattern, Boolean selected)
	{
		String doc = txtArea.getText();
		if(selected==true)
		{
			doc = doc.toLowerCase();
			pattern = pattern.toLowerCase();
		}
		int pos = doc.indexOf(pattern, counter_1);
		if(pos<0)
		{
			btnReplace.setEnabled(false);
			JOptionPane.showMessageDialog(jf,"Cannot find \""+pattern +"\"");
			
			return;
		}
		btnReplace.setEnabled(true);
		counter_1 = pos + pattern.length();
		txtArea.setSelectionStart(pos);
		txtArea.setSelectionEnd(pos+pattern.length());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

// This is action performed on main frame functions
		if(e.getSource().equals(fNew)) 
		{
			fnew();
		}
		if(e.getSource().equals(cut)) 
		{
    		cut();
		}
		if(e.getSource().equals(copy)) 
		{
			copy();
		}
		if(e.getSource().equals(paste))
		{
			paste();
		}
		if(e.getSource().equals(FormatMenu))
		{
			format.frame.setVisible(true);
		}
		if(e.getSource().equals(SMenu))
		{
			counter = 0;
			f.frmFind.setVisible(true);	
		}
		if(e.getSource().equals(RMenu))
		{
			counter_1 = 0;
			frmFindAndReplace.setVisible(true);
		}
		
		if(e.getSource().equals(fOpenMenu))
		{
			open();
	    }
		if(e.getSource().equals(fSaveMenu))
		{
					
		    save();
	    }
		if(e.getSource().equals(fSaveasMenu))
		{
			saveas();
		}
		
// This is action performed on find and replace frame
		if(e.getSource().equals(btnFindNext))
		{
			findNext(textField.getText(), chckbxCaseInsensitive.isSelected());
			replaced_flag = 0;
		}
		if(e.getSource().equals(btnReplace))
		{
			try {
				if(replaced_flag == 0)
				{
					String old = txtArea.getText();
					int pattersize = textField.getText().length();
					String news = old.substring(0, counter_1-pattersize) + textField_1.getText() + old.substring(counter_1,old.length());
					txtArea.setText(news);
					replaced_flag = 1;
				}
			}
			catch(Exception metachar) {	}
			
			 }
		if(e.getSource().equals(btnReplaceAll))
		{
			// Handling meta character 
			try {
				if(chckbxCaseInsensitive.isSelected())
				{
					txtArea.setText(txtArea.getText().toLowerCase().replaceAll(textField.getText().toString().toLowerCase(), textField_1.getText().toString()));	
				}
				else
					txtArea.setText(txtArea.getText().replaceAll(textField.getText().toString(), textField_1.getText().toString() ));	
			}
			catch(Exception metachar) {
				if(chckbxCaseInsensitive.isSelected())
				{
					txtArea.setText(txtArea.getText().toLowerCase().replaceAll("\\"+ textField.getText().toString().toLowerCase(), textField_1.getText().toString() ));	
				}
				else
					txtArea.setText(txtArea.getText().replaceAll("\\"+ textField.getText().toString(), textField_1.getText().toString() ));	
			}
		}
// This is action performed on find frame
	 
		if(e.getSource().equals(f.btnFindFindNext))
		{
			find(f.textFindField.getText(), f.chckbxCaseInsensitive.isSelected());
		}

//  This is action performed on Format frame
		if(e.getSource().equals(format.btnNewButton))
		{
			String old = txtArea.getText();
			txtArea.setText(old.replaceAll("^\\s+", ""));
		}
		if(e.getSource().equals(format.btnNewButton_1))
		{
			String old = txtArea.getText();
			txtArea.setText(old.replaceAll("\\s+$", ""));
		}
		if(e.getSource().equals(format.btnNewButton_2))
		{
			txtArea.setText(txtArea.getText().trim());
		}
		if(e.getSource().equals(format.btnSplitWords))
		{
			txtArea.setText(txtArea.getText().replaceAll(" ", "\n"));
		}
	
// This is action performed on Bill page
		if(e.getSource().equals(bill.btnOk))
		{
			bill.frame.dispose();
			System.exit(0);
		}
	}


		
	@Override
	public void changedUpdate(DocumentEvent c) {
		// TODO Auto-generated method stub
		if(c.getDocument().getProperty("name").equals("textFindField"))
		{
			counter = 0;
		}
		if(c.getDocument().getProperty("name").equals("textField"))
		{
			counter_1 = 0;
		}
		if(c.getDocument().getProperty("name").equals("txtArea"))
		{
			if(!jf.getTitle().endsWith("*")&&path!=null)
				jf.setTitle(jf.getTitle()+"*");
			if(jf.getTitle().equals(untitled)&&txtArea.getText().isEmpty())
			{
				save_flag = 1;
			}
			else
				save_flag = 0;
		}
		if(!txtArea.getText().isEmpty())
		{
			cut.setEnabled(true);
			
		}
		{
			cut.setEnabled(false);
			copy.setEnabled(false);
		}
		Transferable clipdata = clipboard.getContents(clipboard);
		if(clipdata.toString().isEmpty())
		{
			paste.setEnabled(false);
		}
		else 
			paste.setEnabled(true);
		
		
		
	}
	@Override
	public void insertUpdate(DocumentEvent c) {
		// TODO Auto-generated method stub
		if(c.getDocument().getProperty("name").equals("textFindField"))
		{
			counter = 0;
		}
		
		if(c.getDocument().getProperty("name").equals("textField"))
		{
			counter_1 = 0;
		}
		// Capturing changes in txtarea.
		if(c.getDocument().getProperty("name").equals("txtArea"))
		{
			if(!jf.getTitle().endsWith("*")&&path!=null)
				jf.setTitle(jf.getTitle()+"*");
			if(jf.getTitle().equals(untitled)&&txtArea.getText().isEmpty())
			{
				save_flag = 1;
			}
			else
				save_flag = 0;
		}
		if(!txtArea.getText().isEmpty())
		{
			cut.setEnabled(true);
			copy.setEnabled(true);
		}
		else
		{
			cut.setEnabled(false);
			copy.setEnabled(false);
		}
		Transferable clipdata = clipboard.getContents(clipboard);
		if(clipdata.toString().isEmpty())
		{
			paste.setEnabled(false);
		}
		else 
			paste.setEnabled(true);
		
		
	}
	
	@Override
	public void removeUpdate(DocumentEvent c) {
		// TODO Auto-generated method stub
		if(c.getDocument().getProperty("name").equals("textFindField"))
		{
			counter = 0;
		}
		if(c.getDocument().getProperty("name").equals("textField"))
		{
			counter_1 = 0;
		}
		// Capturing changes in txtarea.
		if(c.getDocument().getProperty("name").equals("txtArea"))
		{
			if(!jf.getTitle().endsWith("*")&&path!=null)
				jf.setTitle(jf.getTitle()+"*");
			if(jf.getTitle().equals(untitled)&&txtArea.getText().isEmpty())
			{
				save_flag = 1;
			}
			else
				save_flag = 0;
		}
		if(!txtArea.getText().isEmpty())
		{
			cut.setEnabled(true);
		}
		{
			cut.setEnabled(false);
			copy.setEnabled(false);
		}
		Transferable clipdata = clipboard.getContents(clipboard);
		if(clipdata.toString().isEmpty())
		{
			paste.setEnabled(false);
		}
		else 
			paste.setEnabled(true);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent m) {
		// TODO Auto-generated method stub
		counter = txtArea.getCaretPosition();
		counter_1 = txtArea.getCaretPosition();
		
	}
	@Override
	public void mouseEntered(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent win) {
		// TODO Auto-generated method stub
		if(win.getSource().equals(frmFindAndReplace))
		{
			textField.setText("");
			textField_1.setText("");
			btnReplace.setEnabled(false);
			chckbxCaseInsensitive.setSelected(false);
		}
		else {
			if(win.getSource().equals(f.frmFind))
			{
				f.textFindField.setText("");
				f.chckbxCaseInsensitive.setSelected(false);
			}
		}
		
	}
	@Override
	public void windowClosing(WindowEvent win) {
		// TODO Auto-generated method stub
		if(win.getSource().equals(jf))
		{
			
			if(path==null&&jf.getTitle()==untitled&&txtArea.getText().length()==0)
			{

				long endTime   = System.nanoTime();
				long totalTime = (endTime - startTime)/1000000000;
				bill.label.setText(bill.label.getText() + " " + totalTime + " sec.");
				double usedcost = (totalTime/60.0)*(1/60.0);
				bill.textPane.setText(new DecimalFormat("##.####").format(usedcost) + "$");
				TotatCost = usedcost;
				bill.textPane_2.setText(new DecimalFormat("##.####").format(TotatCost) + "$");
				bill.frame.setVisible(true);
				
				//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				return;
			}
			if(path==null||jf.getTitle().endsWith("*"))
			{
				int dialogResult = JOptionPane.showConfirmDialog(jf,"Do you want to close without saving the file - "+jf.getTitle().substring(0, jf.getTitle().indexOf(suffix))); 
				if(dialogResult == JOptionPane.CANCEL_OPTION){
					  jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					  
					}
				else {
					if(dialogResult == JOptionPane.YES_OPTION)
					{

						long endTime   = System.nanoTime();
						long totalTime = (endTime - startTime)/1000000000;
						bill.label.setText(bill.label.getText() +  " " +totalTime + " sec.");
						double usedcost = (totalTime/60.0)*(1/60.0);
						bill.textPane.setText(new DecimalFormat("##.####").format(usedcost) + "$");
						TotatCost = usedcost;
						bill.textPane_2.setText(new DecimalFormat("##.####").format(TotatCost) + "$");
						bill.frame.setVisible(true);
						//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}
					else
					{
						save();
						if(save_flag==1)
						{

							long endTime   = System.nanoTime();
							long totalTime = (endTime - startTime)/1000000000;
							bill.label.setText(bill.label.getText() +  " " +totalTime + " sec.");
							double usedcost = (totalTime/60.0)*(1/60.0);
							bill.textPane.setText(new DecimalFormat("##.####").format(usedcost) + "$");
							TotatCost = usedcost;
							bill.textPane_2.setText(new DecimalFormat("##.####").format(TotatCost) + "$");
							bill.frame.setVisible(true);
							//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						}
						else
						{
							jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
						}
					}
				}
				
			}
			else
			{

				long endTime   = System.nanoTime();
				long totalTime = (endTime - startTime)/1000000000;
				bill.label.setText(bill.label.getText() +  " " +totalTime + " sec.");
				double usedcost = (totalTime/60.0)*(1/60.0);
				bill.textPane.setText(new DecimalFormat("##.####").format(usedcost) + "$");
				TotatCost = usedcost;
				bill.textPane_2.setText(new DecimalFormat("##.####").format(TotatCost) + "$");
				bill.frame.setVisible(true);
				//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		}
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub	
	}
	}	


