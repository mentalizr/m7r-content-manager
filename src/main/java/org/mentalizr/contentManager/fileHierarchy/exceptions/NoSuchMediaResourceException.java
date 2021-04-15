package org.mentalizr.contentManager.fileHierarchy.exceptions;

import org.mentalizr.contentManager.exceptions.ContentManagerException;

public class NoSuchMediaResourceException extends ContentManagerException {

    private final String programName;
    private final String fileName;

    public NoSuchMediaResourceException(String programName, String fileName) {
        super("No such media resource [" + fileName + "] in program [" + programName + "].");
        this.programName = programName;
        this.fileName = fileName;
    }

    public String getProgramName() {
        return this.programName;
    }

    public String getFileName() {
        return this.fileName;
    }
}
