package com.training.functions;

import java.util.Scanner;

public class Student {
	private int rollNo;
	private String name;
	
	public Student() {
		Scanner input = new Scanner(System.in);
		
		boolean isValid = false;
		do {
			System.out.println("Enter roll no:");
			if(input.hasNextInt()) {
				rollNo = input.nextInt();
				isValid = true;
			}
			
			if(!isValid) {
				System.out.println("Invalid Input");
				input.next();
			}
		}while(!(isValid));
		
		System.out.println("Enter Name:");
		name = input.nextLine();
	}
}
