package org.mentalizr.contentManager.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Nio2Helper {

    public static boolean isExistingRegularFile(Path file) {
        return (Files.exists(file) && Files.isRegularFile(file));
    }

    public static boolean isExistingDir(Path dir) {
        return (Files.exists(dir) && Files.isDirectory(dir));
    }

    public static boolean isPathEndingWithFileName(Path path, String fileName) {
        return (path.getFileName().toString().equals(fileName));
    }

    /**
     * Obtains all subdirectories for specified path. Specified path must be an existing directory.
     *
     * @param dir directory
     * @return a list of {@link Path} instances representing subdirectories
     * @throws IOException
     * @throws PathAssertionException if specified directory does not exist
     */
    public static List<Path> getSubdirectories(Path dir) throws IOException {
        PathAssertions.assertIsExistingDirectory(dir);
        List<Path> subdirectories = Files.walk(dir, 1).filter(Files::isDirectory).collect(Collectors.toList());
        // remove specified dir
        subdirectories.remove(0);
        return subdirectories;
    }

    public static boolean isSubdirectory(Path dir, Path subdir) {
        Path dirWork = dir.normalize().toAbsolutePath();
        PathAssertions.assertIsExistingDirectory(dir);
        Path subdirWork = subdir.normalize().toAbsolutePath();
        PathAssertions.assertIsExistingDirectory(subdirWork);

        return (!dirWork.equals(subdirWork) && subdirWork.startsWith(dirWork));
    }

    public static boolean isDirectSubdirectory(Path dir, Path subdir) {
        Path dirWork = dir.normalize().toAbsolutePath();
        if (!isExistingDir(dir)) return false;
        int nameCountDirWork = dirWork.getNameCount();

        Path subdirWork = subdir.normalize().toAbsolutePath();
        if (!isExistingDir(subdirWork)) return false;
        int nameCountSubdirWork = subdirWork.getNameCount();

        boolean oneLonger = (nameCountSubdirWork - nameCountDirWork == 1);

        return (!dirWork.equals(subdirWork) && subdirWork.startsWith(dirWork) && oneLonger);
    }

    public static Path getCurrentWorkingDir() {
        return Paths.get(".").normalize().toAbsolutePath();
    }

    public static List<Path> getRegularFilesInDirectory(Path dir) throws IOException {
        PathAssertions.assertIsExistingDirectory(dir);
        try (Stream<Path> stream = Files.list(dir)) {
            return stream.filter(Files::isRegularFile).collect(Collectors.toList());
        }
    }

}
