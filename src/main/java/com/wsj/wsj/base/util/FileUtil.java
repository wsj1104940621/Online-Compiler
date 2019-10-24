package com.wsj.wsj.base.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
    // 得到源代码内容
    public static String getFileText(Class cla) {
        return getFileText(getJavaFile(cla));
    }

    // 将文件内容转成String字符串
    public static String getFileText(File file) {
        if (isFile(file)) {
            StringBuffer sb = new StringBuffer();

            FileReader reader = null;
            try {
                reader = new FileReader(file);
                char[] buff = new char[1024];
                int num = 0;
                while ((num=reader.read(buff)) != -1) {
                    sb.append(new String(buff, 0, num));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return sb.toString();
        }
        return "";
    }

    // 通过类找到java源代码文件
    public static File getJavaFile(Class cla){
        if (cla == null) return null;
        String basePath = ProjectInfoUtil.getJavaSourcePath().getPath();
        String javaPath = FileUtil.convertPath(cla.getName());
        javaPath = fileSuffix(javaPath, "java");
        return new File(basePath + javaPath);
    }

  // 点路径转为斜杠路径
    public static String convertPath(String path){
        return String.format("/%s/", path.contains(".")?path.replaceAll("\\.","/"):path);
    }

    //文件名加后缀
    public static String fileSuffix(String fileName, String fileType) {
        fileName = StringUtil.trimRight(fileName, "\\");
        fileName = StringUtil.trimRight(fileName, "/");
        return fileName + "." + fileType;
    }

    public static boolean isFile(File file) {
        return file != null && file.isFile();
    }

    public static boolean isFile(String path) {
        return isFile(new File(path));
    }

    public static boolean isDirectory(File file) {
        return file != null && file.isDirectory();
    }

    public static boolean isDirectory(String path) {
        return isDirectory(new String(path));
    }

    public static boolean isFileType(File file, String type) {
        return isFile(file) && file.getName().endsWith("." + type);
    }

    public static boolean isFileType(String file, String type) {
        return isFile(file) && file.endsWith("." + type);
    }
}
