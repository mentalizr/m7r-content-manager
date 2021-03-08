package org.mentalizr.contentManager.fileHierarchy.media;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class MediaDirTest {

    @Test
    public void test() throws ProgramManagerException {
        MediaDir mediaDir = new MediaDir(new File("src/test/testPrograms/test1/media"));
        assertTrue(mediaDir.hasFile("dummy.txt"));
        assertFalse(mediaDir.hasFile("notExisting"));
    }

}