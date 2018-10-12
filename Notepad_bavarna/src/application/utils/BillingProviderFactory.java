package application.utils;

public class BillingProviderFactory {

	public static IBilling getProvider(String name) {
		if(name.equals("TimeBased")) {
			return (IBilling) new TimeBasedBilling();
		}
		else if(name.equals("With subs")) {
			return new BillingWithSubs();
		}
		else if(name.equals("Feature Based")) {
			return new FeatureBasedBilling();
		}
		else if(name.equals("With Donation")) {
			return new BillingWithDonation();
		}
		return null;
	}
}
