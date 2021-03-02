package org.mentalizr.contentManager;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ProgramDirTest {

    @Test
    public void test() throws ProgramManagerException {

        ProgramDir programDir = new ProgramDir(new File("src/test/testPrograms/test1"));

        assertEquals("test1", programDir.getProgramId());
        assertEquals("Test1", programDir.getDisplayName());

        assertEquals("test1", programDir.getProgram().getId());
        assertEquals("Test1", programDir.getProgram().getName());

    }

}