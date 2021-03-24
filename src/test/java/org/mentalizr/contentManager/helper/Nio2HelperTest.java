package org.mentalizr.contentManager.helper;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.testUtils.TempDir;
import org.mentalizr.contentManager.testUtils.TempDirs;

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
        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
        assertTrue(Nio2Helper.isExistingDir(tempDir.asPath()));
    }

    @Test
    void isExistingDir_neg() throws IOException {
        assertFalse(Nio2Helper.isExistingDir(Paths.get(UUID.randomUUID().toString())));
    }

    @Test
    void getSubdirectories() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();

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
    void getSubdirectoriesEmpty() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
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
        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
        Path subDir = tempDir.asPath().resolve("sub1");
        Files.createDirectory(subDir);

        assertTrue(Nio2Helper.isSubdirectory(tempDir.asPath(), subDir));
    }

    @Test
    void isSubdirectorySubSub() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
        Path subDir = tempDir.asPath().resolve("sub1").resolve("sub2");
        Files.createDirectories(subDir);

        assertTrue(Nio2Helper.isSubdirectory(tempDir.asPath(), subDir));
    }

    @Test
    void isSubdirectory_neg() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
        assertFalse(Nio2Helper.isSubdirectory(tempDir.asPath(), tempDir.asPath()));
        assertFalse(Nio2Helper.isSubdirectory(tempDir.asPath(), tempDir.asPath().resolve("..")));
    }

    @Test
    void isSubdirectory_notExistingSubDir_neg() throws IOException {
         TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
         try {
             Nio2Helper.isSubdirectory(tempDir.asPath(), tempDir.asPath().resolve("not_existing"));
             fail(PathAssertionException.class.getSimpleName() + " expected");
         } catch (PathAssertionException e) {
             // din
         }
     }

    @Test
    void isSubdirectory_notExistingDir_neg() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
        try {
            Nio2Helper.isSubdirectory(Paths.get(UUID.randomUUID().toString()), tempDir.asPath());
            fail(PathAssertionException.class.getSimpleName() + " expected");
        } catch (PathAssertionException e) {
            // din
        }
    }

    @Test
    void isDirectSubdirectory() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
        Path subDir = tempDir.asPath().resolve("sub");
        Files.createDirectory(subDir);

        assertTrue(Nio2Helper.isDirectSubdirectory(tempDir.asPath(), subDir));
    }

    @Test
    void isDirectSubdirectorySubSub() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
        Path subDir = tempDir.asPath().resolve("sub1").resolve("sub2");
        Files.createDirectories(subDir);

        assertFalse(Nio2Helper.isDirectSubdirectory(tempDir.asPath(), subDir));
    }

    @Test
    void isDirectSubdirectoryNotNormalized() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
        Path subDir = tempDir.asPath().resolve("sub1");
        Files.createDirectory(subDir);

        assertTrue(Nio2Helper.isDirectSubdirectory(tempDir.asPath(), tempDir.asPath().resolve("sub1").resolve("..").resolve("sub1")));
    }

    @Test
    void isDirectSubdirectoryNotNormalizedSubSub() throws IOException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
        Path subDir = tempDir.asPath().resolve("sub1").resolve("sub2");
        Files.createDirectories(subDir);

        assertFalse(Nio2Helper.isDirectSubdirectory(tempDir.asPath(), tempDir.asPath().resolve("sub1").resolve("..").resolve("sub1").resolve("sub2")));
    }


}