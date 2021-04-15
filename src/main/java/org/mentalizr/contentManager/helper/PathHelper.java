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
                    + path.toAbsolutePath().toString() + "]: "
                    + e.getMessage());
        }
    }

    public static void createConfFile(Path path, String displayName) throws ContentManagerException {
        try {
            Files.writeString(path, "name=" + displayName + "\n");
        } catch (IOException e) {
            throw new ContentManagerException("Exception when writing configuration file ["
                    + path.toAbsolutePath().toString() + "]: "
                    + e.getMessage());
        }
    }

    public static void createMdpFile(Path path, String displayName) throws ContentManagerException {
        try {
            Files.writeString(path, "@@name=" + displayName + "\n\n// automatically generated mdp file stub/n");
        } catch (IOException e) {
            throw new ContentManagerException("Exception when writing configuration file ["
                    + path.toAbsolutePath().toString() + "]: "
                    + e.getMessage());
        }
    }

}
