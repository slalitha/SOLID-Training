package com.training.interfaces;

import com.training.constants.Constants;

public interface IStudent {

	public int getRollNo();

	public String getName();

	public void setFinalResult(Constants.Result finalResult);

	public Constants.Result getFinalResult();
}
