package org.mentalizr.contentManager.fileHierarchy.media;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoDirectory;

import java.io.File;

import static org.mentalizr.contentManager.helper.PathAssertions.assertFileName;

public class MediaDir extends RepoDirectory {

    public static final String DIR_NAME = "media";

    public MediaDir(File file) throws ProgramManagerException {
        super(file);
        assertFileName(file.toPath(), DIR_NAME);
    }

    public boolean hasFile(String fileName) {
        File file = new File(asFile(), fileName);
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
