package org.mentalizr.contentManager;

public class ProgramManagerException extends Exception{

    public ProgramManagerException() {
    }

    public ProgramManagerException(String message) {
        super(message);
    }

    public ProgramManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProgramManagerException(Throwable cause) {
        super(cause);
    }

    public ProgramManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
