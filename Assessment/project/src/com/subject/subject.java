package com.subject;

public class subject implements Isubject {

	String name = "subject";
	double Internal = 0.0;
	double External = 0.0;
	double Attendance = 0.0;
	private double totalMarks = 0.0;
	
	public subject(String name, double internal, double external, double attendance)
	{
		this.name = name;
		this.Internal = internal;
		this.External = external;
		this.Attendance = attendance;
		this.totalMarks = this.Internal + this.External + this.Attendance;
	}
	@Override
	public double getTotalMarks() {
		return totalMarks;
	}
	
}
