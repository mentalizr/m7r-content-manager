package org.mentalizr.contentManager;

import de.arthurpicht.utils.core.strings.Strings;
import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.contentRoot.MdpDir;
import org.mentalizr.contentManager.fileHierarchy.info.InfoDirMdp;
import org.mentalizr.contentManager.fileHierarchy.module.ModuleDirMdp;
import org.mentalizr.contentManager.fileHierarchy.program.ProgramDir;
import org.mentalizr.serviceObjects.frontend.program.Program;
import org.mentalizr.serviceObjects.frontend.program.ProgramSOX;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ProgramDirTest {

    @Test
    public void test() throws ProgramManagerException {

        ProgramDir programDir = new ProgramDir(new File("src/test/testPrograms/test1"));

        MdpDir mdpDir = programDir.getMdpDir();
        assertEquals("Test1", mdpDir.getProgramConfFile().getProgramConf().getName());

        InfoDirMdp infoDirMdp = mdpDir.getInfotextDir();
        List<MdpFile> infotextFiles = infoDirMdp.getInfoFiles();
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

        assertTrue(mdpDir.hasModuleDir("m2"));
        moduleDirMdp = mdpDir.getModuleDir("m2");
        assertEquals("Module2", moduleDirMdp.getModuleConfFile().getModuleConf().getName());

        assertTrue(mdpDir.hasModuleDir("m3"));
        moduleDirMdp = mdpDir.getModuleDir("m3");
        assertEquals("Module3", moduleDirMdp.getModuleConfFile().getModuleConf().getName());

        List<MdpFile> mdpFiles = programDir.getMdpFiles();
        List<String> idList = mdpFiles.stream().map(MdpFile::getId).collect(Collectors.toList());
        String idString = Strings.listing(idList, " ");

        assertEquals("test1_m1_sm1_s1 " +
                "test1_m1_sm1_s2 " +
                "test1_m1_sm2_s1 " +
                "test1_m2_sm1_s1 " +
                "test1_m3_sm1_s1", idString);

        List<HtmlFile> htmlFiles = programDir.getHtmlFiles();
        idList = htmlFiles.stream().map(HtmlFile::getId).collect(Collectors.toList());
        idString = Strings.listing(idList, " ");

        assertEquals("test1_m1_sm1_s1 " +
                "test1_m1_sm1_s2 " +
                "test1_m1_sm2_s1 " +
                "test1_m2_sm1_s1 " +
                "test1_m3_sm1_s1", idString);

        Program program = programDir.asProgram();
        System.out.println(ProgramSOX.toJsonWithFormatting(program));



//        assertEquals("test1", programDir.getProgramId());
//        assertEquals("Test1", programDir.getDisplayName());
//
//        assertEquals("test1", programDir.getProgram().getId());
//        assertEquals("Test1", programDir.getProgram().getName());

    }

}