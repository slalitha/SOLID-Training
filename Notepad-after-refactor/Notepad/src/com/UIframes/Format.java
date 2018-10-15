package com.UIframes;
import javax.swing.JFrame;

import com.function.Notepad;
import com.function.utilfun;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Format implements ActionListener {

	public JFrame frame;
    protected JButton btnNewButton;
    protected JButton btnNewButton_1;
    protected JButton btnNewButton_2;
    protected JButton btnSplitWords;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */
	public Format() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 406, 189);
		frame.setTitle("Formatting");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnNewButton = new JButton("Trim Leading Spaces");
		btnNewButton.setBounds(10, 11, 150, 31);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Trim Trailing Spaces");
		btnNewButton_1.setBounds(10, 53, 150, 31);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Trim Leading and Trailing Spaces");
		btnNewButton_2.setBounds(10, 95, 197, 31);
		frame.getContentPane().add(btnNewButton_2);
		
		btnSplitWords = new JButton("Split Words");
		btnSplitWords.setBounds(258, 13, 107, 27);
		frame.getContentPane().add(btnSplitWords);
	//	frame.setLocationRelativeTo(Notepad.getInstance().mframe.jf);
		frame.setAlwaysOnTop(true);
		frame.setLocation(600, 200);
		frame.setResizable(false);
		frame.setVisible(false);
		btnNewButton.addActionListener(this);
		btnNewButton_1.addActionListener(this);
		btnNewButton_2.addActionListener(this);
		btnSplitWords.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource().equals(btnNewButton))
		{
			utilfun.getInstance().trimLeadingspace();
		}
		if(e.getSource().equals(btnNewButton_1))
		{
			utilfun.getInstance().trimTrailingspace();
		}
		if(e.getSource().equals(btnNewButton_2))
		{
			Notepad.getInstance().mframe.txtArea.setText(Notepad.getInstance().mframe.txtArea.getText().trim());
		}
		if(e.getSource().equals(btnSplitWords))
		{
			Notepad.getInstance().mframe.txtArea.setText(Notepad.getInstance().mframe.txtArea.getText().replaceAll(" ", "\n"));
		}
	}
}
