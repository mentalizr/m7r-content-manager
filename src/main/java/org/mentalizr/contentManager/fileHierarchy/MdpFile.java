package org.mentalizr.contentManager.fileHierarchy;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;

public class MdpFile extends FhFile {

    private static final String FILETYPE = ".mdp";

    public MdpFile(File file) throws ProgramManagerException {
        super(file);
    }

    @Override
    protected String getFiletype() {
        return FILETYPE;
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

    public static boolean isMdFile(String filename) {
        return isFileOfType(filename, FILETYPE);
    }

//    public String getIdFraction() {
//        return this.infotextFile.getName();
//    }

}
