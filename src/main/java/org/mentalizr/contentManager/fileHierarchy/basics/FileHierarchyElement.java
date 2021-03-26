package org.mentalizr.contentManager.fileHierarchy.basics;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.FileNotFoundException;
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
        }

        if (!requiresExistence() && requiresReadPermission())
            throw new RuntimeException("Illegal " + FileHierarchyElement.class.getSimpleName()
                    + " specification: requiring read permission for a non existing element.");
        if (requiresReadPermission()) {
            if (!this.file.canRead()) throwReadPermissionMissingException();
        }

        if (!requiresExistence() && requiresWritePermission())
            throw new RuntimeException("Illegal " + FileHierarchyElement.class.getSimpleName()
                    + " specification: requiring write permission for a non existing element.");
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
