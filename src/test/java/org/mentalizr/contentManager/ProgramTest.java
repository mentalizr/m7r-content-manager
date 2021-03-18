package org.mentalizr.contentManager;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.program.ProgramDir;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {

    @Test
    void getName() throws ProgramManagerException {
        Path programPath = Paths.get("src/test/testPrograms/test1");
        Program program = new Program(programPath);
        String name = program.getName();
        assertEquals("test1", name);

    }
}