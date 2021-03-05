package org.mentalizr.contentManager;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.*;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgramDirTest {

    @Test
    public void test() throws ProgramManagerException {

        ProgramDir programDir = new ProgramDir(new File("src/test/testPrograms/test1"));

        MdpDir mdpDir = programDir.getMdpDir();
        assertEquals("Test1", mdpDir.getProgramConfFile().getProgramConf().getName());

        InfotextDirMd infotextDirMd = mdpDir.getInfotextDir();
        List<MdpFile> infotextFiles = infotextDirMd.getInfotextFiles();
        assertEquals(1, infotextFiles.size());

        List<ModuleDirMdp> moduleDirMdpList = mdpDir.getModuleDirList();
        assertEquals(3, moduleDirMdpList.size());

        assertTrue(mdpDir.hasModuleDirMdp("m1"));
        ModuleDirMdp moduleDirMdp = mdpDir.getModuleDirMdp("m1");
        assertEquals("Module1", moduleDirMdp.getModuleConfFile().getModuleConf().getName());

        assertTrue(mdpDir.hasModuleDirMdp("m2"));
        moduleDirMdp = mdpDir.getModuleDirMdp("m2");
        assertEquals("Module2", moduleDirMdp.getModuleConfFile().getModuleConf().getName());

        assertTrue(mdpDir.hasModuleDirMdp("m3"));
        moduleDirMdp = mdpDir.getModuleDirMdp("m3");
        assertEquals("Module3", moduleDirMdp.getModuleConfFile().getModuleConf().getName());

//        assertEquals("test1", programDir.getProgramId());
//        assertEquals("Test1", programDir.getDisplayName());
//
//        assertEquals("test1", programDir.getProgram().getId());
//        assertEquals("Test1", programDir.getProgram().getName());

    }

}