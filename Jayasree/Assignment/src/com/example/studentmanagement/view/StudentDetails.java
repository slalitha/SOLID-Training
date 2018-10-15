package com.example.studentmanagement.view;

import java.util.Scanner;

import com.example.studentmanagement.manager.SingletonStudent;
import com.example.studentmanagement.manager.StudentResultMain;

public class StudentDetails {
	
	public void getStudentDetails(){
		@SuppressWarnings("resource")
		Scanner in=new Scanner(System.in);
		System.out.println("********Options*******");
		System.out.println("1.Add Student\n2.List Student\n3.Get Result");
		String option=in.next();
		System.out.println(Integer.parseInt(option));
		do{
			switch(option){
			case "1":
				AddStudent addstudent=new AddStudent();
				addstudent.addStudent();
				break;
			case "2":
				if(SingletonStudent.getInstance().getStudentList().size()==0)
					System.out.println("No students yet!");
				else{
					ListStudent liststudent=new ListStudent();
					liststudent.display(SingletonStudent.getInstance().getStudentList());
				}
				break;
			case "3":
				if(SingletonStudent.getInstance().getStudentList().size()==0)
					System.out.println("No students yet!");
				else{
					StudentResultMain studentresult=new StudentResultMain();
					System.out.println("************************Student Result********************");
					System.out.println("Name\tResult");
					System.out.println("-----------------------");
					for(int i=0;i<SingletonStudent.getInstance().getStudentList().size();i++){
						System.out.println(SingletonStudent.getInstance().getStudentList().get(i).getName()+"\t"+studentresult.getStudentResult(studentresult.getTotalMarks(SingletonStudent.getInstance().getStudentList().get(i).getMarks(), 10, 20)));
					}
				}
				break;
			default:
				System.out.println("Enter a correct option");
			}
			System.out.println("********Options*******");
			System.out.println("1.Add Student\n2.List Student\n3.Get Result");
			option=in.next();
		}while(Integer.parseInt(option)<4);
	}
}
