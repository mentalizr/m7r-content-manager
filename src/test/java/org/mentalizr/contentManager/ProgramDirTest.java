package org.mentalizr.contentManager;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.contentRoot.MdpDir;
import org.mentalizr.contentManager.fileHierarchy.infotext.InfotextDirMd;
import org.mentalizr.contentManager.fileHierarchy.module.ModuleDirMdp;
import org.mentalizr.contentManager.fileHierarchy.program.ProgramDir;
import org.mentalizr.contentManager.fileHierarchy.submodule.SubmoduleDirMdp;

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

        List<ModuleDirMdp> moduleDirMdpList = mdpDir.getModuleDirs();
        assertEquals(3, moduleDirMdpList.size());

        assertTrue(mdpDir.hasModuleDir("m1"));
        assertTrue(mdpDir.hasModuleDir("m2"));
        assertTrue(mdpDir.hasModuleDir("m3"));
        assertFalse(mdpDir.hasModuleDir("xyz"));

        assertTrue(mdpDir.hasModuleDir("m1"));
        ModuleDirMdp moduleDirMdp = mdpDir.getModuleDir("m1");
        assertEquals("Module1", moduleDirMdp.getModuleConfFile().getModuleConf().getName());

        List<SubmoduleDirMdp> submoduleDirMdpList = moduleDirMdp.getSubmoduleDirs();
        assertEquals(2, submoduleDirMdpList.size());

        assertTrue(moduleDirMdp.hasSubmoduleDir("sm1"));
        assertTrue(moduleDirMdp.hasSubmoduleDir("sm2"));
        assertFalse(moduleDirMdp.hasSubmoduleDir("xyz"));

        List<String> submoduleIds = moduleDirMdp.getSubmoduleDirNames();
        assertEquals(2, submoduleIds.size());

//        SubmoduleDirMdp submoduleDirMdp = moduleDirMdp.g

        assertTrue(mdpDir.hasModuleDir("m2"));
        moduleDirMdp = mdpDir.getModuleDir("m2");
        assertEquals("Module2", moduleDirMdp.getModuleConfFile().getModuleConf().getName());

        assertTrue(mdpDir.hasModuleDir("m3"));
        moduleDirMdp = mdpDir.getModuleDir("m3");
        assertEquals("Module3", moduleDirMdp.getModuleConfFile().getModuleConf().getName());

//        assertEquals("test1", programDir.getProgramId());
//        assertEquals("Test1", programDir.getDisplayName());
//
//        assertEquals("test1", programDir.getProgram().getId());
//        assertEquals("Test1", programDir.getProgram().getName());

    }

}