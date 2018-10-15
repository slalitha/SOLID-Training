package com.function;

import javax.swing.JOptionPane;

import com.savehelper.Save;

public class newfile {

	Notepad notepad = Notepad.getInstance();
	public void fnew()
	{
		if(notepad.save_flag== 1||(notepad.mframe.jf.getTitle()==notepad.untitled&&notepad.mframe.txtArea.getText().length()==0))
		{
			notepad.path = null;
			notepad.mframe.txtArea.setText("");
			notepad.mframe.jf.setTitle(notepad.untitled);
			notepad.save_flag = 1;
			return;
		}
		int dialogResult = JOptionPane.showConfirmDialog(notepad.mframe.jf,"Do you want to save changes to "+notepad.mframe.jf.getTitle().substring(0, notepad.mframe.jf.getTitle().indexOf(notepad.suffix))); 
		if(dialogResult == JOptionPane.CANCEL_OPTION){
			// do nothing
			}
		else {
			if(dialogResult == JOptionPane.YES_OPTION)
			{
                Save.getInstance().save();
				if(notepad.save_flag==1)
				{	
				  notepad.path = null;
				  notepad.mframe.txtArea.setText("");
				  notepad.mframe.jf.setTitle(notepad.untitled);
				  notepad.save_flag = 0;
				  return;
				}
				return;
			}
			else
			{
				if(dialogResult == JOptionPane.NO_OPTION)
				{
					notepad.path = null;
					notepad.mframe.txtArea.setText("");
					notepad.mframe.jf.setTitle(notepad.untitled);
					return;
				}
			}
		}
		return;
		
	}
}
