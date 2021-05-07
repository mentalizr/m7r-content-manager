package org.mentalizr.contentManager.fileHierarchy.exceptions;

import org.mentalizr.contentManager.exceptions.ContentManagerException;

public class MediaResourceReferenceInconsistencyException extends ContentManagerException {

    private final String mediaFileName;

    public MediaResourceReferenceInconsistencyException(String mediaFileName) {
        super("Referenced media file is not available: [" + mediaFileName + "].");
        this.mediaFileName = mediaFileName;
    }

    public String getMediaFileName() {
        return this.mediaFileName;
    }
}
