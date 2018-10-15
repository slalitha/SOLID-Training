package com.example.notepad.billing;

import com.example.notepad.pattern.BillingProviderFactory;

public class Billing {
	BillingProviderFactory providerFactory=new BillingProviderFactory();
	public double CalculateBill(double totalTime, int totalUpload){
		double cost=0;
        cost += GetBilling("Timebased", totalTime, 1, 10, 12);
        cost += GetBilling("Featurebased", totalUpload/1024, 1, 10, 12);
        cost += GetBilling("Withdonation", 1, 1, 0,0);
        cost += GetBilling("WithSubscription", 1, 1, 0,0);
		
		
		return cost;
	}
	private double GetBilling(String purpose, double quantity, double rate, double tax, double discountPercent)
    {
        BaseBilling basebilling = providerFactory.GetProvider(purpose);
        if (basebilling instanceof BillingOffer)
        {
            ((BillingOffer)basebilling).applyDiscount(discountPercent);
        }

        return basebilling.CalculateBill(basebilling.calculateCost(quantity, rate), tax);

    }
}
