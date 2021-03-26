package org.mentalizr.contentManager.fileHierarchy.levels.program;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.testUtils.TempDir;
import org.mentalizr.contentManager.testUtils.TempDirs;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BuiltFlagFileTest {

    @Test
    void exists() throws IOException, ProgramManagerException {
        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
//        BuiltFlagFile builtFlagFile = new BuiltFlagFile(new File(tempDir.asFile(), "built.flag"));
        BuiltFlagFile builtFlagFile = new BuiltFlagFile(new File("not_existing"));
        assertFalse(builtFlagFile.exists());
    }

    @Test
    void touch() {
    }

    @Test
    void remove() {
    }

    @Test
    void getCreationTime() {
    }
}