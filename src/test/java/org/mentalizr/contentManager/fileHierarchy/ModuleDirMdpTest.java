package org.mentalizr.contentManager.fileHierarchy;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ModuleDirMdpTest {

    @Test
    public void positive() throws ProgramManagerException {

        ModuleDirMdp moduleDirMdp = new ModuleDirMdp(new File("src/test/testPrograms/test1/mdp/m1"));

        assertEquals("m1", moduleDirMdp.getName());

        assertEquals("Module1", moduleDirMdp.getModuleConfFile().getModuleConf().getName());

        assertEquals(2, moduleDirMdp.getSubmoduleDirs().size());
        assertEquals(Arrays.asList("sm1", "sm2"), moduleDirMdp.getSubmoduleDirNames());
    }

    @Test
    public void noSubmodulesFound_neg() throws ProgramManagerException {
        try {
            new ModuleDirMdp(new File("src/test/fractions/m1"));
            fail(FileNotFoundException.class.getSimpleName() + " expected.");
        } catch (FileNotFoundException e) {
            // din
//            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
    }

    @Test
    public void moduleConfMissing_neg() throws ProgramManagerException {
        try {
            new ModuleDirMdp(new File("src/test/fractions/m2"));
            fail(FileNotFoundException.class.getSimpleName() + " expected.");
        } catch (FileNotFoundException e) {
            // din
//            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
    }

    @Test
    public void moduleNotExisting_neg() throws ProgramManagerException {
        try {
            new ModuleDirMdp(new File("src/test/fractions/not_existing"));
            fail(FileNotFoundException.class.getSimpleName() + " expected.");
        } catch (FileNotFoundException e) {
            // din
//            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
    }





}