package com.training.billing;

public abstract class BaseBillingWithOffer extends BaseBilling {

	protected double discountPercent;

	public void applyDiscount(double discountPercent) {
	}

	@Override
	public double calculateBill(double cost, double tax) {
		return cost + (cost * tax) - (cost * discountPercent);
	}
}
