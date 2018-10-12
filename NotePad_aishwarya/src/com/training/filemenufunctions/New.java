package com.training.filemenufunctions;

import com.training.constants.Constants;
import com.training.functions.NotePad;

public class New {
	
	public void newFile() {
		NotePad notePad = NotePad.getInstance();
		notePad.text.selectAll();
		int begin = notePad.text.getSelectionStart();
		int end = notePad.text.getSelectionEnd();
		notePad.text.replaceRange("", begin, end);
		notePad.setTitle(Constants.TITLE);
		if (notePad.findDialog != null)
			notePad.findDialog.initToZero();
	}

}
