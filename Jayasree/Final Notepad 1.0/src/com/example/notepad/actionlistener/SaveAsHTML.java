package com.example.notepad.actionlistener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JTextArea;

public class SaveAsHTML {
	public void saveasHTML(JTextArea area){
		try{
			  FileOutputStream fs = new FileOutputStream("C:\\Users\\jpattab\\Documents\\Notepad\\TextToHTML1.html");
			  OutputStreamWriter out = new OutputStreamWriter(fs);
			  out.write("<html>");  
			  out.write("<head>"); 
			  out.write("<title>");  
			  out.write("saveas text to html");
			  out.write("</title>");  
			  out.write("</head>");
			  out.write("<body>");
			  for (String line : area.getText().split("\\n")){
				  out.write(line);
				  out.write("<br>");
			  }
			  out.write("</body>");
			  out.write("</html>");
			  out.close();
			  }
			  catch (IOException ex){
			  System.err.println(ex);
			  }
	}
}
