package org.mentalizr.contentManager.fileHierarchy;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;

public class ProgramConfFile extends FhFile {

    private final ProgramConf programConf;

    public ProgramConfFile(File file) throws ProgramManagerException {
        super(file);
        this.programConf = new ProgramConf(this.file);
    }

    public ProgramConf getProgramConf() {
        return this.programConf;
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
