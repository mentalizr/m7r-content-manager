package org.mentalizr.contentManager.fileHierarchy;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.submodule.SubmoduleDirMdp;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SubmoduleDirMdpTest {

    @Test
    public void positive() throws ProgramManagerException {
        SubmoduleDirMdp submoduleDirMdp = new SubmoduleDirMdp(new File("src/test/testPrograms/test1/mdp/m1/sm1"));
        assertEquals("sm1", submoduleDirMdp.getName());
        assertEquals("Submodule1", submoduleDirMdp.getSubmoduleConfFile().getSubmoduleConf().getName());
        assertEquals(2, submoduleDirMdp.getContentFiles().size());
        assertEquals(Arrays.asList("s1", "s2"), submoduleDirMdp.getStepFileNames());
    }

    @Test
    public void nohtmlFilesFound_neg() throws ProgramManagerException {
        try {
            new SubmoduleDirMdp(new File("src/test/testPrograms/test_neg_3/mdp/m1/sm1"));
            fail(FileNotFoundException.class.getSimpleName() + " expected.");
        } catch (FileNotFoundException e) {
            String message = e.getMessage();
            assertTrue(message.contains("No .mdp files found in submodule"));
            assertTrue(message.contains("test_neg_3/mdp/m1/sm1"));
        }
    }

    @Test
    public void submoduleConfMissing_neg() throws ProgramManagerException {
        try {
            new SubmoduleDirMdp(new File("src/test/testPrograms/test_neg_3/mdp/m1/sm2"));
            fail(FileNotFoundException.class.getSimpleName() + " expected.");
        } catch (FileNotFoundException e) {
            String message = e.getMessage();
            assertTrue(message.contains("File not found"));
            assertTrue(message.contains("mdp/m1/sm2/submodule.conf"));
        }
    }

    @Test
    public void submoduleNotExisting_neg() throws ProgramManagerException {
        try {
            new SubmoduleDirMdp(new File("src/test/testPrograms/test_neg_3/mdp/m1/sm_not_existing"));
            fail(FileNotFoundException.class.getSimpleName() + " expected.");
        } catch (FileNotFoundException e) {
            String message = e.getMessage();
            assertTrue(message.contains("Directory not found"));
            assertTrue(message.contains("mdp/m1/sm_not_existing"));
        }
    }

}