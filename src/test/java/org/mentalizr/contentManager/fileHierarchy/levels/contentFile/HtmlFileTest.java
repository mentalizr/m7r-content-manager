package org.mentalizr.contentManager.fileHierarchy.levels.contentFile;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.programStructure.Infotext;
import org.mentalizr.contentManager.programStructure.Step;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class HtmlFileTest {

    @Test
    public void construction() throws ContentManagerException {
        new HtmlFile(new File("src/test/testPrograms/test1/html/_info/info1.html"));
    }

    @Test
    public void fileNotFound() throws ContentManagerException {
        try {
            new HtmlFile(new File("notExisting.mdp"));
            fail(FileNotFoundException.class.getSimpleName() + " expected.");
        } catch (FileNotFoundException e) {
            // din
        }
    }

    @Test
    public void idOfContentHtml() throws ContentManagerException {
        HtmlFile file = new HtmlFile(new File("src/test/testPrograms/test1/html/m1/sm1/s1.html"));
        assertEquals("test1_m1_sm1_s1", file.getId());
    }

    @Test
    public void idOfInfotextHtml() throws ContentManagerException {
        HtmlFile htmlFile = new HtmlFile(new File("src/test/testPrograms/test1/html/_info/info1.html"));
        assertEquals("test1__info_info1", htmlFile.getId());
    }

    @Test
    public void getName() throws ContentManagerException {
        HtmlFile htmlFile = new HtmlFile(new File("src/test/testPrograms/test1/html/_info/info1.html"));
        assertEquals("info1", htmlFile.getName());
    }

    @Test
    public void getDisplayName() throws ContentManagerException {
        HtmlFile htmlFile = new HtmlFile(new File("src/test/testPrograms/test1/html/_info/info1.html"));
        assertEquals("Info1", htmlFile.getDisplayName());
    }

    @Test
    public void isFeedbackNeg() throws ContentManagerException {
        HtmlFile htmlFile = new HtmlFile(new File("src/test/testPrograms/test1/html/m1/sm1/s1.html"));
        assertFalse(htmlFile.isFeedback());
    }

    @Test
    public void isFeedbackPos() throws ContentManagerException {
        HtmlFile htmlFile = new HtmlFile(new File("src/test/testPrograms/test1/html/m1/sm1/s2.html"));
        assertTrue(htmlFile.isFeedback());
    }

    @Test
    public void getFileType() throws ContentManagerException {
        HtmlFile htmlFile = new HtmlFile(new File("src/test/testPrograms/test1/html/_info/info1.html"));
        assertEquals(".html", htmlFile.getFiletype());
    }

    @Test
    public void asStep() throws ContentManagerException {
        HtmlFile htmlFile = new HtmlFile(new File("src/test/testPrograms/test1/html/m1/sm1/s1.html"));
        Step step = htmlFile.asStep();
        assertEquals("test1_m1_sm1_s1", step.getId());
        assertEquals("Step1", step.getName());
        assertFalse(step.isFeedback());
    }

    @Test
    public void asStepWithFeedback() throws ContentManagerException {
        HtmlFile htmlFile = new HtmlFile(new File("src/test/testPrograms/test1/html/m1/sm1/s2.html"));
        Step step = htmlFile.asStep();
        assertTrue(step.isFeedback());
    }

    @Test
    public void asInfotext() throws ContentManagerException {
        HtmlFile htmlFile = new HtmlFile(new File("src/test/testPrograms/test1/html/_info/info1.html"));
        Infotext infotext = htmlFile.asInfotext();
        assertEquals("test1__info_info1", infotext.getId());
        assertEquals("Info1", infotext.getName());
    }

}