package com.trimble.notepadapp.convertor;

import android.util.Log;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PDFConverter implements IConverter {
    private static final String TAG = "PDFConverter";

    @Override
    public boolean convert(String path,String text) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            document.add(new Paragraph(text));
            document.close();
            return true;
        } catch (FileNotFoundException|DocumentException e) {
            Log.e(TAG, e.getMessage());
        }
        return false;
    }
}
