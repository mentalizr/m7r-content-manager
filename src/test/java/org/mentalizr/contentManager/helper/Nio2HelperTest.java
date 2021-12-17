package org.mentalizr.contentManager.helper;

import de.arthurpicht.utils.io.tempDir.TempDir;
import de.arthurpicht.utils.io.tempDir.TempDirs;
import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.TestConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class Nio2HelperTest {

    @Test
    void isExistingDir() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        assertTrue(Nio2Helper.isExistingDir(tempDir.asPath()));
    }

    @Test
    void isExistingDir_neg() {
        assertFalse(Nio2Helper.isExistingDir(Paths.get(UUID.randomUUID().toString())));
    }

    @Test
    void getSubdirectories() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);

        Path subdirectory1 = tempDir.asPath().resolve(UUID.randomUUID().toString());
        Files.createDirectory(subdirectory1);
        assertTrue(Nio2Helper.isExistingDir(subdirectory1));

        Path subdirectory2 = tempDir.asPath().resolve(UUID.randomUUID().toString());
        Files.createDirectory(subdirectory2);
        assertTrue(Nio2Helper.isExistingDir(subdirectory2));

        Path subdirectory3 = tempDir.asPath().resolve(UUID.randomUUID().toString());
        Files.createDirectory(subdirectory3);
        assertTrue(Nio2Helper.isExistingDir(subdirectory3));

        List<Path> subdirectories = Nio2Helper.getSubdirectories(tempDir.asPath());

        assertEquals(3, subdirectories.size());
        assertTrue(subdirectories.contains(subdirectory1));
        assertTrue(subdirectories.contains(subdirectory2));
        assertTrue(subdirectories.contains(subdirectory3));
    }

    @Test
    void getActiveSubdirectories() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);

        Path subdirectory1 = tempDir.asPath().resolve(UUID.randomUUID().toString());
        Files.createDirectory(subdirectory1);
        assertTrue(Nio2Helper.isExistingDir(subdirectory1));

        Path subdirectory2Deactivated = tempDir.asPath().resolve(UUID.randomUUID() + "~");
        assertTrue(subdirectory2Deactivated.getFileName().toString().endsWith("~"));

        Files.createDirectory(subdirectory2Deactivated);
        assertTrue(Nio2Helper.isExistingDir(subdirectory2Deactivated));

        Path subdirectory3 = tempDir.asPath().resolve(UUID.randomUUID().toString());
        Files.createDirectory(subdirectory3);
        assertTrue(Nio2Helper.isExistingDir(subdirectory3));

        List<Path> subdirectories = Nio2Helper.getActiveSubdirectories(tempDir.asPath());

        assertEquals(2, subdirectories.size());
        assertTrue(subdirectories.contains(subdirectory1));
        assertTrue(subdirectories.contains(subdirectory3));
    }

    @Test
    void getSubdirectoriesEmpty() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        List<Path> subdirectories = Nio2Helper.getSubdirectories(tempDir.asPath());
        assertEquals(0, subdirectories.size());
    }

    @Test
    void getSubdirectories_neg() throws IOException {
        try {
            Nio2Helper.getSubdirectories(Paths.get(UUID.randomUUID().toString()));
            fail(PathAssertionException.class.getSimpleName() + " expected.");
        } catch (PathAssertionException e) {
            // din
        }
    }

    @Test
    void isSubdirectory() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        Path subDir = tempDir.asPath().resolve("sub1");
        Files.createDirectory(subDir);

        assertTrue(Nio2Helper.isSubdirectory(tempDir.asPath(), subDir));
    }

    @Test
    void isSubdirectorySubSub() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        Path subDir = tempDir.asPath().resolve("sub1").resolve("sub2");
        Files.createDirectories(subDir);

        assertTrue(Nio2Helper.isSubdirectory(tempDir.asPath(), subDir));
    }

    @Test
    void isSubdirectory_neg() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        assertFalse(Nio2Helper.isSubdirectory(tempDir.asPath(), tempDir.asPath()));
        assertFalse(Nio2Helper.isSubdirectory(tempDir.asPath(), tempDir.asPath().resolve("..")));
    }

    @Test
    void isSubdirectory_notExistingSubDir_neg() throws IOException {
         TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
         try {
             Nio2Helper.isSubdirectory(tempDir.asPath(), tempDir.asPath().resolve("not_existing"));
             fail(PathAssertionException.class.getSimpleName() + " expected");
         } catch (PathAssertionException e) {
             // din
         }
     }

    @Test
    void isSubdirectory_notExistingDir_neg() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        try {
            Nio2Helper.isSubdirectory(Paths.get(UUID.randomUUID().toString()), tempDir.asPath());
            fail(PathAssertionException.class.getSimpleName() + " expected");
        } catch (PathAssertionException e) {
            // din
        }
    }

    @Test
    void isDirectSubdirectory() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        Path subDir = tempDir.asPath().resolve("sub");
        Files.createDirectory(subDir);

        assertTrue(Nio2Helper.isDirectSubdirectory(tempDir.asPath(), subDir));
    }

    @Test
    void isDirectSubdirectorySubSub() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        Path subDir = tempDir.asPath().resolve("sub1").resolve("sub2");
        Files.createDirectories(subDir);

        assertFalse(Nio2Helper.isDirectSubdirectory(tempDir.asPath(), subDir));
    }

    @Test
    void isDirectSubdirectoryNotNormalized() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        Path subDir = tempDir.asPath().resolve("sub1");
        Files.createDirectory(subDir);

        assertTrue(Nio2Helper.isDirectSubdirectory(tempDir.asPath(), tempDir.asPath().resolve("sub1").resolve("..").resolve("sub1")));
    }

    @Test
    void isDirectSubdirectoryNotNormalizedSubSub() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        Path subDir = tempDir.asPath().resolve("sub1").resolve("sub2");
        Files.createDirectories(subDir);

        assertFalse(Nio2Helper.isDirectSubdirectory(tempDir.asPath(), tempDir.asPath().resolve("sub1").resolve("..").resolve("sub1").resolve("sub2")));
    }

    @Test
    void getRegularNonHiddenFilesInDirectory() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        Files.createFile(tempDir.asPath().resolve("a"));
        Files.createFile(tempDir.asPath().resolve("b"));
        Files.createFile(tempDir.asPath().resolve(".c"));
        Files.createDirectory(tempDir.asPath().resolve("dir"));

        List<Path> fileList = Nio2Helper.getRegularNonHiddenFilesInDirectory(tempDir.asPath());

        assertEquals(2, fileList.size());
    }

}