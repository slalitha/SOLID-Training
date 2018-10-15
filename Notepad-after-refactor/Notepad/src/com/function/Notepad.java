package com.function;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

import com.UIframes.Billingpage;
import com.UIframes.Format;
import com.UIframes.find;
import com.UIframes.findreplace;
import com.UIframes.mainframe;

public class Notepad {

	Toolkit toolkit = Toolkit.getDefaultToolkit();
	public Clipboard clipboard = toolkit.getSystemClipboard();
	public int counter = 0;
	public int counter_1 = 0;
	public mainframe mframe = new mainframe();
	public find f = new find();
	public Format format = new Format();
	public Billingpage bill = new Billingpage();
	public findreplace freplace= new findreplace();
	public boolean usedpremiumfeature = false;
	
	
	public String path= null;
	public String untitled = "Untitled - NotePad";
	public String suffix = " - NotePad";
	public int save_flag = 1;
	public int replaced_flag = 0;
	public double TotatCost = 0;
	public long startTime = System.nanoTime();
	public double totalBytes = 0;
	private static Notepad single_instance = null; 
	  
	private Notepad() {  
	}
	 // static method to create instance of Singleton class 
    public static Notepad getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new Notepad(); 
  
        return single_instance; 
    }  
}	


