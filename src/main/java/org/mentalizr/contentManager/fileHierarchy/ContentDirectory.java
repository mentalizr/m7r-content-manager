package org.mentalizr.contentManager.fileHierarchy;

import org.mentalizr.contentManager.exceptions.*;
import org.mentalizr.contentManager.fileHierarchy.contentFile.ContentFile;

import java.io.File;
import java.util.List;

public abstract class ContentDirectory extends RepoDirectory {

    public ContentDirectory(File file) throws ProgramManagerException {
        super(file);
    }

    public abstract List<? extends ContentFile> getContentFiles();

}
