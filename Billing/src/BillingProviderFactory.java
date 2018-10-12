
public class BillingProviderFactory {
	
	public static IBilling getProvider(String name) {
		if(name.equals("BaseBilling")) {
			return new Billing();
		}
		else if(name.equals("With subs")) {
			return new BillingWithSubs();
		}
		return null;
	}

}
