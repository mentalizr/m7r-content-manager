package org.mentalizr.contentManager.fileHierarchy;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.fileHierarchy.levels.module.ModuleDirMdp;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ModuleDirMdpTest {

    @Test
    public void positive() throws ContentManagerException {
        ModuleDirMdp moduleDirMdp = new ModuleDirMdp(new File("src/test/testPrograms/test1/mdp/m1"));
        assertEquals("m1", moduleDirMdp.getName());
        assertEquals("Module1", moduleDirMdp.getModuleConfFile().getModuleConf().getName());
        assertEquals(2, moduleDirMdp.getSubmoduleDirs().size());
        assertEquals(Arrays.asList("sm1", "sm2"), moduleDirMdp.getSubmoduleDirNames());
    }

    @Test
    public void noSubmodulesFound_neg() throws ContentManagerException {
        try {
            new ModuleDirMdp(new File("src/test/testPrograms/test_neg_3/mdp/m3"));
            fail(FileNotFoundException.class.getSimpleName() + " expected.");
        } catch (FileNotFoundException e) {
            String message = e.getMessage();
            assertTrue(message.contains("No submodules found"));
            assertTrue(message.contains("test_neg_3/mdp/m3"));
        }
    }

    @Test
    public void moduleConfMissing_neg() throws ContentManagerException {
        try {
            new ModuleDirMdp(new File("src/test/testPrograms/test_neg_3/mdp/m2"));
            fail(FileNotFoundException.class.getSimpleName() + " expected.");
        } catch (FileNotFoundException e) {
            String message = e.getMessage();
            assertTrue(message.contains("File not found"));
            assertTrue(message.contains("mdp/m2/module.conf"));
        }
    }

    @Test
    public void moduleNotExisting_neg() throws ContentManagerException {
        try {
            new ModuleDirMdp(new File("src/test/testPrograms/test_neg_3/mdp/m_not_existing"));
            fail(FileNotFoundException.class.getSimpleName() + " expected.");
        } catch (FileNotFoundException e) {
            String message = e.getMessage();
            assertTrue(message.contains("Directory not found"));
            assertTrue(message.contains("/m_not_existing"));
        }
    }

}