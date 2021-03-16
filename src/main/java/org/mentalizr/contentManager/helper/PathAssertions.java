package org.mentalizr.contentManager.helper;

import java.nio.file.Files;
import java.nio.file.Path;

public class PathAssertions {

    public static void assertFileName(Path path, String fileName) {
        if (!path.getFileName().toString().equals(fileName))
            throw new RuntimeException("Assertion failed. File name [" + fileName + "] expected " +
                    "but is [" + path.getFileName() + "].");
    }

    public static void assertExistingDirectory(Path path) {
        if (!Files.exists(path) || !Files.isDirectory(path))
            throw new PathAssertionException(path, "Assertion failed. Existing directory expected: ["
                    + path.toAbsolutePath().toString() + "].");
    }

}
