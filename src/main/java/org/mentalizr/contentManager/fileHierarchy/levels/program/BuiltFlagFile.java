package org.mentalizr.contentManager.fileHierarchy.levels.program;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.basics.RepoFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;

public class BuiltFlagFile extends RepoFile {

    public static final String FILE_NAME = "built.flag";

    public BuiltFlagFile(File file) throws ProgramManagerException {
        super(file);
    }

    public boolean exists() {
        return this.file.exists();
    }

    public void touch() throws ProgramManagerException {
        Path path = this.file.toPath();
        try {
            if (Files.exists(path)) {
                Files.setLastModifiedTime(path, FileTime.from(Instant.now()));
            } else {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new ProgramManagerException(e.getMessage(), e);
        }
    }

    public void remove() throws ProgramManagerException {
        try {
            Files.delete(this.file.toPath());
        } catch (IOException e) {
            throw new ProgramManagerException(e.getMessage(), e);
        }
    }

    public Instant getCreationTime() throws ProgramManagerException {
        BasicFileAttributes attributesFlag;
        try {
            attributesFlag = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
        } catch (IOException e) {
            throw new ProgramManagerException(e.getMessage(), e);
        }
        return attributesFlag.creationTime().toInstant();
    }

    @Override
    public boolean requiresExistence() {
        return false;
    }

    @Override
    public boolean requiresReadPermission() {
        return true;
    }

    @Override
    public boolean requiresWritePermission() {
        return true;
    }

    @Override
    protected String getFiletype() {
        return ".flag";
    }
}
