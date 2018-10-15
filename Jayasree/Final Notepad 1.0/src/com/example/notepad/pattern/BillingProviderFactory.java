package com.example.notepad.pattern;


import com.example.notepad.billing.BaseBilling;
import com.example.notepad.billing.BillingWithDonation;
import com.example.notepad.billing.BillingWithSubscription;
import com.example.notepad.billing.TimeBasedBilling;
import com.example.notepad.billing.FeatureBasedBilling;

public class BillingProviderFactory {
	public BaseBilling GetProvider(String purpose){
		switch(purpose){
		case "Withdonation":
			return new BillingWithDonation();
		case "WithSubscription":
			return new BillingWithSubscription();
		case "Timebased":
			return new TimeBasedBilling();
		case "Featurebased":
			return new FeatureBasedBilling();
		}
		return null;
	}
}
