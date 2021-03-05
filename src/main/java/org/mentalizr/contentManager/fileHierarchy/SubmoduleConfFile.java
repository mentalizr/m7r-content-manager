package org.mentalizr.contentManager.fileHierarchy;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;

public class SubmoduleConfFile extends FhFile {

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
