package com.wsj.wsj.tools.ide.util.commands;

import com.wsj.wsj.base.config.objectStore.ObjectStore;
import com.wsj.wsj.tools.ide.util.commands.cmdEnum.CmdEnum;

@com.wsj.wsj.base.config.objectStore.annotation.ObjectStore
public class Command {
    private static Command instance;

    private CmdEnum commandType;
    private String[] args;

    public void setCommandType(CmdEnum commandType) {
        this.commandType = commandType;
    }
    public CmdEnum getCommandType() {
        return this.commandType;
    }

    public String[] getArgs() {
        return this.args;
    }
    public void setArgs(String[] args) {
        this.args = args;
    }

    public String[] getCmds() {
        String[] commands = getCommandType().getCommands();
        if (commands == null) {
            throw new NullPointerException("基础指令不能为空");
        }
        String[] args = getArgs();
        if (args == null) {
            args = new String[0];
        }

        String[] cmds = new String[commands.length + args.length];
        System.arraycopy(commands, 0, cmds, 0, commands.length);
        System.arraycopy(args, 0, cmds, commands.length, args.length);

        setCommandType(null);
        setArgs(null);
        return cmds;
    }

    private Command() {
        if (instance != null) {
            throw new RuntimeException("请不要重复创建实例");
        }
    }

    public static Command getInstance() {
        StackTraceElement[] element = Thread.currentThread().getStackTrace();
        String objType = element[1].getClassName();
        Class obj = null;
        try {
            obj = Command.class.getClassLoader().loadClass(objType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (!Command.class.isAssignableFrom(obj)){
            return null;
        }
        return getInstance(obj);
    }

    public static Command getInstance(Class cla) {
        if (instance == null) {
            synchronized (Command.class) {
                if (instance == null) {
                    if (Command.class.isAssignableFrom(cla)) {
                        instance = (Command) ObjectStore.getInstance().getObjectStore().get(cla);
                    }
                }
            }
        }
        return instance;
    }

    private Object readResolve(){
        return instance;
    }
}
