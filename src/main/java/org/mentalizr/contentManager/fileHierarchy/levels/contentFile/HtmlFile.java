package org.mentalizr.contentManager.fileHierarchy.levels.contentFile;

import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.programStructure.Infotext;
import org.mentalizr.contentManager.programStructure.Step;

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

    public Step asStep() {
        if (this.contentFileType == ContentFileType.STEP) {
            return new Step(getId(), getDisplayName(), isExercise(), isFeedback());
        } else {
            throw new IllegalStateException(
                    "ContentFileType of [" + this.id + "] is not of type [" + ContentFileType.STEP + "]."
            );
        }
    }

    public Infotext asInfotext() {
        if (this.contentFileType == ContentFileType.INFO) {
            return new Infotext(getId(), getDisplayName());
        } else {
            throw new IllegalStateException(
                    "ContentFileType of [" + this.id + "] is not of type [" + ContentFileType.INFO + "]."
            );
        }
    }

}
