package org.mentalizr.contentManager.exceptions;

public class InconsistencyException extends ContentManagerException {

    public InconsistencyException() {
    }

    public InconsistencyException(String message) {
        super(message);
    }

    public InconsistencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InconsistencyException(Throwable cause) {
        super(cause);
    }

    public InconsistencyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
