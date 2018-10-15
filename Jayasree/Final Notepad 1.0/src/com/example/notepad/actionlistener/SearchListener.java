package com.example.notepad.actionlistener;

import java.awt.Color;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

public class SearchListener {
	public void searchtext(JMenuItem search, JTextArea area){
		String text=JOptionPane.showInputDialog(search,"Enter a word to search","Search");
		if(area.getText().contains(text)){
			Highlighter highlighter = area.getHighlighter();
		    HighlightPainter painter=new DefaultHighlighter.DefaultHighlightPainter(Color.blue);
		      int p0 = area.getText().indexOf(text);
		      int p1 = text.length();
		      try {
				highlighter.addHighlight(p0, p1, painter );
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		}
		else{
			JOptionPane.showMessageDialog(search,"Text not found");
		}
	}
}
