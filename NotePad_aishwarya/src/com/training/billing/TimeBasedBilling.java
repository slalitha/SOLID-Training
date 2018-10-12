package com.training.billing;

public class TimeBasedBilling extends BaseBillingWithOffer{
	
	private boolean canApplyDiscount = false;
	@Override
	public void applyDiscount(double discountPercent) {
		// TODO Auto-generated method stub
		if(canApplyDiscount) {
			super.discountPercent = discountPercent;
		}
		
	}
}
