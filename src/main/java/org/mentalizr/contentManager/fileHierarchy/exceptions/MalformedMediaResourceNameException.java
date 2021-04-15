package org.mentalizr.contentManager.fileHierarchy.exceptions;

import org.mentalizr.contentManager.exceptions.ContentManagerException;

public class MalformedMediaResourceNameException extends ContentManagerException {

    private final String resourceName;

    public MalformedMediaResourceNameException(String resourceName) {
        super("Malformed media resource name [" + resourceName + "]. Nothing else than a simple file name is accepted.");
        this.resourceName = resourceName;
    }

    public String getResourceName() {
        return resourceName;
    }
}
