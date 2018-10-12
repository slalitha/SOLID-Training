package com.training.billing;

public interface IBillingWithOffer extends IBilling{
	
	public void applyDiscount(double discountPercent);
}
