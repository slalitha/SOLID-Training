package application.utils;

public class FeatureBasedBilling extends BaseBillingWithOffer {

	private boolean bApplyDiscount = false;
	
	public void applyDiscount(double percentage) {
		if(bApplyDiscount) {
			super.applyDiscount(percentage);
		}
	}

}
