package com.function;


import java.awt.EventQueue;

public class Application {

	public static void main(String[] args)
	{
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notepad.getInstance();
					System.out.print(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}