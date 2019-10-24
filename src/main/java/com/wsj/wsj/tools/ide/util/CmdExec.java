package com.wsj.wsj.tools.ide.util;

import com.wsj.wsj.base.util.StreamUtil;
import com.wsj.wsj.tools.ide.util.commands.Command;
import com.wsj.wsj.tools.ide.util.commands.cmdEnum.CmdEnum;
import com.wsj.wsj.tools.ide.util.stream.StreamEnum;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CmdExec {
    private final static String CHARSET = "gbk";
    private String[] cmd;

    public Map<StreamEnum, String> compileFile(String destPath, File file) {
        Command command = Command.getInstance();
        String[] compileArgs = new String[2];
        compileArgs[0] = destPath;
        compileArgs[1] = file.getPath();
        command.setCommandType(CmdEnum.COMPILE);
        command.setArgs(compileArgs);
        String[] commands = command.getCmds();
        return getExecResult(commands);
    }

    public Map<StreamEnum, String> execFile(String classPath, String execArg){
        Command command = Command.getInstance();
        String[] execArgs = new String[2];
        execArgs[0] = classPath;
        execArgs[1] = execArg;
        command.setCommandType(CmdEnum.EXEC);
        command.setArgs(execArgs);
        String[] commands = command.getCmds();
        return getExecResult(commands);
    }

    public Map<StreamEnum, String> getExecResult(String[] command) {
        Map streamMap = exec(command);
        Map<StreamEnum, String> resultMap = null;
        try {
            resultMap = new HashMap<StreamEnum, String>();

            InputStream is = (InputStream) streamMap.get(StreamEnum.INPUTSTREAM);
            InputStream es = (InputStream) streamMap.get(StreamEnum.ERRORSTREAM);
            OutputStream os = (OutputStream) streamMap.get(StreamEnum.OUTPUTSTREAM);

            resultMap.put(StreamEnum.INPUTSTREAM, StreamUtil.InputStreamToString(is, CHARSET));
            resultMap.put(StreamEnum.ERRORSTREAM, StreamUtil.InputStreamToString(es, CHARSET));
            resultMap.put(StreamEnum.OUTPUTSTREAM, StreamUtil.OutputStreamToString(os, CHARSET));
            resultMap.put(StreamEnum.EXITVAL, streamMap.get(StreamEnum.EXITVAL).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    public String getExecResult(String[] command, StreamEnum streamEnum) throws IOException {
        return getExecResult(command).get(streamEnum);
    }

    private Map exec(String[] commands) {
        Map stream = new HashMap();

        OutputStream os;
        InputStream is;
        InputStream es;

        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(commands);

            is = proc.getInputStream();
            es = proc.getErrorStream();
            os = new ByteArrayOutputStream();

            /*StreamGobbler errorGobbler = new StreamGobbler(es, "ERROR", os, CHARSET);
            StreamGobbler outputGobbler = new StreamGobbler(is, "OUTPUT", os, CHARSET);

            errorGobbler.start();
            outputGobbler.start();*/
            int exitVal = proc.waitFor();

            stream.put(StreamEnum.INPUTSTREAM, is);
            stream.put(StreamEnum.ERRORSTREAM, es);
            stream.put(StreamEnum.OUTPUTSTREAM, os);
            stream.put(StreamEnum.EXITVAL, exitVal);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return stream;
    }
}
