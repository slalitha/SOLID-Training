package com.Billingoptions;

public class billingfactory {

	public static IBilling getProvider(String option)
	{
		IBilling billoption = null;
		switch(option)
		{
		case "DONATION":
			billoption = new billingwithdonation();
			break;
		case "SUBSCRIPTION":
			billoption = new billingwithsubscription();
			break;
		case "FEATUREBASED":
			billoption = new FeaturebasedBilling();
			break;
		case "TIMEBASED":
			billoption = new TimebasedBilling();
			break;
		
		}
		return billoption;
		
	}
}
