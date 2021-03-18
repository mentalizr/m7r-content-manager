package org.mentalizr.contentManager.fileHierarchy.levels.media;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.MalformedMediaResourceNameException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.NoSuchMediaResourceException;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class MediaDirTest {

    @Test
    public void getProgramName() throws ProgramManagerException {
        MediaDir mediaDir = new MediaDir(new File("src/test/testPrograms/test1/media"));
        assertEquals("test1", mediaDir.getProgramName());
    }

    @Test
    public void getMediaResource() throws ProgramManagerException {
        MediaDir mediaDir = new MediaDir(new File("src/test/testPrograms/test1/media"));
        assertTrue(Files.exists(mediaDir.getMediaResource("dummy.txt")));
    }

    @Test
    public void getMediaResource_neg_noSuchResource() throws ProgramManagerException {
        MediaDir mediaDir = new MediaDir(new File("src/test/testPrograms/test1/media"));
        try {
            mediaDir.getMediaResource("notExisting.postfix");
            fail(NoSuchMediaResourceException.class.getSimpleName() + " expected.");
        } catch (NoSuchMediaResourceException e) {
            assertEquals("notExisting.postfix", e.getFileName());
            assertEquals("test1", e.getProgramName());
        }
    }

    @Test
    public void getMediaResource_neg_Malformed() throws ProgramManagerException {
        MediaDir mediaDir = new MediaDir(new File("src/test/testPrograms/test1/media"));
        try {
            mediaDir.getMediaResource("../malicious.postfix");
            fail(MalformedMediaResourceNameException.class.getSimpleName() + " expected.");
        } catch (MalformedMediaResourceNameException e) {
            assertEquals("../malicious.postfix", e.getResourceName());
        }
    }

}