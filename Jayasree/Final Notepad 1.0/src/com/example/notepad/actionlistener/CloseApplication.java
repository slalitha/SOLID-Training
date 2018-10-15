package com.example.notepad.actionlistener;

import javax.swing.JMenuItem;

public class CloseApplication {
	public double generateBill(long startTime, JMenuItem close){
		double amtperhour=1;
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		double seconds = totalTime / 1000;
		double hours=seconds/3600;
		return hours*amtperhour;
	}
}
