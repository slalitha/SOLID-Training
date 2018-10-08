package com.trimble.notepadapp.util;



public class StringUtil {
    public static String removeLeadingSpace(String text) {
        if (text == null)
            return null;
        int len;
        for (len = 0; len < text.length(); len++) {
            if (!Character.isWhitespace(text.charAt(len)))
                break;
        }
        return text.substring(len, text.length());
    }

    public static String removeTrailingSpace(String text) {
        if (text == null)
            return null;
        int len;
        for (len = text.length(); len > 0; len--) {
            if (!Character.isWhitespace(text.charAt(len - 1)))
                break;
        }
        return text.substring(0, len);
    }

    public static String removeLeadingAndTrailingSpace(String text){
        return text.trim();
    }

    public static String splitByLine(String text){
        return text.replaceAll(" ", "\n");
    }
}
