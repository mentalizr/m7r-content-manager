package org.mentalizr.contentManager.fileHierarchy.submodule;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoFile;
import org.mentalizr.contentManager.fileHierarchy.confFile.ConfFile;

import java.io.File;

public class SubmoduleConfFile extends ConfFile {

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
