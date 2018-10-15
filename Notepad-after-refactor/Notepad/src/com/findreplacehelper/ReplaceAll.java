package com.findreplacehelper;

import com.function.Notepad;

public class ReplaceAll {

	Notepad notepad = Notepad.getInstance();
	public void replaceAll()
	{
		try {
			if(notepad.freplace.chckbxCaseInsensitive.isSelected())
			{
				notepad.mframe.txtArea.setText(notepad.mframe.txtArea.getText().toLowerCase().replaceAll(notepad.freplace.textField.getText().toString().toLowerCase(), notepad.freplace.textField_1.getText().toString()));	
			}
			else
				notepad.mframe.txtArea.setText(notepad.mframe.txtArea.getText().replaceAll(notepad.freplace.textField.getText().toString(), notepad.freplace.textField_1.getText().toString() ));	
		}
		catch(Exception metachar) {
			if(notepad.freplace.chckbxCaseInsensitive.isSelected())
			{
				notepad.mframe.txtArea.setText(notepad.mframe.txtArea.getText().toLowerCase().replaceAll("\\"+ notepad.freplace.textField.getText().toString().toLowerCase(), notepad.freplace.textField_1.getText().toString() ));	
			}
			else
				notepad.mframe.txtArea.setText(notepad.mframe.txtArea.getText().replaceAll("\\"+ notepad.freplace.textField.getText().toString(), notepad.freplace.textField_1.getText().toString() ));	
		}
	}
}
