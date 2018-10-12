package application.utils;

public interface IBilling {
	
	double calculateBill(double cost,double tax);
	double calculateCost(long quantity, double rate);

}
