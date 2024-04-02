package org.mentalizr.contentManager.exceptions;

import java.io.Serial;

public class ContentManagerException extends Exception {

    @Serial
    private static final long serialVersionUID = -2570200175156052995L;

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
