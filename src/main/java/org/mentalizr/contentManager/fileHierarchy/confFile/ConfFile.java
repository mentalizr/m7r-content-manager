package org.mentalizr.contentManager.fileHierarchy.confFile;

import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.basics.RepoFile;

import java.io.File;

public abstract class ConfFile extends RepoFile {

    public static final String FILETYPE = ".conf";

    public ConfFile(File file) throws ContentManagerException {
        super(file);
    }

    @Override
    protected String getFiletype() {
        return FILETYPE;
    }

}
