package org.mentalizr.contentManager.fileHierarchy.levels.contentRoot;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.Program;
import org.mentalizr.contentManager.exceptions.ContentManagerException;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ProgramConfTest {

    @Test
    void requiredParameters() throws ContentManagerException {
        ProgramConf programConf = new ProgramConf(new File("src/test/programConfFiles/program1.conf"));
        assertEquals("Test", programConf.getName());
        assertEquals("TestTitle", programConf.getTitle());
        assertEquals("", programConf.getSubtitle());
    }

    @Test
    void allParameters() throws ContentManagerException {
        ProgramConf programConf = new ProgramConf(new File("src/test/programConfFiles/program2.conf"));
        assertEquals("Test", programConf.getName());
        assertEquals("TestFamily", programConf.getFamily());
        assertEquals("TestTitle", programConf.getTitle());
        assertEquals("TestSubtitle", programConf.getSubtitle());
        assertEquals("2024", programConf.getVersion());
        assertEquals("Joe Dummy", programConf.getAuthor());
        assertEquals("Foo Bar", programConf.getEditor());
        assertEquals("Test Copyright", programConf.getCopyright());
        assertEquals("Test License", programConf.getLicense());
        assertEquals("test_logo.png", programConf.getLogo());
    }

    @Test
    void missingName() {
        ContentManagerException e = assertThrows(
                ContentManagerException.class,
                () -> new ProgramConf(new File("src/test/programConfFiles/program3_neg.conf"))
        );
        assertTrue(e.getMessage().startsWith("Parameter [name] not set"));
    }

    @Test
    void missingTitle() {
        ContentManagerException e = assertThrows(
                ContentManagerException.class,
                () -> new ProgramConf(new File("src/test/programConfFiles/program4_neg.conf"))
        );
        assertTrue(e.getMessage().startsWith("Parameter [title] not set"));
    }

    @Test
    void missingNameEmpty() {
        ContentManagerException e = assertThrows(
                ContentManagerException.class,
                () -> new ProgramConf(new File("src/test/programConfFiles/program5_neg.conf"))
        );
        assertTrue(e.getMessage().startsWith("Parameter [name] not set"));
    }

}