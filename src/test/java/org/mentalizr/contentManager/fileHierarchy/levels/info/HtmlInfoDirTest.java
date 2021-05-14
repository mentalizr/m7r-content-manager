package org.mentalizr.contentManager.fileHierarchy.levels.info;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.WrongFileTypeException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;
import org.mentalizr.serviceObjects.frontend.program.InfotextSO;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HtmlInfoDirTest {

    @Test
    void initWithWrongFileType_neg() throws ContentManagerException {
        try {
            new HtmlInfoDir(new File("src/test/testPrograms/test1/html/_info/info1.html"));
            fail(WrongFileTypeException.class.getSimpleName() + " expected.");
        } catch (WrongFileTypeException e) {
            assertTrue(e.getMessage().contains("Directory expected."));
        }
    }

    @Test
    void getInfopageFiles() throws ContentManagerException {
        HtmlInfoDir htmlInfoDir = new HtmlInfoDir(new File("src/test/testPrograms/test1/html/_info"));
        List<HtmlFile> infopageList = htmlInfoDir.getContentFiles();
        assertEquals(1, infopageList.size());
    }

    @Test
    void asInfotextList() throws ContentManagerException {
        HtmlInfoDir htmlInfoDir = new HtmlInfoDir(new File("src/test/testPrograms/test1/html/_info"));
        List<InfotextSO> infotextSOList = htmlInfoDir.asInfotextList();
        assertEquals(1, infotextSOList.size());
        InfotextSO infotextSO = infotextSOList.get(0);
        assertEquals("test1__info_info1", infotextSO.getId());
        assertEquals("Info1", infotextSO.getName());
    }

}