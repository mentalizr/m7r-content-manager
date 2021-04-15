package org.mentalizr.contentManager.fileHierarchy.exceptions;

import org.mentalizr.contentManager.exceptions.ContentManagerException;

import java.io.File;

public class ReadPermissionMissingException extends ContentManagerException {

    private ReadPermissionMissingException(String message) {
        super(message);
    }

    public static ReadPermissionMissingException forDirectory(File dir) {
        return new ReadPermissionMissingException("Read permission missing for directory [" + dir.getAbsolutePath() + "]");
    }

    public static ReadPermissionMissingException forFile(File file) {
        return new ReadPermissionMissingException("Read permission missing for file [" + file.getAbsolutePath() + "]");
    }

}
