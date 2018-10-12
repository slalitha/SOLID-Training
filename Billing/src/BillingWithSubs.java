
public class BillingWithSubs extends Billing{
	
	int subs;
	
	public double calculateBill() {
		return cost+subs;
	}
}
