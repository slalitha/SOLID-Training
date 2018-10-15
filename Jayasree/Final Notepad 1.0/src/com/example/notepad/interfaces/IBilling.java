package com.example.notepad.interfaces;

public interface IBilling {
	public double calculateBill(double cost,double tax);
	public double calculateCost(double quantity,double rate);
}
