package com.Billingoptions;

public abstract class basebillingwithoffer extends baseBilling {
	
	protected double discoutpercentage = 0.0;
	
	public abstract void applydiscount(double discountpercent);
	@Override
	public double calculatebill(double cost, double tax)
	{
		return cost + (cost*tax) - (cost*discoutpercentage);
	}

}
