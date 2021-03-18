package org.mentalizr.contentManager.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Nio2Helper {

    public static List<Path> getSubDirectories(Path dir) throws IOException {
        PathAssertions.assertExistingDirectory(dir);
        return Files.walk(dir, 1).filter(Files::isDirectory).collect(Collectors.toList());
    }

}
