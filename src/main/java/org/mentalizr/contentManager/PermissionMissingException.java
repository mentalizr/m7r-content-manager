package org.mentalizr.contentManager;

import java.io.File;

public class PermissionMissingException extends ProgramManagerException {

    private PermissionMissingException(String message) {
        super(message);
    }

    public static PermissionMissingException forDirectory(File dir) {
        return new PermissionMissingException("No read permission for directory [" + dir.getAbsolutePath() + "]");
    }

}
