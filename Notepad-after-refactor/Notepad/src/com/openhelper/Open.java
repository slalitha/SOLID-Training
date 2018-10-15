package com.openhelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.function.Notepad;
import com.savehelper.Save;

public class Open {

	Notepad notepad = Notepad.getInstance();
	public void open()
	{
		  // Create an object of JFileChooser class 
		if(notepad.save_flag==0)
		{
			int dialogResult = JOptionPane.showConfirmDialog(notepad.mframe.jf,"Do you want to save changes to "+notepad.mframe.jf.getTitle().substring(0, notepad.mframe.jf.getTitle().indexOf(notepad.suffix))); 
			if(dialogResult == JOptionPane.CANCEL_OPTION){
				return;
				}
			else
			{
				if(dialogResult==JOptionPane.YES_OPTION)
				{
					Save.getInstance().save();
					if(notepad.save_flag==0)
						return;
				}
				else
				{
					//Do nothing user don't want to save the changes
				}
			}
			
		}
        JFileChooser j = new JFileChooser("f:"); 
         
        j.setFileFilter(new FileNameExtensionFilter(".txt files","txt"));
        // Invoke the showsOpenDialog function to show the save dialog 
        int r = j.showOpenDialog(null); 

        // If the user selects a file 
        if (r == JFileChooser.APPROVE_OPTION) { 
            // Set the label to the path of the selected directory 
            File fi = new File(j.getSelectedFile().getAbsolutePath()); 
            notepad.path = j.getSelectedFile().getAbsolutePath();
            try { 
                // String 
                String s1 = "", sl = ""; 

                // File reader 
                FileReader fr = new FileReader(fi); 

                // Buffered reader 
                BufferedReader br = new BufferedReader(fr); 

                // Initialize sl 
                sl = br.readLine(); 

                // Take the input from the file 
                while ((s1 = br.readLine()) != null) { 
                    sl = sl + "\n" + s1; 
                } 

                // Set the text 
                notepad.mframe.txtArea.setText(sl); 
                notepad.mframe.jf.setTitle(fi.getName()+ notepad.suffix);
                notepad.save_flag = 1;
                br.close();
            } 
            catch (Exception evt) { 
                JOptionPane.showMessageDialog(notepad.mframe.jf, evt.getMessage()); 
            } 
        }
	}
}
