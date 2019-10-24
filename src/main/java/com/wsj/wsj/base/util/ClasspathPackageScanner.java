package com.wsj.wsj.base.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClasspathPackageScanner {
    public static List<Class> getAllClass(File file) throws IOException, ClassNotFoundException {
        List<Class> ret = new ArrayList<Class>();
        if (file == null) return ret;
        if (file.isDirectory()) {
            File[] list = file.listFiles();
            for (File f : list) {
                ret.addAll(getAllClass(f));
            }
        } else {
            Class cla = fileToClass(file);
            if (cla != null) {
                ret.add(cla);
            }
        }
        return ret;
    }

    private static Class fileToClass(File file) throws IOException, ClassNotFoundException {
        final String fileType = "java";
        if (FileUtil.isFileType(file, fileType)) {
            String name = file.getPath().replace("." + fileType, "")
                    .replace(ProjectInfoUtil.getJavaSourcePath().getPath()+"", "");
            name = name.replaceAll("\\\\", ".");
            name = name.substring(1, name.length());
            // 采用.class 的RestartClassLoader加载类
            return ClasspathPackageScanner.class.getClassLoader().loadClass(name);
        }
        return null;
    }
}
