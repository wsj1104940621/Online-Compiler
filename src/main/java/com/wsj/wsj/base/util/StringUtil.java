package com.wsj.wsj.base.util;

public class StringUtil {
    public static String trimRight(String text, String assign) {
        if (text == null) return null;
        if (text.endsWith(assign)) {
            text = text.substring(0, text.length() - 1);
            trimRight(text, assign);
        }
        return text;
    }

    public static boolean isEmpty(String text) {
        return text == null || text.trim().equals("");
    }
}
