package application.utils;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BillGenerator {
	
	public static void generateBill(JFrame frame, double timeTaken) {
		IBilling billing = BillingProviderFactory.getProvider("TimeBased");
		Double cost = billing.calculateBill(billing.calculateCost((long) (timeTaken / 60.), 1), 10);
		JOptionPane.showMessageDialog(frame,
				"App Usage - " + timeTaken + " mins"
				+ '\n'+ "Amount to be paid - " + cost + " $","App Usage Statistics",1);
	}

}
