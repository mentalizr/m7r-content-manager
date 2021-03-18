package org.mentalizr.contentManager.fileHierarchy.levels.info;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.WrongFileTypeException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;
import org.mentalizr.serviceObjects.frontend.program.Infotext;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HtmlInfoDirTest {

    @Test
    void initWithWrongFileType_neg() throws ProgramManagerException {
        try {
            new HtmlInfoDir(new File("src/test/testPrograms/test1/html/_info/info1.html"));
            fail(WrongFileTypeException.class.getSimpleName() + " expected.");
        } catch (WrongFileTypeException e) {
            assertTrue(e.getMessage().contains("Directory expected."));
        }
    }

    @Test
    void getInfopageFiles() throws ProgramManagerException {
        HtmlInfoDir htmlInfoDir = new HtmlInfoDir(new File("src/test/testPrograms/test1/html/_info"));
        List<HtmlFile> infopageList = htmlInfoDir.getContentFiles();
        assertEquals(1, infopageList.size());
    }

    @Test
    void asInfotextList() throws ProgramManagerException {
        HtmlInfoDir htmlInfoDir = new HtmlInfoDir(new File("src/test/testPrograms/test1/html/_info"));
        List<Infotext> infotextList = htmlInfoDir.asInfotextList();
        assertEquals(1, infotextList.size());
        Infotext infotext = infotextList.get(0);
        assertEquals("test1__info_info1", infotext.getId());
        assertEquals("Info1", infotext.getName());
    }

}