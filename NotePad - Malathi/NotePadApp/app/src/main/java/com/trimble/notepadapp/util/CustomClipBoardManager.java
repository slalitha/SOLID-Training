package com.trimble.notepadapp.util;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.widget.EditText;

import com.trimble.notepadapp.R;

/**
 * Created by mbommi on 10/9/2018.
 */

public class CustomClipBoardManager {

    public static void copyToClipBoard(EditText etContent, Context context) {
        int startSelection = etContent.getSelectionStart();
        int endSelection = etContent.getSelectionEnd();

        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(etContent.getText().toString().substring(startSelection, endSelection),
                etContent.getText().toString().substring(startSelection, endSelection));
        clipboardManager.setPrimaryClip(clip);
    }

    public static boolean pasteClipBoardContent(EditText etContent,Context context){
        int startSelection1 = etContent.getSelectionStart();
        int endSelection1 = etContent.getSelectionEnd();

        ClipboardManager clipboardManager1 = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (!(clipboardManager1.hasPrimaryClip())) {
          return false;
        } else if (!(clipboardManager1.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))) {
            return false;
        } else {
            ClipData.Item item = clipboardManager1.getPrimaryClip().getItemAt(0);
            String pasteData = item.getText().toString();
            String previousData = etContent.getText().toString().substring(0, startSelection1);
            String nextData = etContent.getText().toString().substring(endSelection1);
            etContent.setText(previousData + pasteData + nextData);
            return true;
        }
    }
}
