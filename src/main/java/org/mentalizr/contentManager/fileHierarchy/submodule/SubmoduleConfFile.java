package org.mentalizr.contentManager.fileHierarchy.submodule;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoFile;

import java.io.File;

public class SubmoduleConfFile extends RepoFile {

    public static final String FILE_NAME = "submodule.conf";

    private final SubmoduleConf submoduleConf;

    public SubmoduleConfFile(File file) throws ProgramManagerException {
        super(file);
        this.submoduleConf = new SubmoduleConf(this.file);
    }

    public SubmoduleConf getSubmoduleConf() {
        return this.submoduleConf;
    }

    @Override
    protected String getFiletype() {
        return ".conf";
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
