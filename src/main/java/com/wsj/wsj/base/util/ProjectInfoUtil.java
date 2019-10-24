package com.wsj.wsj.base.util;

import java.io.File;
import java.io.IOException;

public class ProjectInfoUtil {
    private final static String FILE_SEPARATION = "\\";

    private final static String SOURCE_PATH = FILE_SEPARATION + "src" + FILE_SEPARATION +"main"; //源代码所在路径
    private final static String JAVA_SOURCE_PATH = SOURCE_PATH + FILE_SEPARATION + "java"; //java源代码路径

    public static File getBasePath() {
        return new File(System.getProperty("user.dir"));
    }

    public static File getSourcePath(){
        return new File(getBasePath().getPath() + SOURCE_PATH);
    }

    public static File getJavaSourcePath() {
        return new File(getBasePath().getPath() + JAVA_SOURCE_PATH);
    }
}
