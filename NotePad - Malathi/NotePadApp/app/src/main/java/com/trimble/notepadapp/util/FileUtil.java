package com.trimble.notepadapp.util;

import android.os.Environment;
import android.provider.DocumentsContract;
import android.util.Log;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FileUtil {

    private static final String TAG = "FileUtil";

    public static void getListOfFiles(File path, List<File> fileList) {
        File[] files = path.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String filePath = file.getPath();
                    if (filePath.endsWith(".txt"))
                        fileList.add(file);
                } else if (file.isDirectory()) {
                    getListOfFiles(file, fileList);
                }
            }
        }
    }

    public static String readFile(String path) {
        StringBuilder content = new StringBuilder();
        FileInputStream fis = null;
        DataInputStream in = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(new File(path));
            in = new DataInputStream(fis);
            br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                content.append(strLine);
            }
            in.close();
        } catch (IOException ex) {
            Log.d(TAG, ex.getMessage());
        } finally {
            try {
                if (fis != null)
                    fis.close();
                if (in != null)
                    in.close();
                if (br != null)
                    br.close();
            } catch (IOException e) {
                Log.d(TAG, e.getMessage());
            }
        }
        return content.toString();
    }
}
