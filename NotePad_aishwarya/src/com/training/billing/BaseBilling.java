package com.training.billing;

public abstract class BaseBilling  implements IBilling{

	@Override
	public double calculateBill(double cost, double tax) {
		// TODO Auto-generated method stub
		cost += cost * tax;
		return cost;
	}

	@Override
	public double calculateCost(double quantity, double rate) {
		return quantity * rate;
	}
	

}
