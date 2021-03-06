package org.mentalizr.contentManager.fileHierarchy;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MdpDirTest {

    @Test
    public void positive() throws ProgramManagerException {

        MdpDir mdpDir = new MdpDir(new File("src/test/testPrograms/test1/mdp"));

        assertEquals("mdp", mdpDir.getName());

        assertEquals("Test1", mdpDir.getProgramConfFile().getProgramConf().getName());

        assertEquals(3, mdpDir.getModuleDirs().size());
        assertEquals(Arrays.asList("m1", "m2", "m3"), mdpDir.getModuleDirNames());

        assertEquals("_info", mdpDir.getInfotextDir().getName());
    }

    @Test
    public void noModulesFound_neg() throws ProgramManagerException {
        try {
            new MdpDir(new File("src/test/fractions/mdp"));
            fail(FileNotFoundException.class.getSimpleName() + " expected.");
        } catch (FileNotFoundException e) {
            // din
//            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
    }

    @Test
    public void programConfMissing_neg() throws ProgramManagerException {
        try {
            new MdpDir(new File("src/test/fractions/mdp2"));
            fail(FileNotFoundException.class.getSimpleName() + " expected.");
        } catch (FileNotFoundException e) {
            // din
//            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
    }

    @Test
    public void mdpNotExisting_neg() throws ProgramManagerException {
        try {
            new MdpDir(new File("src/test/fractions/not_existing"));
            fail(FileNotFoundException.class.getSimpleName() + " expected.");
        } catch (FileNotFoundException e) {
            // din
//            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
    }




}