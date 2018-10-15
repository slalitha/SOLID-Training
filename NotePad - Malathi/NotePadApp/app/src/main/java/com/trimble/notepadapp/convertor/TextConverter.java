package com.trimble.notepadapp.convertor;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class TextConverter implements IConverter {

    private static final String TAG = "TextConverter";

    @Override
    public boolean convert(String path, String data) {
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(path);
            fileOutputStream = new FileOutputStream(file, false);
            fileOutputStream.write((data + System.getProperty("line.separator")).getBytes());
            return true;
        } catch (IOException ex) {
            Log.d(TAG, ex.getMessage());
        } finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
