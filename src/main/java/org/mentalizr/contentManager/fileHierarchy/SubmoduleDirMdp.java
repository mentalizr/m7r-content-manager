package org.mentalizr.contentManager.fileHierarchy;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SubmoduleDirMdp extends FhDirectory {

    private final ModuleConfFile moduleConfFile;

    public SubmoduleDirMdp(File file) throws ProgramManagerException {
        super(file);
        this.moduleConfFile = new ModuleConfFile(new File(getFile(), "submodule.conf"));
    }

    public ModuleConfFile getModuleConfFile() {
        return moduleConfFile;
    }

    public String getIdFraction() {
        return this.file.getName();
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
