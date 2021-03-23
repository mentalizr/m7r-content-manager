package org.mentalizr.contentManager.build;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.nio.file.Path;

public class BuildException extends ProgramManagerException {

    private final Path path;

    public BuildException(Path path) {
        this.path = path;
    }

    public BuildException(Path path, String message) {
        super(message);
        this.path = path;
    }

    public BuildException(Path path, String message, Throwable cause) {
        super(message, cause);
        this.path = path;
    }

    public BuildException(Path path, Throwable cause) {
        super(cause);
        this.path = path;
    }

    public BuildException(Path path, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.path = path;
    }

    public Path getPath() {
        return this.path;
    }
}
