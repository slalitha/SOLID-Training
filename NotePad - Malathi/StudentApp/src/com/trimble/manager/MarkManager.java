package com.trimble.manager;

import java.util.List;

import com.trimble.model.Mark;

public class MarkManager {

	public static boolean isPass(List<Mark> markList) {

		boolean isPass = true;
		for (Mark mark : markList) {
			if (mark.getTotalMark() < 50) {
				isPass = false;
			}
		}
		return isPass;
	}

	public static boolean isValid(Mark mark) {
		
		if (mark.getmAttdanceMark() > 10 || mark.getmInternalMark() > 20 || mark.getmExternalMark() > 80) {
			return false;
		}
		return true;
	}

}
