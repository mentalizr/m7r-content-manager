package org.mentalizr.contentManager.fileHierarchy.levels.contentFile;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.serviceObjects.frontend.program.Infotext;
import org.mentalizr.serviceObjects.frontend.program.Step;

import java.io.File;

public class HtmlFile extends ContentFile {

    public static final String FILETYPE = ".html";

    public HtmlFile(File file) throws ProgramManagerException {
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

    public Step asStep() {
        return new Step(getId(), getDisplayName());
    }

    public Infotext asInfotext() {
        return new Infotext(getId(), getDisplayName());
    }

}
