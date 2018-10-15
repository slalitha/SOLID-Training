package com.findreplacehelper;

import com.function.Notepad;

public class Replace {
	Notepad notepad = Notepad.getInstance();
	public void replace()
	{
		try {
			if(notepad.replaced_flag == 0)
			{
				String old = notepad.mframe.txtArea.getText();
				int pattersize = notepad.freplace.textField.getText().length();
				String news = old.substring(0, notepad.counter_1-pattersize) + notepad.freplace.textField_1.getText() + old.substring(notepad.counter_1,old.length());
				notepad.mframe.txtArea.setText(news);
				notepad.replaced_flag = 1;
			}
		}
		catch(Exception metachar) {	}
	}
}
