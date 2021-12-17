package org.mentalizr.contentManager.fileHierarchy.basics;

import de.arthurpicht.utils.io.tempDir.TempDir;
import de.arthurpicht.utils.io.tempDir.TempDirs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.TestConfig;
import org.mentalizr.contentManager.exceptions.ContentManagerException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class RepoFileTest {

    @Test
    void init_readPermissionWithoutRequiredExistence() throws IOException, ContentManagerException {

        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        Path file = Files.createFile(tempDir.asPath().resolve("testFile.test"));

        new RepoFile(file.toFile()) {
            @Override
            protected String getFiletype() {
                return ".test";
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

        };
    }

    @Test
    void init_writePermissionWithoutRequiredExistence() throws IOException, ContentManagerException {

        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        Path file = Files.createFile(tempDir.asPath().resolve("testFile.test"));

        new RepoFile(file.toFile()) {
            @Override
            protected String getFiletype() {
                return ".test";
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

        };
    }

    @Test
    void errorOnInit_neg_readPermissionWithoutRequiredExistence() {

        Assertions.assertThrows(AssertionError.class, () -> new RepoFile(new File("not_existing.test")) {
            @Override
            protected String getFiletype() {
                return ".test";
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
                return false;
            }

        });
    }

    @Test
    void errorOnInit_neg_writePermissionWithoutRequiredExistence() {

        Assertions.assertThrows(AssertionError.class, () -> new RepoFile(new File("not_existing.test")) {
            @Override
            protected String getFiletype() {
                return ".test";
            }

            @Override
            public boolean requiresExistence() {
                return false;
            }

            @Override
            public boolean requiresReadPermission() {
                return false;
            }

            @Override
            public boolean requiresWritePermission() {
                return true;
            }

        });
    }

}