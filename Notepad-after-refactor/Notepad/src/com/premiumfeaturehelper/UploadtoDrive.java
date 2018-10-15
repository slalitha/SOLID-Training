package com.premiumfeaturehelper;

import java.io.File;

import com.function.Notepad;

public class UploadtoDrive {

	Notepad notepad = Notepad.getInstance();
	public void upload() {
	if(notepad.path!=null)
	{
		File file = new File(notepad.path);
		if(file.exists())
		{
			String filename = file.getName();
		    createDriveObject.UploadtoDrive(notepad.path, filename);
			storeuploadedfilesize(file.length());
		}
		
	}
}
	public void storeuploadedfilesize(double filesize)
	{
		notepad.totalBytes += filesize;
	}
}