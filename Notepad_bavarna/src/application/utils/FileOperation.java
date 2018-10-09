package application.utils;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import application.main.Notepad;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class FileOperation {
	
	Notepad notepad;

	boolean saved;
	boolean newFileFlag;
	String fileName;
	String applicationTitle="Javapad";
	
	File fileRef;
	JFileChooser chooser;
	
	public String DEST = "results/";
	
	public int pdfId = 1;
	
	public FileOperation(Notepad npd)
	{
		this.notepad=npd;

		saved=true;
		newFileFlag=true;
		fileName=new String("Untitled");
		fileRef=new File(fileName);
		this.notepad.getFrame().setTitle(fileName+" - "+applicationTitle);

		chooser=new JFileChooser();
		chooser.addChoosableFileFilter(new NotepadFileFilter(".java","Java Source Files(*.java)"));
		chooser.addChoosableFileFilter(new NotepadFileFilter(".txt","Text Files(*.txt)"));
		chooser.addChoosableFileFilter(new NotepadFileFilter(".pdf","Pdf Files(*.pdf"));
		chooser.addChoosableFileFilter(new NotepadFileFilter(".xml","XML files(*.xml)"));
		chooser.setCurrentDirectory(new File("."));

	}

	
	boolean isSave() {
		return saved;
	}
	
	public void setSave(boolean saved) {
		this.saved=saved;
	}
	
	String getFileName() {
		return new String(fileName);
	}
	
	void setFileName(String fileName) {
		this.fileName=new String(fileName);
	}
	
	boolean saveFile(File temp) {
		FileWriter fout=null;
		try {
			fout=new FileWriter(temp);
			fout.write(notepad.getTextArea().getText());
		}
		catch(IOException ioe) {
			updateStatus(temp,false);
			return false;
		}
		finally {
			try {
				fout.close();
			}catch(IOException excp){
				
			}
		}
		updateStatus(temp,true);
		return true;
	}
	
	public boolean saveThisFile() {

		if(!newFileFlag) {
			return saveFile(fileRef);
		}

		return saveAsFile();
	}
	
	public boolean saveAsFile() {
		File temp=null;
		chooser.setDialogTitle("Save As...");
		chooser.setApproveButtonText("Save Now"); 
		chooser.setApproveButtonMnemonic(KeyEvent.VK_S);
		chooser.setApproveButtonToolTipText("Click me to save!");

		do {
	
			if(chooser.showSaveDialog(this.notepad.getFrame())!=JFileChooser.APPROVE_OPTION) {
				return false;
			}
			temp=chooser.getSelectedFile();
			if(!temp.exists()) {
				break;
			}
			if(JOptionPane.showConfirmDialog(this.notepad.getFrame(),"<html>"+temp.getPath()+" already exists.<br>Do you want to replace it?<html>",
					"Save As",JOptionPane.YES_NO_OPTION
						)==JOptionPane.YES_OPTION) {
				break;
			}
		}while(true);
		
		return saveFile(temp);
	}

	boolean openFile(File temp)	{
		FileInputStream fin=null;
		BufferedReader din=null;

		try {
			fin=new FileInputStream(temp);
			din=new BufferedReader(new InputStreamReader(fin));
			String str=" ";
			while(str!=null) {
				str=din.readLine();
				if(str==null) {
					break;
				}
			this.notepad.getTextArea().append(str+"\n");
			}

		}
		catch(IOException ioe) {
			updateStatus(temp,false);return false;
		}
		finally {
			try	{
				din.close();
				fin.close();
				}catch(IOException excp){
					
				}
			}
		updateStatus(temp,true);
		this.notepad.getTextArea().setCaretPosition(0);
		return true;
	}
	
	public void openFile() {
		if(!confirmSave()) return;
		chooser.setDialogTitle("Open File...");
		chooser.setApproveButtonText("Open this"); 
		chooser.setApproveButtonMnemonic(KeyEvent.VK_O);
		chooser.setApproveButtonToolTipText("Click me to open the selected file.!");

		File temp=null;
		do {
			if(chooser.showOpenDialog(this.notepad.getFrame())!=JFileChooser.APPROVE_OPTION) {
				return;
			}
			temp=chooser.getSelectedFile();

			if(temp.exists()) {
				break;
			}

			JOptionPane.showMessageDialog(this.notepad.getFrame(),
					"<html>"+temp.getName()+"<br>file not found.<br>"+
							"Please verify the correct file name was given.<html>",
							"Open",	JOptionPane.INFORMATION_MESSAGE);

		} while(true);

		this.notepad.getTextArea().setText("");

		if(!openFile(temp)) {
			fileName="Untitled"; 
			saved=true; 
			this.notepad.getFrame().setTitle(fileName+" - "+applicationTitle);
		}
		if(!temp.canWrite()) {
			newFileFlag=true;
		}

	}
	
	void updateStatus(File temp,boolean saved) {
		if(saved) {
			this.saved=true;
			fileName=new String(temp.getName());
			if(!temp.canWrite()) {
				fileName+="(Read only)"; 
				newFileFlag=true;
			}
			fileRef=temp;
			notepad.getFrame().setTitle(fileName + " - "+applicationTitle);
			notepad.getStatusBar().setText("File : "+temp.getPath()+" saved/opened successfully.");
			newFileFlag=false;
		}
		else {
			notepad.getStatusBar().setText("Failed to save/open : "+temp.getPath());
		}
	}
	
	public boolean confirmSave() {
		String strMsg="<html>The text in the "+fileName+" file has been changed.<br>"+
		"Do you want to save the changes?<html>";
		if(!saved) {
			int x=JOptionPane.showConfirmDialog(this.notepad.getFrame(),strMsg,applicationTitle,JOptionPane.YES_NO_CANCEL_OPTION);

			if(x==JOptionPane.CANCEL_OPTION) {
				return false;
			}
			if(x==JOptionPane.YES_OPTION && !saveAsFile()) {
				return false;
			}
		}
		return true;
	}
	
	public void newFile() {
		if(!confirmSave()) {
			return;
		}

		this.notepad.getTextArea().setText("");
		fileName=new String("Untitled");
		fileRef=new File(fileName);
		saved=true;
		newFileFlag=true;
		this.notepad.getFrame().setTitle(fileName+" - "+applicationTitle);
	}
	
	public void createPdf()
			throws DocumentException, IOException {
			
		        Document document = new Document();
		        String filename =  pdfId +".pdf";
		        File file = new File(DEST);
		        //file.getParentFile().mkdirs();
		        PdfWriter.getInstance(document, new FileOutputStream(filename));
		        document.open();
		        String fileContent = notepad.getTextArea().getText();
		    	InputStream is = new ByteArrayInputStream(fileContent.getBytes());

		        BufferedReader br = new BufferedReader(new InputStreamReader(is));
		        String line;
		        Paragraph p;
		        Font normal = new Font(FontFamily.TIMES_ROMAN, 12);
		        Font bold = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		        boolean title = true;
		        while ((line = br.readLine()) != null) {
		            p = new Paragraph(line, title ? bold : normal);
		            p.setAlignment(Element.ALIGN_JUSTIFIED);
		            title = line.isEmpty();
		            document.add(p);
		        }
		        document.close();
		        JOptionPane.showMessageDialog(notepad.getFrame(),
						"Import Successful" + "\n" + "location - " + filename ,"Info",1);
		   
		        pdfId++;
		    }

	
}
