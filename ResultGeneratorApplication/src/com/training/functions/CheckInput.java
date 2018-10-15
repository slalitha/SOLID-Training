package com.training.functions;

public class CheckInput {
	
	public boolean checkRollNo(String input) {
		try {
			int in = Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkSubjectMarks(String[] info) {
		if (((info.length - 2) % 3) == 0) {
			for (int i = 2; i < info.length; i += 1) {
				try {
					float in = Float.parseFloat(info[i]);
					if (in < 0 || in > 100) {
						return false;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

}
