package org.mentalizr.contentManager.exceptions;

public class NoSuchProgramException extends ContentManagerException {

    private final String programName;

    public NoSuchProgramException(String programName) {
        super("No such program [" + programName + "].");
        this.programName = programName;
    }

    public String getProgramName() {
        return programName;
    }
}
