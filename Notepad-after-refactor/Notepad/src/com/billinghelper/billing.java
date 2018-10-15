package com.billinghelper;

import com.Billingoptions.IBilling;
import com.Billingoptions.basebillingwithoffer;
import com.Billingoptions.billingfactory;

import com.function.Notepad;
public class billing {

	billingfactory billfactory = new billingfactory();
	Notepad notepad = Notepad.getInstance();
	
	public double generatebill()
	{
		double totalBill = 0;
		double totalTime = calculatetotalTime();
		double totalUpload = notepad.totalBytes/1024.0; // Get size in KB
		totalBill += Getbilling("TIMEBASED", totalTime, 1/60.0, 0.10, 1);
		totalBill += Getbilling("FEATUREBASED", totalUpload, 1, 0.10, 1 );
		totalBill += Getbilling("DONATION", 1,1,0, 0);
		totalBill += Getbilling("SUBSCRIPTION", 1, 1, 0, 0);
	
		return totalBill;
	}

	private double Getbilling(String purpose, double quantity, double rate, double tax, double discountPercent  )
	{
		IBilling billing = billingfactory.getProvider(purpose);	
		if(billing instanceof basebillingwithoffer)
		{
			((basebillingwithoffer) billing).applydiscount(discountPercent);
		}
		return billing.calculatebill(billing.calculateCost(quantity, rate), tax);	
	}
	
	
	public double timebasedcost()
	{
		return Getbilling("TIMEBASED",  calculatetotalTime(), 1/60.0, 0.10, 1);
	}
	public double featurebasedcost()
	{
		return Getbilling("FEATUREBASED",  notepad.totalBytes/1024.0, 1, 0.10, 1);
	}
	public double calculatetotalTime()
	{
		long endTime   = System.nanoTime();
		double timespan =  (endTime - notepad.startTime)/1000000000;	
		return timespan;
	}
}