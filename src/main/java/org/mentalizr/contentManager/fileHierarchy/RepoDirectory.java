package org.mentalizr.contentManager.fileHierarchy;

import org.mentalizr.contentManager.exceptions.*;
import org.mentalizr.contentManager.fileHierarchy.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.ReadPermissionMissingException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.WritePermissionMissingException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.WrongFileTypeException;

import java.io.File;

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
