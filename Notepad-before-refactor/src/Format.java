import javax.swing.JFrame;
import javax.swing.JButton;

public class Format {

	protected JFrame frame;
    protected JButton btnNewButton;
    protected JButton btnNewButton_1;
    protected JButton btnNewButton_2;
    protected JButton btnSplitWords;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */
	public Format() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 406, 189);
		frame.setTitle("Formatting");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnNewButton = new JButton("Trim Leading Spaces");
		btnNewButton.setBounds(10, 11, 150, 31);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Trim Trailing Spaces");
		btnNewButton_1.setBounds(10, 53, 150, 31);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Trim Leading and Trailing Spaces");
		btnNewButton_2.setBounds(10, 95, 197, 31);
		frame.getContentPane().add(btnNewButton_2);
		
		btnSplitWords = new JButton("Split Words");
		btnSplitWords.setBounds(258, 13, 107, 27);
		frame.getContentPane().add(btnSplitWords);
		frame.setVisible(false);
	}
	
	
}
