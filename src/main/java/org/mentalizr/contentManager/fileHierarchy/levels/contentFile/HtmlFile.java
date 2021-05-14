package org.mentalizr.contentManager.fileHierarchy.levels.contentFile;

import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.serviceObjects.frontend.program.InfotextSO;
import org.mentalizr.serviceObjects.frontend.program.StepSO;

import java.io.File;

public class HtmlFile extends ContentFile {

    public static final String FILETYPE = ".html";

    public HtmlFile(File file) throws ContentManagerException {
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

    public StepSO asStep() {
        return new StepSO(getId(), getDisplayName());
    }

    public InfotextSO asInfotext() {
        return new InfotextSO(getId(), getDisplayName());
    }

}
