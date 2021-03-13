package org.mentalizr.contentManager.fileHierarchy.contentRoot;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.contentFile.HtmlFile;
import org.mentalizr.contentManager.utils.ContentFileUtils;
import org.mentalizr.serviceObjects.frontend.program.Infotext;
import org.mentalizr.serviceObjects.frontend.program.Module;
import org.mentalizr.serviceObjects.frontend.program.Program;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HtmlDirTest {

    @Test
    public void getProgramConf() throws ProgramManagerException {
        HtmlDir htmlDir = new HtmlDir(new File("src/test/testPrograms/test1/html"));
        assertEquals("Test1", htmlDir.getProgramConfFile().getProgramConf().getName());
    }

    @Test
    public void getName() throws ProgramManagerException {
        HtmlDir htmlDir = new HtmlDir(new File("src/test/testPrograms/test1/html"));
        assertEquals("html", htmlDir.getName());
    }

    @Test
    public void getDisplayName() throws ProgramManagerException {
        HtmlDir htmlDir = new HtmlDir(new File("src/test/testPrograms/test1/html"));
        assertEquals("Test1", htmlDir.getDisplayName());
    }

    @Test
    public void getId() throws ProgramManagerException {
        HtmlDir htmlDir = new HtmlDir(new File("src/test/testPrograms/test1/html"));
        assertEquals("test1", htmlDir.getId());
    }

    @Test
    public void getContentFiles() throws ProgramManagerException {
        HtmlDir htmlDir = new HtmlDir(new File("src/test/testPrograms/test1/html"));
        List<HtmlFile> htmlFiles = htmlDir.getContentFiles();
        assertEquals(5, htmlFiles.size());
        assertTrue(ContentFileUtils.containsId(htmlFiles, "test1_m1_sm1_s1"));
        assertTrue(ContentFileUtils.containsId(htmlFiles, "test1_m1_sm1_s2"));
        assertTrue(ContentFileUtils.containsId(htmlFiles, "test1_m1_sm2_s1"));
        assertTrue(ContentFileUtils.containsId(htmlFiles, "test1_m2_sm1_s1"));
        assertTrue(ContentFileUtils.containsId(htmlFiles, "test1_m3_sm1_s1"));
    }

    @Test
    public void asProgram() throws ProgramManagerException {
        HtmlDir htmlDir = new HtmlDir(new File("src/test/testPrograms/test1/html"));
        Program program = htmlDir.asProgram();
        assertEquals("test1", program.getId());
        assertEquals("Test1", program.getName());
        List<Module> modules = program.getModules();
        assertEquals(3, modules.size());
        List<Infotext> infotexts = program.getInfotexts();
        assertEquals(1, infotexts.size());
    }

}