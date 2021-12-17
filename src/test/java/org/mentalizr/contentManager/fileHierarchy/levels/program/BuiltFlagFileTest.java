package org.mentalizr.contentManager.fileHierarchy.levels.program;

import de.arthurpicht.utils.io.tempDir.TempDir;
import de.arthurpicht.utils.io.tempDir.TempDirs;
import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.TestConfig;
import org.mentalizr.contentManager.exceptions.ContentManagerException;

import java.io.IOException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BuiltFlagFileTest {

    @Test
    void exists() throws IOException, ContentManagerException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        BuiltFlagFile builtFlagFile = new BuiltFlagFile(tempDir.asPath());
        assertFalse(builtFlagFile.exists());

        builtFlagFile.touch();
        assertTrue(builtFlagFile.exists());
    }

    @Test
    void getCreationTime() throws ContentManagerException, IOException {

        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
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
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        BuiltFlagFile builtFlagFile = new BuiltFlagFile(tempDir.asPath());
        assertFalse(builtFlagFile.exists());

        builtFlagFile.touch();
        assertTrue(builtFlagFile.exists());

        builtFlagFile.remove();
        assertFalse(builtFlagFile.exists());
    }

}