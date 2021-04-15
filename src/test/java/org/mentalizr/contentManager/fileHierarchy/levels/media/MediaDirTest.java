package org.mentalizr.contentManager.fileHierarchy.levels.media;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.MalformedMediaResourceNameException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.NoSuchMediaResourceException;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class MediaDirTest {

    @Test
    public void getProgramName() throws ContentManagerException {
        MediaDir mediaDir = new MediaDir(new File("src/test/testPrograms/test1/media"));
        assertEquals("test1", mediaDir.getProgramName());
    }

    @Test
    public void getMediaResource() throws ContentManagerException {
        MediaDir mediaDir = new MediaDir(new File("src/test/testPrograms/test1/media"));
        assertTrue(Files.exists(mediaDir.getMediaResource("dummy.txt")));
    }

    @Test
    public void getMediaResource_neg_noSuchResource() throws ContentManagerException {
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
    public void getMediaResource_neg_Malformed() throws ContentManagerException {
        MediaDir mediaDir = new MediaDir(new File("src/test/testPrograms/test1/media"));
        try {
            mediaDir.getMediaResource("../malicious.postfix");
            fail(MalformedMediaResourceNameException.class.getSimpleName() + " expected.");
        } catch (MalformedMediaResourceNameException e) {
            assertEquals("../malicious.postfix", e.getResourceName());
        }
    }

}