package org.mentalizr.contentManager.fileHierarchy;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;

public class ModuleConfFile extends FhFile {

    private final ModuleConf moduleConf;

    public ModuleConfFile(File file) throws ProgramManagerException {
        super(file);
        this.moduleConf = new ModuleConf(this.file);
    }

    public ModuleConf getModuleConf() {
        return this.moduleConf;
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
