package com.UIframes;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.function.Notepad;
import com.findhelper.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

public class find implements ActionListener, WindowListener, DocumentListener{

	public JFrame frmFind;
	public JTextField textFindField;
	protected JButton btnFindFindNext;
	public JCheckBox chckbxCaseInsensitive;
	/**
	 * Launch the application.
	 */
	
	

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public find() {
		initializwe();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initializwe() {
		frmFind = new JFrame();
		frmFind.setTitle("Find");
		frmFind.setBounds(100, 100, 392, 126);
		frmFind.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmFind.getContentPane().setLayout(null);
	
		JLabel lblFindWhat = new JLabel("Find what :");
		lblFindWhat.setBounds(10, 26, 79, 14);
		frmFind.getContentPane().add(lblFindWhat);
		
		textFindField = new JTextField();
		textFindField.setBounds(85, 23, 146, 20);
		frmFind.getContentPane().add(textFindField);
		textFindField.setColumns(10);
		
		btnFindFindNext = new JButton("Find Next");
		btnFindFindNext.setBounds(278, 22, 89, 23);
		frmFind.getContentPane().add(btnFindFindNext);
		
		chckbxCaseInsensitive = new JCheckBox("Case insensitive");
		chckbxCaseInsensitive.setBounds(85, 57, 146, 23);
		chckbxCaseInsensitive.setSelected(false);
		frmFind.getContentPane().add(chckbxCaseInsensitive);
		
		textFindField.getDocument().addDocumentListener(this);
		textFindField.getDocument().putProperty("name", "textFindField");
		
		frmFind.setAlwaysOnTop(true);
		frmFind.setLocation(600, 200);
		frmFind.setResizable(false);
		frmFind.setVisible(false);
		
		btnFindFindNext.addActionListener(this);
		chckbxCaseInsensitive.addActionListener(this);
		frmFind.addWindowListener(this);		
		
	}
	private void resetcounter()
	{
		Notepad notepad = Notepad.getInstance();
		notepad.counter = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(Notepad.getInstance().f.btnFindFindNext))
		{
			Find find = new Find();
			find.find(Notepad.getInstance().f.textFindField.getText(), Notepad.getInstance().f.chckbxCaseInsensitive.isSelected());
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent win) {
		// TODO Auto-generated method stub
		if(win.getSource().equals( frmFind))
		{
			// Reset all components of find frame
		     textFindField.setText("");
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
	public void changedUpdate(DocumentEvent c) {
		// TODO Auto-generated method stub
		resetcounter();
		
	}

	@Override
	public void insertUpdate(DocumentEvent c) {
		// TODO Auto-generated method stub
		resetcounter();
	}

	@Override
	public void removeUpdate(DocumentEvent c) {
		// TODO Auto-generated method stub
		resetcounter();
	}
}
