package com.training.billing;

import com.training.constants.Constants.BillingOptions;

public class BillingFactoryMethod {
	
	public static IBilling getBillProvider(BillingOptions option) {
		IBilling billProvider = null; 
		switch(option) {
		case TIME:
			billProvider = new TimeBasedBilling();
			break;
		case FEATURE:
			billProvider = new FeatureBasedBilling();
			break;
		case SUBSCRIPTION:
			billProvider = new SubscriptionBasedBilling();
			break;
		case DONATION:
			billProvider = new DonationBasedBilling();
			break;
		}
		return billProvider;
	}
}
