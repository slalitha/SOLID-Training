package com.trimble.model;

public class Student {

	private static int number = 0;

	private String mName;
	private int mRollNo;
	private int[] mMarks;

	public Student(String name, int[] marks) {
		this.mRollNo = number;
		this.mName = name;
		this.mMarks = marks;
		number++;
	}

	public int getmRollno() {
		return mRollNo;
	}

	public String getmName() {
		return mName;
	}

	public int[] getmMarks() {
		return mMarks;
	}
}
