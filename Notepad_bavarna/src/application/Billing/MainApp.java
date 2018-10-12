
public class MainApp {
	
	public static void main(String[] args) {
		IBilling billing = BillingProviderFactory.getProvider("BaseBilling");
		System.out.println(billing.calculateBill());
		
	}

}
