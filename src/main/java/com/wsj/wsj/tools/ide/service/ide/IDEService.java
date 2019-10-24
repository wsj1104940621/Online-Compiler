package com.wsj.wsj.tools.ide.service.ide;

import com.wsj.wsj.base.util.FileUtil;
import com.wsj.wsj.base.util.ProjectInfoUtil;
import com.wsj.wsj.tools.ide.entity.JavaEntity;
import com.wsj.wsj.tools.ide.util.CmdExec;
import com.wsj.wsj.tools.ide.util.commands.cmdEnum.CmdEnum;
import com.wsj.wsj.tools.ide.util.stream.StreamEnum;
import com.wsj.wsj.tools.ide.util.commands.Command;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;

@Service
public class IDEService {
    private final File dempFile = com.wsj.wsj.tools.ide.util.commands.file.FileUtil.
            getPersonalFile("HelloWorld", "HelloWorld.java",
                    com.wsj.wsj.tools.ide.util.commands.file.FileUtil.FileType.TEMPFILE);

    public String getClassText(Class cla) throws IOException {
        return FileUtil.getFileText(cla);
    }

    public String getDemoText() {
        if (FileUtil.isFile(dempFile)) {
            return FileUtil.getFileText(dempFile);
        } else {
            return "";
        }
    }

    public String codeExec(JavaEntity javaEntity) {
        String result = null;

        BufferedWriter bufferedWriter = null;
        try {
            if (!dempFile.exists()) {
                dempFile.createNewFile();
            }
            if (dempFile.isFile()) {
                FileWriter fileWriter = new FileWriter(dempFile);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(javaEntity.getFileCode());
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String projectName = "HelloWorld";

        CmdExec cmdExec = new CmdExec();
        String classPath = com.wsj.wsj.tools.ide.util.commands.file.FileUtil.
                getPersonalFilePath(projectName,
                        com.wsj.wsj.tools.ide.util.commands.file.FileUtil.FileType.CLASSES);
        Map compileResult = cmdExec.compileFile(classPath, dempFile);
        String exitVal = compileResult.get(StreamEnum.EXITVAL).toString();
        if (exitVal != null && !exitVal.equals("0")) {
            result = compileResult.get(StreamEnum.ERRORSTREAM).toString();
            return result;
        }

        Map execResult = cmdExec.execFile(classPath, projectName);
        result = (String) execResult.get(StreamEnum.INPUTSTREAM) + execResult.get(StreamEnum.ERRORSTREAM);
        return result;
    }
}
