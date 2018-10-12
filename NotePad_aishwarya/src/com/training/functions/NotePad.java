package com.training.functions;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

import com.training.constants.Constants;
import com.training.dialogs.*;
import com.training.filemenufunctions.BillGenerator;
import com.training.filemenufunctions.Exit;
import com.training.filemenufunctions.New;
import com.training.filemenufunctions.Open;
import com.training.filemenufunctions.Save;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.awt.datatransfer.*;

public class NotePad extends JFrame {
	private static final long serialVersionUID = 1L;

	// static variable single_instance of type Singleton
	private static NotePad notePad = null;

	// The file chooser
	public JFileChooser chooser;
	// The text area
	public JTextArea text;
	public Font currFont;
	// The target filename
	public String fileName;
	// The clipboard used to copy/paste operations
	public Clipboard clipBoard;
	// Is there any modifications to the text area?
	public boolean askToSave;
	// File opened for the first time (not modified yet)?
	public boolean openForTheFirstTime;
	// The current directory
	public File currDir;
	// The replace dialog box
	public ReplaceDialog repDialog;
	// The dialog box to perform find operation
	public FindDialog findDialog;

	// Items of Menu Bar
	private JMenu file, edit, tool, format;
	private JMenu font, fontStyle, fontSize, trimSpaces;
	// Items of each menu.
	private JMenuItem newMenu, open, save, exit;
	private JMenuItem selectAll, copy, cut, paste;
	private JMenuItem find, replace;
	private JMenuItem font1, font2, font3, font4;
	private JMenuItem fontStyle1, fontStyle2, fontStyle3, fontStyle4;
	private JMenuItem fontSize1, fontSize2, fontSize3, fontSize4;
	private JMenuItem trimLeading, trimTrailing, trimBoth;
	public long startTime, endTime;

	private Save saveFunc;
	private Open openFunc;
	private New newFunc;
	private Exit exitFunc;
	private BillGenerator bill;

	/**
	 * Creates a frame with text area and menu bar.
	 */
	private NotePad() {
		startTime = System.currentTimeMillis();
		setSize(Constants.WIDTH, Constants.HEIGHT);
		setTitle(Constants.TITLE);

		askToSave = false;

		chooser = new JFileChooser();
		chooser.setCurrentDirectory(currDir);
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		text = new JTextArea(Constants.ROWS, Constants.COLS);
		text.setTabSize(Constants.TABSIZE);
		text.setLineWrap(true);
		currFont = new Font("TIMES New Roman", Font.PLAIN, 15);
		text.setFont(currFont);

		JPanel panel = new JPanel();
		JScrollPane pane = new JScrollPane(text);
		panel.add(pane);
		Container contentPane = getContentPane();
		contentPane.add(panel);
		clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
		currDir = new File(".");

		setMenuBar();
	}

	// static method to create instance of Singleton class
	public static NotePad getInstance() {
		if (notePad == null) {
			notePad = new NotePad();
		}
		return notePad;
	}

	/**
	 * Adding menus to the menu bar.
	 */
	private void setMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		file = new JMenu("File");
		edit = new JMenu("Edit");
		tool = new JMenu("Tool");
		format = new JMenu("Format");

		setFileMenuItems();
		setEditMenuItems();
		setToolMenuItems();
		setFormatMenuItems();

		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(tool);
		menuBar.add(format);

		addAllListeners();
	}

	/**
	 * Adds items to File Menu.
	 */
	private void setFileMenuItems() {
		newMenu = new JMenuItem("New", 'N');
		open = new JMenuItem("Open", 'O');
		save = new JMenuItem("Save As", 'S');
		exit = new JMenuItem("Exit", 'E');

		newMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

		file.add(newMenu);
		file.add(open);
		file.add(save);
		file.addSeparator();
		file.add(exit);
	}

	/**
	 * Adds items to Edit Menu.
	 */
	private void setEditMenuItems() {
		selectAll = new JMenuItem("Select All");
		copy = new JMenuItem("Copy");
		cut = new JMenuItem("Cut");
		paste = new JMenuItem("Paste");

		copy.setEnabled(false);
		cut.setEnabled(false);

		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));

		edit.add(selectAll);
		edit.addSeparator();
		edit.add(copy);
		edit.add(cut);
		edit.add(paste);
	}

	/**
	 * Adds items to Tool Menu.
	 */
	private void setToolMenuItems() {
		find = new JMenuItem("Find");
		replace = new JMenuItem("Replace");

		find.setEnabled(false);
		replace.setEnabled(false);

		tool.add(find);
		tool.add(replace);
	}

	/**
	 * Adds items to Format Menu.
	 */
	private void setFormatMenuItems() {
		font = new JMenu("Font");
		fontStyle = new JMenu("Font Style");
		fontSize = new JMenu("Font Size");
		trimSpaces = new JMenu("Trim Spaces");

		format.add(font);
		format.add(fontStyle);
		format.add(fontSize);
		format.add(trimSpaces);

		font1 = new JMenuItem("Courier");
		font2 = new JMenuItem("Sans Serif");
		font3 = new JMenuItem("Monospaced");
		font4 = new JMenuItem("Times New Roman");

		font.add(font1);
		font.add(font2);
		font.add(font3);
		font.add(font4);

		fontStyle1 = new JMenuItem("Regular");
		fontStyle2 = new JMenuItem("Bold");
		fontStyle3 = new JMenuItem("Italic");
		fontStyle4 = new JMenuItem("Bold Italic");

		fontStyle.add(fontStyle1);
		fontStyle.add(fontStyle2);
		fontStyle.add(fontStyle3);
		fontStyle.add(fontStyle4);

		fontSize1 = new JMenuItem("12");
		fontSize2 = new JMenuItem("14");
		fontSize3 = new JMenuItem("18");
		fontSize4 = new JMenuItem("20");

		fontSize.add(fontSize1);
		fontSize.add(fontSize2);
		fontSize.add(fontSize3);
		fontSize.add(fontSize4);

		trimLeading = new JMenuItem("Leading");
		trimTrailing = new JMenuItem("Trailing");
		trimBoth = new JMenuItem("Leading & Trailing");

		trimSpaces.add(trimLeading);
		trimSpaces.add(trimTrailing);
		trimSpaces.add(trimBoth);
	}

	/**
	 * Add the listeners to each menu. Also adding the document listener for the
	 * text area to detect changes in the document
	 */
	private void addAllListeners() {
		setFunctionObjects();
		addWinListener();
		addFileMenuListener();
		addEditMenuListener();
		addToolMenuListener();
		addFontMenuListener();
		addFontStyleMenuListener();
		addFontSizeMenuListener();
		addTrimSpacesListener();
		DocumentListener listener = new textListener();
		text.getDocument().addDocumentListener(listener);
	}

	private void setFunctionObjects() {
		saveFunc = new Save();
		openFunc = new Open();
		newFunc = new New();
		exitFunc = new Exit();
		bill = new BillGenerator();
	}

	/**
	 * Add the window listener
	 */
	private void addWinListener() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitFunc.exitFile();
				double totalBill = bill.getTotalBill();
			}
		});
	}

	/**
	 * Adds listener to each item of File menu.
	 */
	private void addFileMenuListener() {
		newMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newFunc.newFile();
			}
		});
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFunc.openFile();
			}
		});
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFunc.saveCurrFile();
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitFunc.exitFile();
				double totalBill = bill.getTotalBill();
			}
		});
	}

	/**
	 * Adds listener to each item of Edit Menu.
	 */
	private void addEditMenuListener() {
		selectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.selectAll();
			}
		});
		copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selText = text.getSelectedText();
				if (selText != null) {
					StringSelection text = new StringSelection(selText);
					clipBoard.setContents(text, null);
				}
			}
		});
		cut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getSelectedText() != null) {
					int begin = text.getSelectionStart();
					int end = text.getSelectionEnd();
					String selText = text.getSelectedText();
					StringSelection selectedtext = new StringSelection(selText);
					clipBoard.setContents(selectedtext, null);
					text.replaceRange("", begin, end);
				}
			}
		});
		paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = text.getCaretPosition();
				Transferable contents = clipBoard.getContents(this);
				if (contents != null) {
					try {
						String selText = (String) (contents.getTransferData(DataFlavor.stringFlavor));
						text.insert(selText, pos);
					} catch (Exception c) {
					}
				}
			}
		});
	}

	/**
	 * Adds listeners to each item of Tool Menu.
	 */
	private void addToolMenuListener() {
		find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findDialog = new FindDialog(NotePad.this, text);
				findDialog.setVisible(true);
			}
		});
		replace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repDialog = new ReplaceDialog(NotePad.this, text);
				repDialog.setVisible(true);
			}
		});
	}

	/**
	 * Adds listeners to each item of Font Menu.
	 */
	private void addFontMenuListener() {
		font1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currFont = new Font("Courier", currFont.getStyle(), currFont.getSize());
				text.setFont(currFont);
			}
		});
		font2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currFont = new Font("Sans Serif", currFont.getStyle(), currFont.getSize());
				text.setFont(currFont);
			}
		});
		font3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currFont = new Font("Monospaced", currFont.getStyle(), currFont.getSize());
				text.setFont(currFont);
			}
		});
		font4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currFont = new Font("Times New Roman", currFont.getStyle(), currFont.getSize());
				text.setFont(currFont);
			}
		});
	}

	/**
	 * Adds listeners to each item of Font Style Menu.
	 */
	private void addFontStyleMenuListener() {
		fontStyle1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currFont = new Font(currFont.getFontName(), Font.PLAIN, currFont.getSize());
				text.setFont(currFont);
			}
		});
		fontStyle2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currFont = new Font(currFont.getFontName(), Font.BOLD, currFont.getSize());
				text.setFont(currFont);
			}
		});
		fontStyle3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currFont = new Font(currFont.getFontName(), Font.ITALIC, currFont.getSize());
				text.setFont(currFont);
			}
		});
		fontStyle4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currFont = new Font(currFont.getFontName(), Font.BOLD | Font.ITALIC, currFont.getSize());
				text.setFont(currFont);
			}
		});
	}

	/**
	 * Adds listeners to each item of Font Size Menu.
	 */
	private void addFontSizeMenuListener() {
		fontSize1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currFont = new Font(currFont.getFontName(), currFont.getStyle(), 12);
				text.setFont(currFont);
			}
		});
		fontSize2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currFont = new Font(currFont.getFontName(), currFont.getStyle(), 14);
				text.setFont(currFont);
			}
		});
		fontSize3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currFont = new Font(currFont.getFontName(), currFont.getStyle(), 18);
				text.setFont(currFont);
			}
		});
		fontSize4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currFont = new Font(currFont.getFontName(), currFont.getStyle(), 20);
				text.setFont(currFont);
			}
		});
	}

	/**
	 * Adds listeners to each item of Trim Spaces Menu.
	 */
	private void addTrimSpacesListener() {
		trimLeading.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (String line : text.getText().split("\\n")) {
					int i = 0;
					while (i < line.length() && Character.isWhitespace(line.charAt(i))) {
						i++;
					}
					text.setText(line.substring(i, line.length()));
				}
			}
		});
		trimTrailing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (String line : text.getText().split("\\n")) {
					int i = line.length() - 1;
					while (i > 0 && Character.isWhitespace(line.charAt(i))) {
						i--;
					}
					text.setText(line.substring(0, i + 1));
				}
			}
		});
		trimBoth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (String line : text.getText().split("\\n")) {
					line = line.trim();
					text.setText(line);
				}
			}
		});
	}

	/**
	 * Ask user whether they want to save the texts to a file before exiting
	 */
	private void saveBeforeExit() {
		text.selectAll();
		if (text.getSelectedText() != null && askToSave) {
			int select = JOptionPane.showConfirmDialog(NotePad.this, "Save the texts to a file before exiting?",
					"JavaNotePad", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if (select == JOptionPane.YES_OPTION) {
				int result = chooser.showSaveDialog(NotePad.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					fileName = chooser.getSelectedFile().getPath();
					saveToFile(fileName);
				}
			} else if (select == JOptionPane.NO_OPTION)
				System.exit(0);
			else if (select == JOptionPane.CANCEL_OPTION)
				text.setCaretPosition(0);
		} else
			System.exit(0);
	}

	/**
	 * Attempt to save the contents of the text area to a file
	 */
	private void saveToFile(String filename) {
		writeToFile(filename);
		setTitle(filename);
	}

	/**
	 * Open a requested file and display the content of file in the text area
	 */
	private void putTextIntoTextArea() {
		try {
			FileReader file = new FileReader(fileName);
			BufferedReader in = new BufferedReader(file);
			boolean done = false;
			while (!done) {
				String line = in.readLine();
				if (line == null)
					done = true;
				else
					text.append(line + "\n");
			}
		} catch (FileNotFoundException e) {
			text.append(fileName + " not found \n");
		} catch (IOException e) {
			text.append("Error when trying to read " + fileName + " \n");
		}
	}

	/**
	 * Save the contents of the text area to a file
	 */
	private void writeToFile(String filename) {
		try {
			text.selectAll();
			String input = text.getSelectedText();
			PrintWriter out = new PrintWriter(new FileOutputStream(filename, false));
			int start = 0;
			String temp = "";
			String chars = "";
			if (input.indexOf("\n") == -1)
				out.println(input);
			else {
				for (int i = 0; i < input.length(); i++) {
					chars = chars + input.charAt(i);
					if (chars.equals("\n")) {
						out.println(temp);
						start = i + 1;
						temp = "";
					} else
						temp = temp + input.charAt(i);
					chars = "";
				}
			}
			out.close();
		} catch (IOException f) {
		}
	}

	/**
	 * Uploads the current file to cloud storage
	 */
	private void saveToCloud(String filename) {

	}

	/**
	 * The listener class for the text area
	 */

	private class textListener implements DocumentListener {
		/**
		 * This method is invoked whenever something is inserted into the text area
		 */
		public void insertUpdate(DocumentEvent e) {
			if (openForTheFirstTime)
				openForTheFirstTime = false;
			askToSave = true;
			copy.setEnabled(true);
			cut.setEnabled(true);
			find.setEnabled(true);
			replace.setEnabled(true);
		}

		/**
		 * This method is invoked whenever something is inserted into the text area
		 */
		public void removeUpdate(DocumentEvent e) {
			askToSave = true;
			if (text.getText().equals("")) {
				copy.setEnabled(false);
				cut.setEnabled(false);
				find.setEnabled(false);
				replace.setEnabled(false);
			}
		}

		/**
		 * Not used
		 */
		public void changedUpdate(DocumentEvent e) {
		}
	}
}
