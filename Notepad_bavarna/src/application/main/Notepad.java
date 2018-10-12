package application.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.itextpdf.text.DocumentException;

import application.utils.BillingProviderFactory;
import application.utils.BaseBilling;
import application.utils.BillGenerator;
import application.utils.FileOperation;
import application.utils.FindDialog;
import application.utils.IBilling;

public class Notepad implements ActionListener, application.constants.MenuConstants {
	
	JFrame frame;
	JTextArea textArea;
	JLabel statusBar;

	private String fileName="Untitled";
	String applicationName="Javapad";

	String searchString, replaceString;
	int lastSearchIndex;
	FileOperation fileHandler;
	FindDialog findReplaceDialog=null; 
	
	JDialog backgroundDialog=null;
	JDialog foregroundDialog=null;
	JMenuItem cutItem,copyItem, deleteItem, findItem, findNextItem, replaceItem, selectAllItem;
	
	long startTime = System.currentTimeMillis();
	
	long endTime;
	BaseBilling billing = null;

	Notepad() {
		frame = new JFrame(fileName+" - "+applicationName);
		textArea = new JTextArea(30,60);
		statusBar=new JLabel("||       Ln 1, Col 1  ",JLabel.RIGHT);
		frame.add(new JScrollPane(textArea),BorderLayout.CENTER);
		frame.add(statusBar,BorderLayout.SOUTH);
		frame.add(new JLabel("  "),BorderLayout.EAST);
		frame.add(new JLabel("  "),BorderLayout.WEST);
		createMenuBar(frame);
		frame.pack();
		frame.setLocation(100,50);
		frame.setVisible(true);
		frame.setLocation(150,50);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		fileHandler=new FileOperation(this);
		
		billing = new BaseBilling();
		
		textArea.addCaretListener(
		new CaretListener() {
			public void caretUpdate(CaretEvent e)
			{
				int lineNumber=0, column=0, pos=0;

				try {
					pos=textArea.getCaretPosition();
					lineNumber=textArea.getLineOfOffset(pos);
					column=pos-textArea.getLineStartOffset(lineNumber);
				}catch(Exception excp)	{}
				if(textArea.getText().length()==0){lineNumber=0; column=0;}
				statusBar.setText("||       Ln "+(lineNumber+1)+", Col "+(column+1));
			}
		});
	DocumentListener myListener = new DocumentListener() {
		public void changedUpdate(DocumentEvent e){fileHandler.setSave(false);}
		public void removeUpdate(DocumentEvent e){fileHandler.setSave(false);}
		public void insertUpdate(DocumentEvent e){fileHandler.setSave(false);}
	};
		textArea.getDocument().addDocumentListener(myListener);
	/////////
		WindowListener frameClose=new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
	{
	if(fileHandler.confirmSave())System.exit(0);
	}
	};
	frame.addWindowListener(frameClose);
	
	}

	public JFrame getFrame() {
		return frame;
	} 
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public JLabel getStatusBar() {
		return statusBar;
	}
	
	
	public void actionPerformed(ActionEvent ev)
	{
		String cmdText=ev.getActionCommand();
		if(cmdText.equals(fileNew)) {
			fileHandler.newFile();
		}
		else if(cmdText.equals(fileOpen)) {
			fileHandler.openFile();
		}
		else if(cmdText.equals(fileSave)) {
			fileHandler.saveThisFile();
		}
		else if(cmdText.equals(fileSaveAs)) {
			fileHandler.saveAsFile();
		}
		else if(cmdText.equals(fileExit)) {
			endTime = System.currentTimeMillis();
			double timeTaken = TimeUnit.MILLISECONDS.toMinutes(endTime-startTime);
//			JOptionPane.showMessageDialog(frame,
//					"App Usage - " + timeTaken + " mins"
//					+ '\n'+ "Amount to be paid - " + ((timeTaken / 60 ) * 1) + " $","App Usage Statistics",1);
			BillGenerator.generateBill(frame, timeTaken);
			if(fileHandler.confirmSave()) {
				System.exit(0);
			}
		}
		else if(cmdText.equals(editCut)) {
			textArea.cut();
		}
		else if(cmdText.equals(editCopy)) {
			textArea.copy();
		}
		else if(cmdText.equals(editPaste)) {
			textArea.paste();
		}
		else if(cmdText.equals(editDelete)) {
			textArea.replaceSelection("");
		}
		else if(cmdText.equals(editFind)) {
			if(Notepad.this.textArea.getText().length()==0) {
				return;	// text box have no text
			}
			if(findReplaceDialog==null) {
				findReplaceDialog=new FindDialog(Notepad.this.textArea);
			}
			findReplaceDialog.showDialog(Notepad.this.frame,true);//find
		}
		else if(cmdText.equals(editFindNext)) {
			if(Notepad.this.textArea.getText().length()==0) {
				return;	// text box have no text
			}

			if(findReplaceDialog==null) {
				statusBar.setText("Nothing to search for, use Find option of Edit Menu first !!!!");
			}
			else {
				findReplaceDialog.findNextWithSelection();
			}
		}
		else if(cmdText.equals(editReplace)) {
			if(Notepad.this.textArea.getText().length()==0) {
				return;	// text box have no text
			}

			if(findReplaceDialog==null) {
				findReplaceDialog=new FindDialog(Notepad.this.textArea);
			}
			findReplaceDialog.showDialog(Notepad.this.frame,false);//replace
		}
		else if(cmdText.equals(editSelectAll)) {
			textArea.selectAll();
		}
		else if(cmdText.equals(split_words)) {
			String text = textArea.getText();
			text = text.replaceAll("\\s+", "\n");
			textArea.removeAll();
			textArea.setText(text);
		}
		else if(cmdText.equals(trim_both)) {
			String text = textArea.getText();
			text = text.replaceAll("\\s", " ");
			textArea.removeAll();
			textArea.setText(text);
		}
		else if(cmdText.equals(trim_leading)) {
			String regex = "^\\\\s+";
			String text = textArea.getText();
			text = text.replaceAll(regex, "\n");
			textArea.removeAll();
			textArea.setText(text);
		}
		else if(cmdText.equals(trim_trailing)) {
			String text = textArea.getText();
			text = removeLeadingSpaces(text);
			textArea.removeAll();
			textArea.setText(text);
		}
		else if(cmdText.equals(pdf)) {
			fileHandler.createPdf();
		}
	}
	
	JMenuItem createMenuItem(String s, int key,JMenu toMenu,ActionListener al) {
		JMenuItem temp=new JMenuItem(s,key);
		temp.addActionListener(al);
		toMenu.add(temp);

		return temp;
	}
	
	JMenuItem createMenuItem(String s, int key,JMenu toMenu,int aclKey,ActionListener al) {
		JMenuItem temp=new JMenuItem(s,key);
		temp.addActionListener(al);
		temp.setAccelerator(KeyStroke.getKeyStroke(aclKey,ActionEvent.CTRL_MASK));
		toMenu.add(temp);

		return temp;
	}
	
	JCheckBoxMenuItem createCheckBoxMenuItem(String s, int key,JMenu toMenu,ActionListener al)
	{
		JCheckBoxMenuItem temp=new JCheckBoxMenuItem(s);
		temp.setMnemonic(key);
		temp.addActionListener(al);
		temp.setSelected(false);
		toMenu.add(temp);

		return temp;
	}
	
	JMenu createMenu(String s,int key,JMenuBar toMenuBar) {
		JMenu temp=new JMenu(s);
		temp.setMnemonic(key);
		toMenuBar.add(temp);
		return temp;
	}
	
	/*********************************/
	void createMenuBar(JFrame f)
	{
		JMenuBar mb=new JMenuBar();
		JMenuItem temp;

		JMenu fileMenu=createMenu(fileText,KeyEvent.VK_F,mb);
		JMenu editMenu=createMenu(editText,KeyEvent.VK_E,mb);
		
		createMenuItem(fileNew,KeyEvent.VK_N,fileMenu,KeyEvent.VK_N,this);
		createMenuItem(fileOpen,KeyEvent.VK_O,fileMenu,KeyEvent.VK_O,this);
		createMenuItem(fileSave,KeyEvent.VK_S,fileMenu,KeyEvent.VK_S,this);
		createMenuItem(fileSaveAs,KeyEvent.VK_A,fileMenu,this);
		fileMenu.addSeparator();
		createMenuItem(fileExit,KeyEvent.VK_X,fileMenu,this);
	
		temp=createMenuItem(editUndo,KeyEvent.VK_U,editMenu,KeyEvent.VK_Z,this);
		temp.setEnabled(false);
		editMenu.addSeparator();
		cutItem=createMenuItem(editCut,KeyEvent.VK_T,editMenu,KeyEvent.VK_X,this);
		copyItem=createMenuItem(editCopy,KeyEvent.VK_C,editMenu,KeyEvent.VK_C,this);
		createMenuItem(editPaste,KeyEvent.VK_P,editMenu,KeyEvent.VK_V,this);
		deleteItem=createMenuItem(editDelete,KeyEvent.VK_L,editMenu,this);
		deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));
		editMenu.addSeparator();
		findItem=createMenuItem(editFind,KeyEvent.VK_F,editMenu,KeyEvent.VK_F,this);
		findNextItem=createMenuItem(editFindNext,KeyEvent.VK_N,editMenu,this);
		findNextItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
		replaceItem=createMenuItem(editReplace,KeyEvent.VK_R,editMenu,KeyEvent.VK_H,this);
		selectAllItem=createMenuItem(editSelectAll,KeyEvent.VK_A,editMenu,KeyEvent.VK_A,this);
		
		JMenu formatMenu = createMenu(format,KeyEvent.VK_F1,mb);
		createMenuItem(trim_leading,KeyEvent.KEY_FIRST,formatMenu,this);
		createMenuItem(trim_trailing,KeyEvent.KEY_FIRST,formatMenu,this);
		createMenuItem(trim_both,KeyEvent.KEY_FIRST,formatMenu,this);
		createMenuItem(split_words,KeyEvent.KEY_FIRST,formatMenu,this);
	
		JMenu otherMenu = createMenu(others,KeyEvent.KEY_LAST,mb);
		createMenuItem(store_to_cloud,KeyEvent.KEY_TYPED,otherMenu,this);
		
		JMenu importMenu = createMenu(export,KeyEvent.KEY_LOCATION_NUMPAD,mb);
		createMenuItem(pdf,KeyEvent.KEY_TYPED,importMenu,this);

		MenuListener editMenuListener=new MenuListener() {
		   public void menuSelected(MenuEvent evvvv) {
			   if(Notepad.this.textArea.getText().length()==0) {
				   findItem.setEnabled(false);
				   findNextItem.setEnabled(false);
				   replaceItem.setEnabled(false);
				   selectAllItem.setEnabled(false);
			   }
			   else {
				   findItem.setEnabled(true);
				   findNextItem.setEnabled(true);
				   replaceItem.setEnabled(true);
				   selectAllItem.setEnabled(true);
			  }
			   if(Notepad.this.textArea.getSelectionStart()==textArea.getSelectionEnd()) {
				   cutItem.setEnabled(false);
				   copyItem.setEnabled(false);
				   deleteItem.setEnabled(false);
			   }
			   else	{
				   cutItem.setEnabled(true);
				   copyItem.setEnabled(true);
				   deleteItem.setEnabled(true);
			   }
			}
		   public void menuDeselected(MenuEvent evvvv){}
		   public void menuCanceled(MenuEvent evvvv){}
		};
		editMenu.addMenuListener(editMenuListener);
		f.setJMenuBar(mb);

	}
	
	////////////////////////////////////
	public static void main(String[] s) {
		new Notepad();
	}
	
	public static String removeLeadingSpaces(String param)
    {
        if (param == null) {
            return null;
        }
         
        if(param.isEmpty()) {
            return "";
        }
         
        int arrayIndex = 0;
        while(true)
        {
            if (!Character.isWhitespace(param.charAt(arrayIndex++))) {
                break;
            }
        }
        return param.substring(arrayIndex-1);
    }

}
