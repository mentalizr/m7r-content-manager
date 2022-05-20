package org.mentalizr.contentManager.helper;

import org.mentalizr.contentManager.exceptions.ContentManagerException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathHelper {

    public static void createDirectory(Path path) throws ContentManagerException {
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            throw new ContentManagerException("Exception when creating directory ["
                    + path.toAbsolutePath() + "]: "
                    + e.getMessage());
        }
    }

}
