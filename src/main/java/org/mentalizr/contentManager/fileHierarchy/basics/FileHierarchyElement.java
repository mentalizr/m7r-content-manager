package org.mentalizr.contentManager.fileHierarchy.basics;

import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.ReadPermissionMissingException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.WritePermissionMissingException;

import java.io.File;
import java.nio.file.Path;

public abstract class FileHierarchyElement {

    protected File file;

    public FileHierarchyElement(File file) throws ContentManagerException {
        this.file = file;

        if (requiresExistence()) {
            if (!this.file.exists()) throwFileNotFoundException();
        }

        if (!requiresExistence() && requiresReadPermission())
            throw new AssertionError("Illegal " + this.getClass().getSimpleName()
                    + " specification: requiring read permission for a non existing element."
                    + " Specify requireExistence also.");
        if (requiresReadPermission()) {
            if (!this.file.canRead()) throwReadPermissionMissingException();
        }

        if (!requiresExistence() && requiresWritePermission())
            throw new AssertionError("Illegal " + this.getClass().getSimpleName()
                    + " specification: requiring write permission for a non existing element."
                    + " Specify requireExistence also.");
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

    /**
     * Specifies weather file hierarchy element is expected as existing.
     *
     * @return
     */
    public abstract boolean requiresExistence();

    /**
     * Specifies weather file hierarchy element is expected to be readably by executing user.
     * Checking file permissions requires existence also.
     *
     * @return
     */
    public abstract boolean requiresReadPermission();

    /**
     * Specifies weather file hierarchy element is expected to be writable by executing user.
     * Checking file permissions requires existence also.
     *
     * @return
     */
    public abstract boolean requiresWritePermission();

    protected abstract void throwFileNotFoundException() throws FileNotFoundException;

    protected abstract void throwReadPermissionMissingException() throws ReadPermissionMissingException;

    protected abstract void throwWritePermissionMissingException() throws WritePermissionMissingException;

}
