package org.mentalizr.contentManager.fileHierarchy;

import org.mentalizr.contentManager.fileHierarchy.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.ReadPermissionMissingException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.WritePermissionMissingException;

import java.io.File;
import java.nio.file.Path;

public abstract class FileHierarchyElement {

    protected File file;

    public FileHierarchyElement(File file) throws ProgramManagerException {
        this.file = file;

        if (requiresExistence()) {
            if (!this.file.exists()) throwFileNotFoundException();
        } else {
            return;
        }

        if (requiresReadPermission()) {
            if (!this.file.canRead()) throwReadPermissionMissingException();
        }

        if (requiresWritePermission()) {
            if (!this.file.canWrite()) throwWritePermissionMissingException();
        }
    }

    public File asFile() {
        return this.file;
    }

    public Path asPath() {
        return this.file.toPath();
    }

    public String getName() {
        return this.file.getName();
    }

    public abstract boolean requiresExistence();

    public abstract boolean requiresReadPermission();

    public abstract boolean requiresWritePermission();

    protected abstract void throwFileNotFoundException() throws FileNotFoundException;

    protected abstract void throwReadPermissionMissingException() throws ReadPermissionMissingException;

    protected abstract void throwWritePermissionMissingException() throws WritePermissionMissingException;

}
