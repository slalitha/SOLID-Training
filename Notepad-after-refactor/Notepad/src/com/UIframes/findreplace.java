package com.UIframes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.findreplacehelper.FindNext;
import com.findreplacehelper.Replace;
import com.findreplacehelper.ReplaceAll;
import com.function.Notepad;

public class findreplace implements WindowListener, ActionListener, DocumentListener {

	public JFrame frmFindAndReplace;
	public JTextField textField;
	protected JButton btnFindNext;
	protected JLabel lblFindWhat;
	protected JLabel lblReplaceWith;
	public JTextField textField_1;
	public JButton btnReplace ;
	protected JButton btnReplaceAll;
	public JCheckBox chckbxCaseInsensitive;
	
	public findreplace() {
		initialize();
	}
	private void initialize() {
		frmFindAndReplace = new JFrame();
		textField = new JTextField();
		btnFindNext = new JButton("Find Next");
	    lblFindWhat = new JLabel("Find what :");
		lblReplaceWith = new JLabel("Replace with :");
		textField_1 = new JTextField();
		btnReplace = new JButton("Replace");
		btnReplaceAll = new JButton("Replace All");
		chckbxCaseInsensitive = new JCheckBox("Case insensitive");
		
		frmFindAndReplace.setAlwaysOnTop(true);
		frmFindAndReplace.setResizable(false);
		
		 
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
		frmFindAndReplace.setLocationRelativeTo(null);
		frmFindAndReplace.setLocation(600, 200);

		btnFindNext.addActionListener(this);
		btnReplace.addActionListener(this);
		btnReplaceAll.addActionListener(this);
		frmFindAndReplace.addWindowListener(this);
		
		textField.getDocument().addDocumentListener(this);
		textField_1.getDocument().addDocumentListener(this);
		textField.getDocument().putProperty("name", "textField");
		textField_1.getDocument().putProperty("name", "textField_1");	
	}
	private void resetcounter()
	{
		Notepad notepad= Notepad.getInstance();
		notepad.counter_1 = 0;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(Notepad.getInstance().freplace.btnFindNext))
		{
			FindNext findnext = new FindNext();
			findnext.findNext(Notepad.getInstance().freplace.textField.getText(), Notepad.getInstance().freplace.chckbxCaseInsensitive.isSelected());
			Notepad.getInstance().replaced_flag = 0;
		}
		if(e.getSource().equals(Notepad.getInstance().freplace.btnReplace))
		{
			Replace replace = new Replace();
			replace.replace();
			 }
		if(e.getSource().equals(Notepad.getInstance().freplace.btnReplaceAll))
		{
			// Handling meta character 
			ReplaceAll replaceAll = new ReplaceAll();
			replaceAll.replaceAll();
		}
		
		
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent win) {
		
		// TODO Auto-generated method stub
		if(win.getSource().equals( frmFindAndReplace))
		{
			// Reset all components of find and replace frame.
			 textField.setText("");
			 textField_1.setText("");
			 btnReplace.setEnabled(false);
			 chckbxCaseInsensitive.setSelected(false);
		}
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
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
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		resetcounter();
	}
	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		resetcounter();
	}
	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub
		resetcounter();
	}

}
