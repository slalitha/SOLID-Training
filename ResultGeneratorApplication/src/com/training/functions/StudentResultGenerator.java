package com.training.functions;

import com.training.constants.Constants;
import com.training.student.Student;
import com.training.subject.DecideSubjectResult;
import com.training.subject.Subject;

public class StudentResultGenerator {

	public Student generateStudentResult(String[] info) {
		Student student = new Student(Integer.parseInt(info[0]), info[1]);

		for (int i = 2; i < info.length; i += 3) {
			float internal = Float.parseFloat(info[i]);
			float external = Float.parseFloat(info[i + 1]);
			float attendance = Float.parseFloat(info[i + 2]);
			Subject subject = new DecideSubjectResult(internal, external, attendance);

			student.setFinalResult(subject.getSubjectResult());
			if (student.getFinalResult().equals(Constants.Result.FAIL)) {
				break;
			}
		}
		return student;
	}

}
