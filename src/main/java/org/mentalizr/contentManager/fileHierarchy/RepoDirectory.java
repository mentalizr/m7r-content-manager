package org.mentalizr.contentManager.fileHierarchy;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.contentManager.exceptions.*;
import org.mentalizr.contentManager.fileHierarchy.contentFile.ContentFile;
import org.mentalizr.contentManager.fileHierarchy.contentRoot.HtmlDir;
import org.mentalizr.contentManager.fileHierarchy.contentRoot.MdpDir;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class RepoDirectory extends FileHierarchyElement {

    public RepoDirectory(File file) throws ProgramManagerException {
        super(file);
        assertIsDirectory();
    }

    @Override
    protected void throwFileNotFoundException() throws FileNotFoundException {
        throw FileNotFoundException.forDirectory(this.file);
    }

    @Override
    protected void throwReadPermissionMissingException() throws ReadPermissionMissingException {
        throw ReadPermissionMissingException.forDirectory(this.file);
    }

    @Override
    protected void throwWritePermissionMissingException() throws WritePermissionMissingException {
        throw WritePermissionMissingException.forDirectory(this.file);
    }

    private void assertIsDirectory() throws WrongFileTypeException {
        if (!this.file.isDirectory())
            throw WrongFileTypeException.directoryExpected(file);
    }

}
