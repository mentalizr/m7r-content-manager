package org.mentalizr.contentManager.testUtils;

import de.arthurpicht.utils.io.nio2.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class TempDirs {

    private static final String testTempRoot = "testTemp";

    public static TempDir createUniqueTempDir() throws IOException {
        assureTempRootExistence();
        Path temp = Paths.get(testTempRoot, UUID.randomUUID().toString());
        Files.createDirectory(temp);
        return new TempDir(temp, false);
    }

    public static TempDir createUniqueTempDirAutoClean() throws IOException {
        assureTempRootExistence();
        Path temp = Paths.get(testTempRoot, UUID.randomUUID().toString());
        Files.createDirectory(temp);
        FileUtils.forceDeleteOnShutdown(temp);
        return new TempDir(temp, true);
    }

    private static void assureTempRootExistence() throws IOException {
        Path rootPath = Paths.get(testTempRoot);

        if (Files.exists(rootPath) && Files.isDirectory(rootPath)) return;

        if (Files.exists(rootPath) && !Files.isDirectory(rootPath))
            throw new IOException("Can not create " + rootPath.toAbsolutePath().toString() + " since a non-directory" +
                    " file is preexisting with that name.");

        Files.createDirectory(rootPath);

        Path gitIgnoreFile = Paths.get(testTempRoot, ".gitignore");
        Files.writeString(gitIgnoreFile, "*\n*/\n!.gitignore\n");
    }

}
