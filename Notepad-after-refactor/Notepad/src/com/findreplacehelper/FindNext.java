package com.findreplacehelper;

import javax.swing.JOptionPane;

import com.function.Notepad;

public class FindNext {
	Notepad notepad = Notepad.getInstance();
	public void findNext(String pattern, Boolean selected)
	{
		String doc = notepad.mframe.txtArea.getText();
		if(selected==true)
		{
			doc = doc.toLowerCase();
			pattern = pattern.toLowerCase();
		}
		int pos = doc.indexOf(pattern, notepad.counter_1);
		if(pos<0)
		{
			notepad.freplace.btnReplace.setEnabled(false);
			JOptionPane.showMessageDialog(notepad.mframe.jf,"Cannot find \""+pattern +"\"");
			
			return;
		}
		notepad.freplace.btnReplace.setEnabled(true);
		notepad.counter_1 = pos + pattern.length();
		notepad.mframe.txtArea.setSelectionStart(pos);
		notepad.mframe.txtArea.setSelectionEnd(pos+pattern.length());
	}
}
