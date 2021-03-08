package org.mentalizr.contentManager.fileHierarchy.media;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoDirectory;

import java.io.File;

public class MediaDir extends RepoDirectory {

    public MediaDir(File file) throws ProgramManagerException {
        super(file);
    }

    public boolean hasFile(String fileName) {
        File file = new File(getFile(), fileName);
        return file.exists();
    }

    @Override
    public boolean requiresExistence() {
        return true;
    }

    @Override
    public boolean requiresReadPermission() {
        return true;
    }

    @Override
    public boolean requiresWritePermission() {
        return false;
    }
}
