package com.wsj.wsj.tools.ide.util.stream;

import java.io.*;

public class StreamGobbler extends Thread{
    private String encode = "gbk";

    private InputStream is;
    private String type;
    private OutputStream os;

    public StreamGobbler(InputStream is, String type){
        this(is, type, null, null);
    }

    public StreamGobbler(InputStream is, String type, OutputStream os) {
        this(is, type, os, null);
    }

    public StreamGobbler(InputStream is, String type, String encode){
        this(is, type, null, encode);
    }

    public StreamGobbler(InputStream is, String type, OutputStream os, String encode) {
        this.is = is;
        this.type = type;
        this.os = os;
        setEncode(encode);
    }

    @Override
    public void run() {
        try {
            PrintWriter pw = os == null?null:new PrintWriter(os);
            if(pw != null) {
                pw.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        if (encode != null) this.encode = encode;
    }
}
