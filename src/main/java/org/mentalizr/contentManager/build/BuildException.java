package org.mentalizr.contentManager.build;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;

public class BuildException extends ProgramManagerException {

    private final String fileName;

    public BuildException(String fileName, String message) {
        super("Exception on compiling file [" + fileName + "]: " + message);
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
