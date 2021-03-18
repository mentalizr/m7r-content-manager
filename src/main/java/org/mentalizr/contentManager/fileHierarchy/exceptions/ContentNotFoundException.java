package org.mentalizr.contentManager.fileHierarchy.exceptions;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;

public class ContentNotFoundException extends ProgramManagerException {

    private final String id;

    public ContentNotFoundException(String id) {
        super("Content with id [" + id + "] not found.");
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
