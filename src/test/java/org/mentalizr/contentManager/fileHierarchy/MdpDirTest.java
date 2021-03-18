package org.mentalizr.contentManager.fileHierarchy;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.MdpDir;
import org.mentalizr.contentManager.utils.ContentFileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MdpDirTest {

    @Test
    public void getModuleDirNames() throws ProgramManagerException {
        MdpDir mdpDir = new MdpDir(new File("src/test/testPrograms/test1/mdp"));
        assertEquals(Arrays.asList("m1", "m2", "m3"), mdpDir.getModuleDirNames());
    }

    @Test
    public void hasModuleDir() throws ProgramManagerException {
        MdpDir mdpDir = new MdpDir(new File("src/test/testPrograms/test1/mdp"));
        assertTrue(mdpDir.hasModuleDir("m1"));
        assertFalse(mdpDir.hasModuleDir("not_existing"));
    }

    @Test
    public void getModuleDirs() throws ProgramManagerException {
        MdpDir mdpDir = new MdpDir(new File("src/test/testPrograms/test1/mdp"));
        assertEquals(3, mdpDir.getModuleDirs().size());
    }

    @Test
    public void getProgramConf() throws ProgramManagerException {
        MdpDir mdpDir = new MdpDir(new File("src/test/testPrograms/test1/mdp"));
        assertEquals("Test1", mdpDir.getProgramConfFile().getProgramConf().getName());
    }

    @Test
    public void getName() throws ProgramManagerException {
        MdpDir mdpDir = new MdpDir(new File("src/test/testPrograms/test1/mdp"));
        assertEquals("mdp", mdpDir.getName());
    }

    @Test
    public void getContentFiles() throws ProgramManagerException {
        MdpDir mdpDir = new MdpDir(new File("src/test/testPrograms/test1/mdp"));
        List<MdpFile> mdpFiles = mdpDir.getContentFiles();
        assertEquals(6, mdpFiles.size());
        assertTrue(ContentFileUtils.containsId(mdpFiles, "test1_m1_sm1_s1"));
        assertTrue(ContentFileUtils.containsId(mdpFiles, "test1_m1_sm1_s2"));
        assertTrue(ContentFileUtils.containsId(mdpFiles, "test1_m1_sm2_s1"));
        assertTrue(ContentFileUtils.containsId(mdpFiles, "test1_m2_sm1_s1"));
        assertTrue(ContentFileUtils.containsId(mdpFiles, "test1_m3_sm1_s1"));
        assertTrue(ContentFileUtils.containsId(mdpFiles, "test1__info_info1"));
    }

    @Test
    public void getInfoDir() throws ProgramManagerException {
        MdpDir mdpDir = new MdpDir(new File("src/test/testPrograms/test1/mdp"));
        assertEquals("_info", mdpDir.getInfoDir().getName());
    }

    @Test
    public void noModulesFound_neg() throws ProgramManagerException {
        try {
            new MdpDir(new File("src/test/testPrograms/test_neg_1/mdp"));
            fail(FileNotFoundException.class.getSimpleName() + " expected.");
        } catch (FileNotFoundException e) {
            assertTrue(e.getMessage().contains("No modules found"));
        }
    }

    @Test
    public void programConfMissing_neg() throws ProgramManagerException {
        try {
            new MdpDir(new File("src/test/testPrograms/test_neg_2/mdp"));
            fail(FileNotFoundException.class.getSimpleName() + " expected.");
        } catch (FileNotFoundException e) {
            assertTrue(e.getMessage().contains("File not found"));
            assertTrue(e.getMessage().contains("test_neg_2/mdp/program.conf"));
        }
    }

    @Test
    public void mdpNotExisting_neg() throws ProgramManagerException {
        try {
            new MdpDir(new File("src/test/testPrograms/test_not_existing"));
            fail(FileNotFoundException.class.getSimpleName() + " expected.");
        } catch (FileNotFoundException e) {
            assertTrue(e.getMessage().contains("Directory not found"));
            assertTrue(e.getMessage().contains("src/test/testPrograms/test_not_existing"));
        }
    }






}