package org.mentalizr.contentManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class FileSystemHelper {

    // See https://stackoverflow.com/questions/58059463/find-the-deepest-file-directory-in-the-tree-java
    public static Path findDeepest(Path path) throws IOException {
        Objects.requireNonNull(path);
        if (!Files.exists(path)) throw new IllegalArgumentException("No such path: " + path.toAbsolutePath().toString());

        try (Stream<Path> s = Files.walk(path)) {
            Optional<Path> deepestOptional = s.filter(Files::isRegularFile).map(path::relativize).max(Comparator.comparing(Path::getNameCount));
            if (deepestOptional.isEmpty()) throw new IllegalStateException();
            return deepestOptional.get();
        }
    }

    public static int getDepth(Path path) throws IOException {
        Objects.requireNonNull(path);
        if (!Files.exists(path)) throw new IllegalArgumentException("No such path: " + path.toAbsolutePath().toString());

        Path deepestPath = findDeepest(path);
        boolean isRegularFile = Files.isRegularFile(deepestPath);
        return deepestPath.getNameCount() - (isRegularFile ? 1 : 0);
    }

    public static void deleteDirectoryRecursively(Path path) throws IOException {
        Files.walk(path)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

}