import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class find {

	protected JFrame frmFind;
	protected JTextField textFindField;
	protected JButton btnFindFindNext;
	protected JCheckBox chckbxCaseInsensitive;
	/**
	 * Launch the application.
	 */
	
	

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public find() {
		initializwe();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initializwe() {
		frmFind = new JFrame();
		frmFind.setTitle("Find");
		frmFind.setBounds(100, 100, 392, 126);
		frmFind.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmFind.getContentPane().setLayout(null);
	
		JLabel lblFindWhat = new JLabel("Find what :");
		lblFindWhat.setBounds(10, 26, 79, 14);
		frmFind.getContentPane().add(lblFindWhat);
		
		textFindField = new JTextField();
		textFindField.setBounds(85, 23, 146, 20);
		frmFind.getContentPane().add(textFindField);
		textFindField.setColumns(10);
		
		btnFindFindNext = new JButton("Find Next");
		btnFindFindNext.setBounds(278, 22, 89, 23);
		frmFind.getContentPane().add(btnFindFindNext);
		
		chckbxCaseInsensitive = new JCheckBox("Case insensitive");
		chckbxCaseInsensitive.setBounds(85, 57, 146, 23);
		chckbxCaseInsensitive.setSelected(false);
		frmFind.getContentPane().add(chckbxCaseInsensitive);
		frmFind.setVisible(false);
		
	}
}
