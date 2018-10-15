package com.UIframes;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.function.Notepad;
import com.function.newfile;
import com.function.utilfun;
import com.openhelper.Open;
import com.premiumfeaturehelper.UploadtoDrive;
import com.savehelper.Save;
import com.savehelper.Saveas;

public class mainframe implements ActionListener, WindowListener, DocumentListener, MouseListener{

	public JTextArea txtArea;
	protected JScrollPane scroll;
	public JFrame jf;
	protected JMenuBar jmb;
	protected JMenu fileMenu;
	public JMenu EditMenu;
	public JMenuItem fOpenMenu;
	public JMenuItem fSaveMenu;
	public JMenuItem fSaveasMenu;
	public JMenuItem fSavecloud;
	public JMenuItem cut;
	public JMenuItem copy;
	public JMenuItem paste;
	public JMenuItem SMenu;
	public JMenuItem RMenu;
	public JMenuItem FormatMenu;
	public JMenuItem fNew;

	
	public mainframe() {
		initialize();
	}
	//Notepad notepad = Notepad.getInstance();
	private void initialize() {
		txtArea = new JTextArea();
		scroll = new JScrollPane(txtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jf = new JFrame();
		
		jmb = new JMenuBar();
		fileMenu = new JMenu("File");
        fOpenMenu = new JMenuItem("Open ");
		fSaveMenu = new JMenuItem("Save ");
		fSaveasMenu = new JMenuItem("Save As");
		fSavecloud = new JMenuItem("Save to Cloud");
		EditMenu = new JMenu("Edit");
		cut = new  JMenuItem("Cut  ");
		copy=new   JMenuItem("Copy ");
		paste= new JMenuItem("Paste ");
		SMenu = new JMenuItem("Search");
		RMenu = new JMenuItem("Replace");
		FormatMenu = new JMenuItem("Format");
		fNew = new JMenuItem("New");
		
		fOpenMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		fSaveMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		fSaveasMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
		    
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
		
		Font font = new Font("Arial", Font.ITALIC, 14);
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
	
		jf.setJMenuBar(jmb);
		jf.setTitle("Untitled - NotePad");

		if(txtArea.getText().isEmpty())
		{
		   cut.setEnabled(false);
		   copy.setEnabled(false);
		   paste.setEnabled(false);
		}
		
		jf.addWindowListener(this);
		 fNew.addActionListener(this);
		 fOpenMenu.addActionListener(this);
		 fSaveMenu.addActionListener(this);
		 fSaveasMenu.addActionListener(this);
		 fSavecloud.addActionListener(this);
		 cut.addActionListener(this);
		 copy.addActionListener(this);
		 paste.addActionListener(this);
		 SMenu.addActionListener(this);
		 RMenu.addActionListener(this);
		 FormatMenu.addActionListener(this);
	     EditMenu.addActionListener(this);
	     
	     
	     txtArea.addMouseListener(this);
	     
	     txtArea.getDocument().addDocumentListener(this);
		 txtArea.getDocument().putProperty("name", "txtArea");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(fNew)) 
		{
			newfile fnew = new newfile();
			fnew.fnew();
		}
		if(e.getSource().equals(cut)) 
		{
    		utilfun.getInstance().cut();
		}
		if(e.getSource().equals(copy)) 
		{
			utilfun.getInstance().copy();
		}
		if(e.getSource().equals(paste))
		{
			utilfun.getInstance().paste();
		}
		if(e.getSource().equals(FormatMenu))
		{
			Notepad.getInstance().format.frame.setVisible(true);
		}
		if(e.getSource().equals(SMenu))
		{
			Notepad.getInstance().counter = 0;
			Notepad.getInstance().f.frmFind.setVisible(true);	
		}
		if(e.getSource().equals(RMenu))
		{
			Notepad.getInstance().counter_1 = 0;
			Notepad.getInstance().freplace.frmFindAndReplace.setVisible(true);
		}
		
		if(e.getSource().equals(fOpenMenu))
		{
			Open open = new Open();
			open.open();
	    }
		if(e.getSource().equals(fSaveMenu))
		{
					
		    Save.getInstance().save();
	    }
		if(e.getSource().equals(fSaveasMenu))
		{
			Saveas.getInstance().saveas();
		}
		if(e.getSource().equals(fSavecloud))
		{
			Notepad.getInstance().usedpremiumfeature = true;
			UploadtoDrive upload = new UploadtoDrive();
			upload.upload();
		}
		
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent win) {
		// TODO Auto-generated method stub
		if(win.getSource().equals(jf))
		{
			
			Notepad notepad = Notepad.getInstance();
			
			if(notepad.path==null&& jf.getTitle()==notepad.untitled&& txtArea.getText().length()==0)
			{
				notepad.bill.displaybill();
				return;
			}
			if(notepad.path==null|| jf.getTitle().endsWith("*"))
			{
				int dialogResult = JOptionPane.showConfirmDialog( jf,"Do you want to close without saving the file - "+ jf.getTitle().substring(0,jf.getTitle().indexOf(notepad.suffix))); 
				switch(dialogResult)
				{
				case JOptionPane.CANCEL_OPTION:
					 jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					 break;
				case JOptionPane.YES_OPTION:
					 notepad.bill.displaybill();
					 break;
				case JOptionPane.NO_OPTION:
					if(notepad.save_flag==1)
					{
						notepad.bill.displaybill();
					}
					else
					{
						Save.getInstance().save();
						notepad.bill.displaybill();
					}
					break;	
				}
			}
			else
			{
				notepad.bill.displaybill();
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
	public void changedUpdate(DocumentEvent c) {
		// TODO Auto-generated method stub
		Notepad notepad = Notepad.getInstance();
		if(c.getDocument().getProperty("name").equals("txtArea"))
		{
			
			if(!jf.getTitle().endsWith("*")&& notepad.path!=null)
				jf.setTitle(jf.getTitle()+"*");
			if(jf.getTitle().equals(notepad.untitled)&& txtArea.getText().isEmpty())
			{
				notepad.save_flag = 1;
			}
			else
				notepad.save_flag = 0;
		}
		if(!txtArea.getText().isEmpty())
		{
			cut.setEnabled(true);
			copy.setEnabled(true);
		}
		else{
			cut.setEnabled(false);
			copy.setEnabled(false);
		}
		Transferable clipdata = notepad.clipboard.getContents(notepad.clipboard);
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
		Notepad notepad = Notepad.getInstance();
		if(c.getDocument().getProperty("name").equals("txtArea"))
		{
			
			if(!jf.getTitle().endsWith("*")&& notepad.path!=null)
				jf.setTitle(jf.getTitle()+"*");
			if(jf.getTitle().equals(notepad.untitled)&& txtArea.getText().isEmpty())
			{
				notepad.save_flag = 1;
			}
			else
				notepad.save_flag = 0;
		}
		if(!txtArea.getText().isEmpty())
		{
			cut.setEnabled(true);
			copy.setEnabled(true);
		}
		else{
			cut.setEnabled(false);
			copy.setEnabled(false);
		}
		Transferable clipdata = notepad.clipboard.getContents(notepad.clipboard);
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
		Notepad notepad = Notepad.getInstance();
		if(c.getDocument().getProperty("name").equals("txtArea"))
		{
			
			if(!jf.getTitle().endsWith("*")&& notepad.path!=null)
				jf.setTitle(jf.getTitle()+"*");
			if(jf.getTitle().equals(notepad.untitled)&& txtArea.getText().isEmpty())
			{
				notepad.save_flag = 1;
			}
			else
				notepad.save_flag = 0;
		}
		if(!txtArea.getText().isEmpty())
		{
			cut.setEnabled(true);
			copy.setEnabled(true);
		}
		else{
			cut.setEnabled(false);
			copy.setEnabled(false);
		}
		Transferable clipdata = notepad.clipboard.getContents(notepad.clipboard);
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
		Notepad notepad = Notepad.getInstance();
		notepad.counter = txtArea.getCaretPosition();
		notepad.counter_1 = txtArea.getCaretPosition();
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
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
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
