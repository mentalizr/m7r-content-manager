package org.mentalizr.contentManager.fileHierarchy;

import org.mentalizr.contentManager.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.exceptions.ReadPermissionMissingException;
import org.mentalizr.contentManager.exceptions.WritePermissionMissingException;

import java.util.Locale;

public abstract class FhFile extends FileHierarchyElement {

    public FhFile(java.io.File file) throws ProgramManagerException {
        super(file);
        assertFiletype();
    }

    protected abstract String getFiletype();

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

    private void assertFiletype() {
        if (!isCorrectFileType())
            throw new RuntimeException("Not a " + getFiletype() + " file: [" + this.file.getAbsolutePath() + "]");
    }

    private boolean isCorrectFileType() {
        return isFileOfType(this.file.getName(), getFiletype());
//        return (this.file.getName().toLowerCase(Locale.ROOT).endsWith(getFiletype().toLowerCase(Locale.ROOT)));
    }

    public static boolean isFileOfType(String filename, String filenamePostfix) {
        return (filename.toLowerCase(Locale.ROOT).endsWith(filenamePostfix.toLowerCase(Locale.ROOT)));
    }

}
