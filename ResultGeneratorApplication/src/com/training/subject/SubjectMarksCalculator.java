package com.training.subject;

import com.training.constants.Constants;

public abstract class SubjectMarksCalculator extends Subject{
	protected float totalMarks;

	public SubjectMarksCalculator(float internal, float external, float attendance) {
		super(internal, external, attendance);
	}
	
	@Override
	public void calculateTotalMarks() {
		totalMarks = (float) ((0.2 * internal) + (0.75 * external) + (0.05 * attendance));
		
	}

}
