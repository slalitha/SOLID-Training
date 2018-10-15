package com.training.subject;

import com.training.constants.Constants;

public class DecideSubjectResult  extends SubjectMarksCalculator{
	
	public DecideSubjectResult(float internal, float external, float attendance) {
		super(internal, external, attendance);
	}

	@Override
	public void generateSubjectResult() {
		if (totalMarks >= 50) {
			subjectResult = Constants.Result.PASS;
		} else {
			subjectResult = Constants.Result.FAIL;
		}
	}
}
