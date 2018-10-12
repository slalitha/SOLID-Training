package application.utils;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BaseBilling implements IBilling {
	



	@Override
	public double calculateBill(double cost, double tax) {
		// TODO Auto-generated method stub
		return cost + cost * tax;
	}

	@Override
	public double calculateCost(long quantity, double rate) {
		// TODO Auto-generated method stub
		return quantity * rate ;
	}
	
	
	
	

}
