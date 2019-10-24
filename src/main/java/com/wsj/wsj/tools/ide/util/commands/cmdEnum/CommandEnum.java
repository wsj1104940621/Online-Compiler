package com.wsj.wsj.tools.ide.util.commands.cmdEnum;

public enum CommandEnum {
    COMPILE("javac"), EXEC("java"), CMD("cmd.exe"), CLOSE("/C"), KEEP("/K"), CLASSPATH("-cp"), ASSIGNPATH("-d");

    private String command;
    private CommandEnum(String command) {
        this.command = command;
    }

    @Override
    public String toString(){
        return this.command;
    }
}
