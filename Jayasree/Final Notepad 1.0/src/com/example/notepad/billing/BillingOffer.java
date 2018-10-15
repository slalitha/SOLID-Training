package com.example.notepad.billing;

public abstract class BillingOffer extends BaseBilling{
	protected double discountPercentage;
    public abstract void applyDiscount(double discountPercentage);

    public double CalculateBill(double cost, double tax)
    {
        return cost + (cost * tax) - (cost * discountPercentage);
    }
}
