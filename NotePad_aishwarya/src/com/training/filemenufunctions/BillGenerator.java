package com.training.filemenufunctions;

import java.util.concurrent.TimeUnit;

import com.training.billing.BillingFactoryMethod;
import com.training.billing.IBilling;
import com.training.constants.Constants;
import com.training.functions.NotePad;

public class BillGenerator {
	
	public double getTotalBill() {
		IBilling billProvider = BillingFactoryMethod.getBillProvider(Constants.BillingOptions.TIME);
		double totalBill = 0;
		
		totalBill += billProvider.calculateBill(billProvider.calculateCost(calculateTotalDuration(), 1), 10);
		
		return totalBill;
	}
	
	
	
	private double calculateTotalDuration() {
		NotePad notePad = NotePad.getInstance();
		notePad.endTime = System.currentTimeMillis();
		double duration = TimeUnit.MILLISECONDS.toMinutes(notePad.endTime-notePad.startTime);
		return duration;
	}
}
