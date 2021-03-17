package org.mentalizr.contentManager.fileHierarchy.confFile;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoFile;

import java.io.File;

public abstract class ConfFile extends RepoFile {

    public static final String FILETYPE = ".conf";

    public ConfFile(File file) throws ProgramManagerException {
        super(file);
    }

    @Override
    protected String getFiletype() {
        return FILETYPE;
    }

}
