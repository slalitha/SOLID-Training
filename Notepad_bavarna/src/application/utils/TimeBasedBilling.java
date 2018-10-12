package application.utils;

import java.util.concurrent.TimeUnit;

public class TimeBasedBilling extends BaseBillingWithOffer{
	
private boolean bApplyDiscount = false;
	
	public void applyDiscount(double percentage) {
		if(bApplyDiscount) {
			super.applyDiscount(percentage);
		}
	}
}
