import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class Billingpage {

	protected JFrame frame;
	protected Label label;
	protected JLabel lblPremiumFeatureCharges;
    protected JTextPane textPane;
    protected JTextPane textPane_1;
    protected JLabel lblTotalCost;
    protected JTextPane textPane_2;
    protected JButton btnOk;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public Billingpage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 435, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label = new Label("Used for");
		label.setFont(new Font("Arial Black", Font.ITALIC, 15));
		label.setBounds(10, 38, 180, 36);
		frame.getContentPane().add(label);
		
		lblPremiumFeatureCharges = new JLabel("Premium Feature Charges ");
		lblPremiumFeatureCharges.setFont(new Font("Arial", Font.ITALIC, 15));
		lblPremiumFeatureCharges.setBounds(10, 99, 195, 36);
		frame.getContentPane().add(lblPremiumFeatureCharges);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setText("NIL");
		textPane.setBounds(218, 54, 55, 20);
		frame.getContentPane().add(textPane);
		
		textPane_1 = new JTextPane();
		textPane_1.setText("NIL");
		textPane_1.setEditable(false);
		textPane_1.setBounds(218, 109, 55, 20);
		frame.getContentPane().add(textPane_1);
		
		lblTotalCost = new JLabel("Total Cost");
		lblTotalCost.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 15));
		lblTotalCost.setBounds(47, 166, 149, 58);
		frame.getContentPane().add(lblTotalCost);
		
		textPane_2 = new JTextPane();
		textPane_2.setText("NIL");
		textPane_2.setEditable(false);
		textPane_2.setBounds(218, 187, 55, 20);
		frame.getContentPane().add(textPane_2);
		
		btnOk = new JButton("OK.");
		btnOk.setBounds(166, 227, 89, 23);
		frame.getContentPane().add(btnOk);
		
		frame.setVisible(false);
	}
}
