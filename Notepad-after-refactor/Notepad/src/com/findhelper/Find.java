package com.findhelper;

import javax.swing.JOptionPane;

import com.function.Notepad;

public class Find {

	Notepad notepad = Notepad.getInstance();
	public void find(String pattern, Boolean selected)
	{
		//f.textFindField.setText(Integer.toString(txtArea.getDocument().getLength()));
		String doc = notepad.mframe.txtArea.getText();
		if(selected==true)
		{
			doc = doc.toLowerCase();
			pattern = pattern.toLowerCase();
		}
		int pos = doc.indexOf(pattern, notepad.counter);
		if(pos<0)
		{
			
			JOptionPane.showMessageDialog(notepad.mframe.jf,"Cannot find \""+pattern +"\"");
			
			return;
		}
		notepad.counter = pos + pattern.length();
		notepad.mframe.txtArea.setSelectionStart(pos);
		notepad.mframe.txtArea.setSelectionEnd(pos+pattern.length());	
	}
}
