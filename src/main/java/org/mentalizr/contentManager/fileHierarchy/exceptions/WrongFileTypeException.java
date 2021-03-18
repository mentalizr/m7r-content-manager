package org.mentalizr.contentManager.fileHierarchy.exceptions;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;

public class WrongFileTypeException extends ProgramManagerException {

    public WrongFileTypeException(String message) {
        super(message);
    }

    public static WrongFileTypeException fileExpected(File file) {
        return new WrongFileTypeException("Wrong file type. Regular file expected. [" + file.getAbsolutePath() + "]");
    }

    public static WrongFileTypeException directoryExpected(File dir) {
        return new WrongFileTypeException("Wrong file type. Directory expected. [" + dir.getAbsolutePath() + "]");
    }

}
