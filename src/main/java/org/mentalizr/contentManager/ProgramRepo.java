package org.mentalizr.contentManager;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoDirectory;
import org.mentalizr.contentManager.fileHierarchy.program.ProgramDir;

import java.io.File;

public class ProgramRepo extends RepoDirectory {

    private final ProgramDir programDir;

    public ProgramRepo(File file) throws ProgramManagerException {
        super(file);
        this.programDir = new ProgramDir(this.file);
    }

    public void clean() {

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
