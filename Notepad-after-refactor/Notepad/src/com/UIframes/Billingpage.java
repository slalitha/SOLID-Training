package com.UIframes;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.Font;
import javax.swing.JTextPane;

import com.billinghelper.billing;
import com.function.Notepad;

import javax.swing.JButton;

public class Billingpage implements ActionListener {

	public JFrame frame;
	public Label label;
	protected JLabel lblPremiumFeatureCharges;
    public JTextPane textPane;
    protected JTextPane textPane_1;
    protected JLabel lblTotalCost;
    public JTextPane textPane_2;
    protected JButton btnOk;

	public Billingpage() {
		initialize();
	}
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
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);

		frame.setVisible(false);
		
		btnOk.addActionListener(this);
	}
	
	public void displaybill()
	{
		
		Notepad notepad = Notepad.getInstance();
		billing billcalc = new billing();
		notepad.TotatCost = billcalc.generatebill();
		// Showing total time
		notepad.bill.label.setText(notepad.bill.label.getText() + " " + billcalc.calculatetotalTime() + " sec.");
		// Showing Time based Cost
		notepad.bill.textPane.setText(new DecimalFormat("##.####").format(billcalc.timebasedcost()) + "$");
		// Showing Feature based Cost
		notepad.bill.textPane_1.setText(new DecimalFormat("##.####").format(billcalc.featurebasedcost()) + "$");
		// Showing total bill
		notepad.bill.textPane_2.setText(new DecimalFormat("##.####").format(notepad.TotatCost) + "$");
		notepad.bill.frame.setVisible(true);
		return;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnOk))
		{
			frame.dispose();
			System.exit(0);
		}
	}
}
