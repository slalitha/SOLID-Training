package com.trimble.notepadapp.convertor;


import com.trimble.notepadapp.activity.NewActivity;

public class Converter {

    public static IConverter getInstance(NewActivity.FileType fileType) {

        if (fileType == NewActivity.FileType.PDF) {
            return new PDFConverter();
        } else {
            return new TextConverter();
        }
    }
}
