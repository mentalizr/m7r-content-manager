package org.mentalizr.contentManager.fileHierarchy.levels.contentRoot;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;
import org.mentalizr.contentManager.utils.ContentFileUtils;
import org.mentalizr.serviceObjects.frontend.program.InfotextSO;
import org.mentalizr.serviceObjects.frontend.program.ModuleSO;
import org.mentalizr.serviceObjects.frontend.program.ProgramSO;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HtmlDirTest {

    @Test
    public void getProgramConf() throws ContentManagerException {
        HtmlDir htmlDir = new HtmlDir(new File("src/test/testPrograms/test1/html"));
        assertEquals("Test1", htmlDir.getProgramConfFile().getProgramConf().getName());
    }

    @Test
    public void getName() throws ContentManagerException {
        HtmlDir htmlDir = new HtmlDir(new File("src/test/testPrograms/test1/html"));
        assertEquals("html", htmlDir.getName());
    }

    @Test
    public void getDisplayName() throws ContentManagerException {
        HtmlDir htmlDir = new HtmlDir(new File("src/test/testPrograms/test1/html"));
        assertEquals("Test1", htmlDir.getDisplayName());
    }

    @Test
    public void getId() throws ContentManagerException {
        HtmlDir htmlDir = new HtmlDir(new File("src/test/testPrograms/test1/html"));
        assertEquals("test1", htmlDir.getId());
    }

    @Test
    public void getContentFiles() throws ContentManagerException {
        HtmlDir htmlDir = new HtmlDir(new File("src/test/testPrograms/test1/html"));
        List<HtmlFile> htmlFiles = htmlDir.getContentFiles();
        assertEquals(6, htmlFiles.size());
        assertTrue(ContentFileUtils.containsId(htmlFiles, "test1_m1_sm1_s1"));
        assertTrue(ContentFileUtils.containsId(htmlFiles, "test1_m1_sm1_s2"));
        assertTrue(ContentFileUtils.containsId(htmlFiles, "test1_m1_sm2_s1"));
        assertTrue(ContentFileUtils.containsId(htmlFiles, "test1_m2_sm1_s1"));
        assertTrue(ContentFileUtils.containsId(htmlFiles, "test1_m3_sm1_s1"));
        assertTrue(ContentFileUtils.containsId(htmlFiles, "test1__info_info1"));
    }

    @Test
    public void asProgram() throws ContentManagerException {
        HtmlDir htmlDir = new HtmlDir(new File("src/test/testPrograms/test1/html"));
        ProgramSO programSO = htmlDir.asProgram();
        assertEquals("test1", programSO.getId());
        assertEquals("Test1", programSO.getName());
        List<ModuleSO> moduleSOs = programSO.getModules();
        assertEquals(3, moduleSOs.size());
        List<InfotextSO> infotextSOs = programSO.getInfotexts();
        assertEquals(1, infotextSOs.size());
    }

}