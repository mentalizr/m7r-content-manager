package org.mentalizr.contentManager.fileHierarchy.levels.module;

import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.confFile.ConfFile;

import java.io.File;

public class ModuleConfFile extends ConfFile {

    public static final String FILE_NAME = "module.conf";

    private final ModuleConf moduleConf;

    public ModuleConfFile(File file) throws ContentManagerException {
        super(file);
        this.moduleConf = new ModuleConf(this.file);
    }

    public ModuleConf getModuleConf() {
        return this.moduleConf;
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
