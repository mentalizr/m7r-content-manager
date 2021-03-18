package org.mentalizr.contentManager.fileHierarchy.levels.contentFile;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.FileNotFoundException;
import org.mentalizr.serviceObjects.frontend.program.Infotext;
import org.mentalizr.serviceObjects.frontend.program.Step;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class HtmlFileTest {

    @Test
    public void construction() throws ProgramManagerException {
        new HtmlFile(new File("src/test/testPrograms/test1/html/_info/info1.html"));
    }

    @Test
    public void fileNotFound() throws ProgramManagerException {
        try {
            new HtmlFile(new File("notExisting.mdp"));
            fail(FileNotFoundException.class.getSimpleName() + " expected.");
        } catch (FileNotFoundException e) {
            // din
        }
    }

    @Test
    public void idOfContentHtml() throws ProgramManagerException {
        HtmlFile file = new HtmlFile(new File("src/test/testPrograms/test1/html/m1/sm1/s1.html"));
        assertEquals("test1_m1_sm1_s1", file.getId());
    }

    @Test
    public void idOfInfotextHtml() throws ProgramManagerException {
        HtmlFile htmlFile = new HtmlFile(new File("src/test/testPrograms/test1/html/_info/info1.html"));
        assertEquals("test1__info_info1", htmlFile.getId());
    }

    @Test
    public void getName() throws ProgramManagerException {
        HtmlFile htmlFile = new HtmlFile(new File("src/test/testPrograms/test1/html/_info/info1.html"));
        assertEquals("info1", htmlFile.getName());
    }

    @Test
    public void getDisplayName() throws ProgramManagerException {
        HtmlFile htmlFile = new HtmlFile(new File("src/test/testPrograms/test1/html/_info/info1.html"));
        assertEquals("Info1", htmlFile.getDisplayName());
    }

    @Test
    public void getFileType() throws ProgramManagerException {
        HtmlFile htmlFile = new HtmlFile(new File("src/test/testPrograms/test1/html/_info/info1.html"));
        assertEquals(".html", htmlFile.getFiletype());
    }

    @Test
    public void asStep() throws ProgramManagerException {
        HtmlFile htmlFile = new HtmlFile(new File("src/test/testPrograms/test1/html/m1/sm1/s1.html"));
        Step step = htmlFile.asStep();
        assertEquals("test1_m1_sm1_s1", step.getId());
        assertEquals("Step1", step.getName());
    }

    @Test
    public void asInfotext() throws ProgramManagerException {
        HtmlFile htmlFile = new HtmlFile(new File("src/test/testPrograms/test1/html/_info/info1.html"));
        Infotext infotext = htmlFile.asInfotext();
        assertEquals("test1__info_info1", infotext.getId());
        assertEquals("Info1", infotext.getName());
    }



}