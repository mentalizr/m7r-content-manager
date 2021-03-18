package org.mentalizr.contentManager.fileHierarchy.levels.contentRoot;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.confFile.ConfFile;

import java.io.File;

public class ProgramConfFile extends ConfFile {

    public static final String FILE_NAME = "program.conf";

    private final ProgramConf programConf;

    public ProgramConfFile(File file) throws ProgramManagerException {
        super(file);
        this.programConf = new ProgramConf(this.file);
    }

    public ProgramConf getProgramConf() {
        return this.programConf;
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
