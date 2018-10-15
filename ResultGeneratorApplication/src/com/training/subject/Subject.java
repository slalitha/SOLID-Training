package com.training.subject;

import com.training.constants.Constants;
import com.training.interfaces.ISubject;

public abstract class Subject implements ISubject {
	protected float internal;
	protected float external;
	protected float attendance;
	protected Constants.Result subjectResult;

	public Subject(float internal, float external, float attendance) {
		this.internal = internal;
		this.external = external;
		this.attendance = attendance;
		calculateTotalMarks();
		generateSubjectResult();
	}

	public Constants.Result getSubjectResult() {
		return subjectResult;
	}
}
