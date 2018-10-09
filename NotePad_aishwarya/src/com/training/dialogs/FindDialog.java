package com.training.dialogs;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.training.constants.Constants;

/**
 * The dialog box where user can use to search for certain words on the text.
 */
public class FindDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	// The parent frame
	private JFrame master;
	// index to search from
	private int init;
	// The text area
	private JTextArea text;
	// The text to search for
	private JTextField fText;
	// Perform case sensitive search or not
	private JCheckBox mCase;
	// The find button
	private JButton fButton;
	// The close button
	private JButton cButton;
	// Is this case sensitive search
	private boolean caseSensitive;

	/**
	 * Construct the find dialog box
	 */
	public FindDialog(JFrame master, JTextArea text) {
		super(master, "Find", false);
		this.master = master;
		this.text = text;

		Container content = getContentPane();
		content.setLayout(new BorderLayout());

		fText = new JTextField(10);
		mCase = new JCheckBox("Match Case");
		fButton = new JButton("Find Next");
		cButton = new JButton("Close");

		JPanel pane1 = new JPanel();
		pane1.add(new JLabel("Find : "));
		pane1.add(fText);
		pane1.add(mCase);
		content.add(pane1, BorderLayout.CENTER);

		JPanel pane2 = new JPanel();
		pane2.setLayout(new GridLayout(1, 2));
		pane2.add(fButton);
		pane2.add(cButton);
		content.add(pane2, BorderLayout.SOUTH);

		addListener();

		setSize(Constants.FIND_DIALOG_WIDTH, Constants.FIND_DIALOG_HEIGHT);
	}

	/**
	 * Set the search index to the beginning of the column of the text area
	 */
	public void initToZero() {
		init = 0;
	}

	/**
	 * Add needed action listener
	 */
	private void addListener() {
		mCase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mCase.isSelected())
					caseSensitive = true;
				else
					caseSensitive = false;
			}
		});
		fButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sText = fText.getText();
				if (sText != null) {
					String tSearch = text.getText();
					int size = tSearch.length();
					boolean endOfSearch = false;
					while (!endOfSearch) {
						int index = 0;
						if (caseSensitive)
							index = tSearch.indexOf(sText, init);
						else
							index = tSearch.toLowerCase().indexOf(sText.toLowerCase(), init);
						if (index != -1) {
							endOfSearch = true;
							text.select(index, index + sText.length());
							init = text.getCaretPosition();
						} else {
							endOfSearch = true;
							JOptionPane.showMessageDialog(master, "\"" + sText + "\"" + " not found ");
						}
					}
				}
			}
		});

		cButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
}