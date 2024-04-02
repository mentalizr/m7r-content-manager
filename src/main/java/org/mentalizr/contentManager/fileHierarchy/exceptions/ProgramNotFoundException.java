package org.mentalizr.contentManager.fileHierarchy.exceptions;

import org.mentalizr.contentManager.exceptions.ContentManagerException;

import java.io.Serial;

public class ProgramNotFoundException extends ContentManagerException {

    @Serial
    private static final long serialVersionUID = -6639031465636880758L;
    private final String programName;

    public ProgramNotFoundException(String programName) {
        super("Program [" + programName + "] not found.");
        this.programName = programName;
    }

    public String getProgramName() {
        return this.programName;
    }
}
