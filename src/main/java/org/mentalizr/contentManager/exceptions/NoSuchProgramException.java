package org.mentalizr.contentManager.exceptions;

import java.io.Serial;

public class NoSuchProgramException extends ContentManagerException {

    @Serial
    private static final long serialVersionUID = 1731836819898479692L;
    private final String programName;

    public NoSuchProgramException(String programName) {
        super("No such program [" + programName + "].");
        this.programName = programName;
    }

    public String getProgramName() {
        return programName;
    }
}
