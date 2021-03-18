package org.mentalizr.contentManager.fileHierarchy.exceptions;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;

public class FileNotFoundException extends ProgramManagerException {

    public FileNotFoundException(String message) {
        super(message);
    }

    public static FileNotFoundException forFile(File file) {
        return new FileNotFoundException("File not found: [" + file.getAbsolutePath() + "]");
    }

    public static FileNotFoundException forDirectory(File dir) {
        return new FileNotFoundException("Directory not found: [" + dir.getAbsolutePath() + "]");
    }

}
