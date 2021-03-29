package org.mentalizr.contentManager.fileHierarchy.basics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.testUtils.TempDir;
import org.mentalizr.contentManager.testUtils.TempDirs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class RepoFileTest {

    @Test
    void init_readPermissionWithoutRequiredExistence() throws IOException, ProgramManagerException {

        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
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

//            @Override
//            protected boolean requireContainingDir() {
//                return false;
//            }
        };
    }

    @Test
    void init_writePermissionWithoutRequiredExistence() throws IOException, ProgramManagerException {

        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
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

//            @Override
//            protected boolean requireContainingDir() {
//                return false;
//            }
        };
    }

    @Test
    void errorOnInit_neg_readPermissionWithoutRequiredExistence() {

        Assertions.assertThrows(AssertionError.class, () -> {
            new RepoFile(new File("not_existing.test")) {
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

//                @Override
//                protected boolean requireContainingDir() {
//                    return false;
//                }
            };
        });
    }

    @Test
    void errorOnInit_neg_writePermissionWithoutRequiredExistence() {

        Assertions.assertThrows(AssertionError.class, () -> {
            new RepoFile(new File("not_existing.test")) {
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

//                @Override
//                protected boolean requireContainingDir() {
//                    return false;
//                }
            };
        });
    }

}