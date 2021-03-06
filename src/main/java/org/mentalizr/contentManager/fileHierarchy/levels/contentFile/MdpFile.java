package org.mentalizr.contentManager.fileHierarchy.levels.contentFile;

import org.mentalizr.contentManager.exceptions.ContentManagerException;

import java.io.File;

public class MdpFile extends ContentFile {

    public static final String FILETYPE = ".mdp";

    public MdpFile(File file) throws ContentManagerException {
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

}
