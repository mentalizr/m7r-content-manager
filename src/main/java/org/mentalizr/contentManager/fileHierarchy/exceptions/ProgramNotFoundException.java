package org.mentalizr.contentManager.fileHierarchy.exceptions;

import org.mentalizr.contentManager.exceptions.ContentManagerException;

public class ProgramNotFoundException extends ContentManagerException {

    private final String programName;

    public ProgramNotFoundException(String programName) {
        super("Program [" + programName + "] not found.");
        this.programName = programName;
    }

    public String getProgramName() {
        return this.programName;
    }
}
