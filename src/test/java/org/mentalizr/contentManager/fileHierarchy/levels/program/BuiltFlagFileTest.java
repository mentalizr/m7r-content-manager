package org.mentalizr.contentManager.fileHierarchy.levels.program;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.testUtils.TempDir;
import org.mentalizr.contentManager.testUtils.TempDirs;

import java.io.IOException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class BuiltFlagFileTest {

    @Test
    void exists() throws IOException, ContentManagerException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
        BuiltFlagFile builtFlagFile = new BuiltFlagFile(tempDir.asPath());
        assertFalse(builtFlagFile.exists());

        builtFlagFile.touch();
        assertTrue(builtFlagFile.exists());
    }

    @Test
    void getCreationTime() throws ContentManagerException, IOException {

        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
        BuiltFlagFile builtFlagFile = new BuiltFlagFile(tempDir.asPath());

        Instant preTimestamp = Instant.now();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // din
        }

        builtFlagFile.touch();
        Instant creationTimestamp = builtFlagFile.getCreationTime();
        Instant postTimestsamp = Instant.now();

        System.out.println(preTimestamp);
        System.out.println(creationTimestamp);
        System.out.println(postTimestsamp);

        assertTrue(preTimestamp.isBefore(creationTimestamp));
        assertTrue(creationTimestamp.isBefore(postTimestsamp));
    }

    @Test
    void remove() throws IOException, ContentManagerException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
        BuiltFlagFile builtFlagFile = new BuiltFlagFile(tempDir.asPath());
        assertFalse(builtFlagFile.exists());

        builtFlagFile.touch();
        assertTrue(builtFlagFile.exists());

        builtFlagFile.remove();
        assertFalse(builtFlagFile.exists());
    }

}