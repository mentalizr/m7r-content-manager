package org.mentalizr.contentManager.fileHierarchy;

import org.mentalizr.contentManager.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.exceptions.ReadPermissionMissingException;
import org.mentalizr.contentManager.exceptions.WritePermissionMissingException;

import java.io.File;

public abstract class FhDirectory extends FileHierarchyElement {

    public FhDirectory(File file) throws ProgramManagerException {
        super(file);
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
}
