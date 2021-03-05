package org.mentalizr.contentManager.fileHierarchy;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SubmoduleDirMdpTest {

    @Test
    public void test() throws ProgramManagerException {

        SubmoduleDirMdp submoduleDirMdp = new SubmoduleDirMdp(new File("src/test/testPrograms/test1/mdp/m1/sm1"));

        assertEquals("sm1", submoduleDirMdp.getDirName());

        assertEquals("Submodule1", submoduleDirMdp.getSubmoduleConfFile().getSubmoduleConf().getName());

        assertEquals(2, submoduleDirMdp.getStepFiles().size());
        assertEquals(Arrays.asList("s1.mdp", "s2.mdp"), submoduleDirMdp.getStepFileNames());

    }

}