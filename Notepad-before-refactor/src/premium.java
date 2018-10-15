import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Checkbox;

public class premium {

	private JFrame frmCharges;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					premium window = new premium();
					window.frmCharges.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public premium() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCharges = new JFrame();
		frmCharges.setTitle("Charges");
		frmCharges.setBounds(100, 100, 449, 300);
		frmCharges.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCharges.getContentPane().setLayout(null);
		
		JLabel lblChargesBasedOn = new JLabel("Charges based on usage time : 1$ per hour");
		lblChargesBasedOn.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblChargesBasedOn.setBounds(10, 45, 380, 39);
		frmCharges.getContentPane().add(lblChargesBasedOn);
		
		JLabel lblChargesForPremium = new JLabel("Charges for premium feature : 1$ per 10 KB");
		lblChargesForPremium.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblChargesForPremium.setBounds(10, 108, 380, 28);
		frmCharges.getContentPane().add(lblChargesForPremium);
		
		Checkbox checkbox = new Checkbox("Check that box, if you want to access premium features.*");
		checkbox.setFont(new Font("Arial", Font.PLAIN, 10));
		checkbox.setBounds(10, 176, 380, 33);
		frmCharges.getContentPane().add(checkbox);
	}
}
