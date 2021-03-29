package org.mentalizr.contentManager.fileHierarchy.basics;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.ReadPermissionMissingException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.WritePermissionMissingException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.WrongFileTypeException;
import org.mentalizr.contentManager.helper.Nio2Helper;
import org.mentalizr.contentManager.helper.PathAssertions;

import java.io.File;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Objects;

public abstract class RepoFile extends FileHierarchyElement {

    public RepoFile(java.io.File file) throws ProgramManagerException {
        super(file);
        if (requiresExistence()) assertIsFile();
        assertFiletype();
//        if (requireContainingDir()) assertExistenceOfContainingDirectory();
    }

    protected abstract String getFiletype();

//    protected abstract boolean requireContainingDir();

    @Override
    protected void throwFileNotFoundException() throws FileNotFoundException {
        throw FileNotFoundException.forFile(this.file);
    }

    @Override
    protected void throwReadPermissionMissingException() throws ReadPermissionMissingException {
        throw ReadPermissionMissingException.forFile(this.file);
    }

    @Override
    protected void throwWritePermissionMissingException() throws WritePermissionMissingException {
        throw WritePermissionMissingException.forFile(this.file);
    }

    private void assertIsFile() throws WrongFileTypeException {
        if (!this.file.isFile())
            throw WrongFileTypeException.fileExpected(file);
    }

    private void assertFiletype() {
        if (!isTypeAppropriate())
            throw new RuntimeException("Not a " + getFiletype() + " file: [" + this.file.getAbsolutePath() + "]");
    }

    private boolean isTypeAppropriate() {
        return isTypeAppropriate(this.file, getFiletype());
    }

    public static boolean isTypeAppropriate(File file, String filenamePostfix) {
        return (file.getName().toLowerCase(Locale.ROOT).endsWith(filenamePostfix.toLowerCase(Locale.ROOT)));
    }

//    private void assertExistenceOfContainingDirectory() throws FileNotFoundException {
//        Path dir = this.file.toPath().getParent();
//        Objects.requireNonNull(dir, "No content file. [" + this.file.getAbsolutePath() + "]");
//        if (!Nio2Helper.isExistingDir(dir))
//            throw FileNotFoundException.forDirectory(dir.toFile());
//    }

}
