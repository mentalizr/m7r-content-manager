package org.mentalizr.contentManager.fileHierarchy.exceptions;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;

public class MalformedMediaResourceNameException extends ProgramManagerException {

    private final String resourceName;

    public MalformedMediaResourceNameException(String resourceName) {
        super("Malformed media resource name [" + resourceName + "]. Nothing else than a simple file name is accepted.");
        this.resourceName = resourceName;
    }

    public String getResourceName() {
        return resourceName;
    }
}
