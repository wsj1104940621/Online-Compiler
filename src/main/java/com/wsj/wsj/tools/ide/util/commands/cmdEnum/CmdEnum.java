package com.wsj.wsj.tools.ide.util.commands.cmdEnum;

public enum CmdEnum {
    COMPILE{
        @Override
        void fit() {
            String[] commands = getCommands();
            if (commands == null) {
                commands = new String[4];
                commands[0] = CommandEnum.CMD.toString();
                commands[1] = CommandEnum.CLOSE.toString();
                commands[2] = CommandEnum.COMPILE.toString();
                commands[3] = CommandEnum.ASSIGNPATH.toString();
                setCommands(commands);
            }
        }
    }, EXEC{
        @Override
        void fit() {
            String[] commands = getCommands();
            if (commands == null) {
                commands = new String[4];
                commands[0] = CommandEnum.CMD.toString();
                commands[1] = CommandEnum.CLOSE.toString();
                commands[2] = CommandEnum.EXEC.toString();
                commands[3] = CommandEnum.CLASSPATH.toString();
                setCommands(commands);
            }
        }
    };

    private String[] commands;

    private CmdEnum(){
        fit();
    }

    void fit() {}

    void setCommands(String[] commands){
        this.commands = commands;
    }

    public String[] getCommands() {
        return this.commands;
    }
}
