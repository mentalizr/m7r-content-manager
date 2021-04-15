package org.mentalizr.contentManager.exceptions;

public class ContentManagerException extends Exception {

    public ContentManagerException() {
    }

    public ContentManagerException(String message) {
        super(message);
    }

    public ContentManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContentManagerException(Throwable cause) {
        super(cause);
    }

    public ContentManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
