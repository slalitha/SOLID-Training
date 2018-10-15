package com.example.notepad.actionlistener;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ReplaceListener {
	public void replacetext(JTextArea area){
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
}
