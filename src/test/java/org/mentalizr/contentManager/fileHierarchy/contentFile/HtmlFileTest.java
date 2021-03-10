package org.mentalizr.contentManager.fileHierarchy.contentFile;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class HtmlFileTest {

    @Test
    public void test() throws ProgramManagerException {

        HtmlFile htmlFile = new HtmlFile(new File("src/test/testPrograms/test1/html/_info/info1.html"));

        assertEquals("info1.html", htmlFile.getName());
        assertEquals("Info1", htmlFile.getDisplayName());
    }

    @Test
    public void id() throws ProgramManagerException {
        HtmlFile file = new HtmlFile(new File("src/test/testPrograms/test1/html/m1/sm1/s1.html"));
        assertEquals("test1_m1_sm1_s1", file.getId());
    }

}