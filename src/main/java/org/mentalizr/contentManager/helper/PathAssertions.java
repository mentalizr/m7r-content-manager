package org.mentalizr.contentManager.helper;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Checks assertions on path instances. Throws {@link PathAssertionException} if assertion fails.
 */
public class PathAssertions {

    /**
     * Asserts that the file denoted by a specified path instance equals to the specified file name.
     *
     * @param file
     * @param fileName
     */
    public static void assertFileName(Path file, String fileName) {
        if (!Nio2Helper.isPathEndingWithFileName(file, fileName))
            throw new PathAssertionException(file, "Assertion failed. File name [" + fileName + "] expected " +
                    "but is [" + file.getFileName() + "].");
    }

    public static void assertIsExistingDirectory(Path dir) {
        if (!Nio2Helper.isExistingDir(dir))
            throw new PathAssertionException(dir, "Assertion failed. Existing directory expected: ["
                    + dir.toAbsolutePath().toString() + "].");
    }

    public static void assertPathNotExisting(Path path) {
        if (Files.exists(path))
            throw new PathAssertionException(path, "Assertion failed. Path as not existing expected : ["
                    + path.toAbsolutePath().toString() + "].");
    }

    public static void assertIsDirectSubdirectory(Path dir, Path subdir) {
        if (!Nio2Helper.isDirectSubdirectory(dir, subdir))
            throw new PathAssertionException(subdir, "Assertion failed. [" + subdir.toAbsolutePath().toString() + "] " +
                    "is no direct subdirectory of [" + dir.toAbsolutePath().toString() + "].");
    }

}
