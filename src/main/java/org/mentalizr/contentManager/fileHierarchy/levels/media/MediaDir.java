package org.mentalizr.contentManager.fileHierarchy.levels.media;

import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.basics.RepoDirectory;
import org.mentalizr.contentManager.fileHierarchy.exceptions.MalformedMediaResourceNameException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.NoSuchMediaResourceException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mentalizr.contentManager.helper.PathAssertions.assertFileName;

public class MediaDir extends RepoDirectory {

    public static final String DIR_NAME = "media";

    public MediaDir(File file) throws ContentManagerException {
        super(file);
        assertFileName(file.toPath(), DIR_NAME);
    }

    @Deprecated
    public boolean hasMediaResource(String fileName) throws MalformedMediaResourceNameException {
        Path fileNamePath = asCheckedPath(fileName);
        return Files.exists(fileNamePath);
    }

    public Path getMediaResource(String fileName) throws MalformedMediaResourceNameException, NoSuchMediaResourceException {
        Path fileNamePath = asCheckedPath(fileName);
        if (!Files.exists(fileNamePath)) throw new NoSuchMediaResourceException(getProgramName(), fileName);
        return asCheckedPath(fileName);
    }

    public String getProgramName() {
        int programIndex = asPath().getNameCount() - 2;
        if (programIndex < 0) throw new RuntimeException("MediaDir not in program hierarchy.");
        return asPath().getName(programIndex).toString();
    }

    private Path asCheckedPath(String fileName) throws MalformedMediaResourceNameException {
        Path fileNamePath = Paths.get(fileName);
        if (fileNamePath.getNameCount() != 1) throw new MalformedMediaResourceNameException(fileName);
        return asPath().resolve(fileName);
    }

    @Override
    public boolean requiresExistence() {
        return true;
    }

    @Override
    public boolean requiresReadPermission() {
        return true;
    }

    @Override
    public boolean requiresWritePermission() {
        return false;
    }
}
