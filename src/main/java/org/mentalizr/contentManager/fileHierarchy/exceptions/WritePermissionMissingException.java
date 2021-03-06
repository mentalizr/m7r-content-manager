package org.mentalizr.contentManager.fileHierarchy.exceptions;

import org.mentalizr.contentManager.exceptions.ContentManagerException;

import java.io.File;

public class WritePermissionMissingException extends ContentManagerException {

    private WritePermissionMissingException(String message) {
        super(message);
    }

    public static WritePermissionMissingException forDirectory(File dir) {
        return new WritePermissionMissingException("Write permission missing for directory [" + dir.getAbsolutePath() + "]");
    }

    public static WritePermissionMissingException forFile(File file) {
        return new WritePermissionMissingException("Write permission missing for file [" + file.getAbsolutePath() + "]");
    }

}
