package com.function;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class utilfun {
	
	private static utilfun single_instance = null; 
	public static utilfun getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new utilfun(); 
  
        return single_instance; 
    } 
	Notepad notepad = Notepad.getInstance();
	private utilfun() {
		
	}
	public void copy()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String selection = notepad.mframe.txtArea.getSelectedText();
		if(selection==null){
            return;
        }
		StringSelection strSel = new StringSelection(selection);
		clipboard.setContents(strSel, strSel);
	}
	public void paste()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		Transferable clipdata = clipboard.getContents(clipboard);
		try {
			if(clipdata.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				String s = (String) (clipdata.getTransferData(DataFlavor.stringFlavor));
				notepad.mframe.txtArea.replaceSelection(s);
			}
		}
		catch (Exception er){
			
		}
	}
	public void cut()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		String selection = notepad.mframe.txtArea.getSelectedText();
		if(selection==null){
            return;
        }
		StringSelection strSel = new StringSelection(selection);
		clipboard.setContents(strSel, strSel);
		notepad.mframe.txtArea.replaceRange("", notepad.mframe.txtArea.getSelectionStart(), notepad.mframe.txtArea.getSelectionEnd());
	}
	public void trimLeadingspace()
	{
		String old = notepad.mframe.txtArea.getText();
		notepad.mframe.txtArea.setText(old.replaceAll("^\\s+", ""));
	}
	public void trimTrailingspace()
	{
		String old = notepad.mframe. txtArea.getText();
		notepad.mframe.txtArea.setText(old.replaceAll("\\s+$", ""));
	}
}
