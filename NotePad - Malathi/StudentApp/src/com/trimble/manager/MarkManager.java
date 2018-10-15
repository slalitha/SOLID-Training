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
}
