package com.wsj.wsj.tools.ide.util.commands.file;

import java.io.File;

public class FileUtil {
    public final static String basePath = "D:\\Workspace\\idea_personal\\wsj\\file\\ide\\";
    public final static String sourcePath = basePath + "source\\";
    public final static String personalPath = basePath + "personal\\";
    public final static String tempFileDict = "tempFile\\";
    public final static String classesDict = "classes\\";

    public static String getSourcePath(String projectName) {
        return sourcePath + projectName + "\\\\";
    }

    public static String getPersonalPath (String userName) {
        return personalPath + userName + "\\\\";
    }

    public static String getPersonalProject(String userName, String projectName) {
        if (userName == null || userName.trim().equals("")) {
            return getPersonalPath("test") + projectName + "\\\\";
        } else {
            return getPersonalPath(userName) + projectName + "\\\\";
        }
    }

    public static String getPersonalProject(String projectName) {
        return getPersonalProject(null, projectName);
    }

    public static String getPersonalFilePath(String projectName, FileType type) {
        String fp = getPersonalProject(projectName);
        switch (type) {
            case TEMPFILE:
                fp += tempFileDict;
                break;
            case CLASSES:
                fp += classesDict;
                break;
            case SOURCE:
                break;
        }
        return fp;
    }

    public static File getPersonalFile(String projectName, String filePath, FileType type) {
        String fp = getPersonalFilePath(projectName, type) + filePath;
        return new File(fp);
    }

    public enum FileType{
        TEMPFILE, CLASSES, SOURCE
    }
}
