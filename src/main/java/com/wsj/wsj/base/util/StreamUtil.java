package com.wsj.wsj.base.util;

import java.io.*;

public class StreamUtil {
    public static String InputStreamToString(InputStream is, String charset) throws IOException {
        if (is == null) return "";
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        String str = result.toString(charset);
        if (result != null) result.close();
        if (is != null) is.close();
        return str;
    }

    public static InputStream StringToInputStream(String str) {
        if (str == null) return null;
        return new ByteArrayInputStream(str.getBytes());
    }

    public static OutputStream StringToOutputStream(String str) throws IOException {
        if (str == null) return null;
        OutputStream os = System.out;
        os.write(str.getBytes());
        return os;
    }

    public static String OutputStreamToString(OutputStream os, String charset) throws IOException {
        InputStream is = OutputToInput(os);
        os.close();
        return InputStreamToString(is, charset);
    }

    public static InputStream OutputToInput(OutputStream os) throws IOException {
        if (os == null) return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = (ByteArrayOutputStream) os;
        if (baos != null) baos.close();
        if (os != null) os.close();
        return new ByteArrayInputStream(baos.toByteArray());
    }

    public static OutputStream InputToOutput(InputStream is) throws IOException {
        if (is == null) return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int ch;
        while ((ch = is.read()) != -1) {
            baos.write(ch);
        }
        baos.flush();
        if (is != null) is.close();
        return baos;
    }
}
