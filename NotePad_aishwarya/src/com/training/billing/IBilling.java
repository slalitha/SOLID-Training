package com.training.billing;

public interface IBilling {
	
	double calculateBill(double cost, double tax);
	
	double calculateCost(double quantity, double rate);

}
