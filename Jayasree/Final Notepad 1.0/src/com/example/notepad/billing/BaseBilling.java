package com.example.notepad.billing;


public abstract class BaseBilling{
	public double CalculateBill(double cost,double tax){
		return cost+(cost*tax);
	}
	public double calculateCost(double quantity, double rate) {
		return (quantity*rate);
	}

}
