package application.utils;

public abstract class BaseBillingWithOffer extends BaseBilling{
	
	protected double discountPercentage;

	public void applyDiscount(double percentage) {
		this.discountPercentage = percentage;
	}
	
	public double calculateBill(double cost, double tax) {
		return cost + (cost * tax ) - (cost * discountPercentage);
	}

}
