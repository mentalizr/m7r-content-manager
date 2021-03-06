package org.mentalizr.contentManager;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ContentManagerException;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgramTest {

    @Test
    void getName() throws ContentManagerException {
        Path programPath = Paths.get("src/test/testPrograms/test1");
        Program program = new Program(programPath);
        String name = program.getName();
        assertEquals("test1", name);

    }
}