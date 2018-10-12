package com.training.functions;

import java.util.Scanner;

public class Batch {
	private int numberOfStudents;
	private int numberOfSubjects;
	
	public Batch() {
		Scanner input = new Scanner(System.in);
		
		boolean isValid = false;
		do {
			System.out.println("Enter number of students:");
			if(input.hasNextInt()) {
				numberOfStudents = input.nextInt();
				isValid = true;
			}
			
			if(!isValid) {
				System.out.println("Invalid Input");
				input.next();
			}
		}while(!(isValid));
		
		isValid = false;
		do {
			System.out.println("Enter number of subjects:");
			if(input.hasNextInt()) {
				numberOfSubjects = input.nextInt();
				isValid = true;
			}
			
			if(!isValid) {
				System.out.println("Invalid Input");
				input.next();
			}
		}while(!(isValid));
	}
	
	public int getNoOfStudents() {
		return numberOfStudents;
	}
	
	public int getNoOfSubjects() {
		return numberOfSubjects;
	}
}
