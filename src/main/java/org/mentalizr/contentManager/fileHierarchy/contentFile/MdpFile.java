package org.mentalizr.contentManager.fileHierarchy.contentFile;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoFile;

import java.io.File;

public class MdpFile extends ContentFile {

    public static final String FILETYPE = ".mdp";

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

    @Override
    public String getName() {
        return Strings.cutEnd(super.getName(), FILETYPE.length());
    }

}
