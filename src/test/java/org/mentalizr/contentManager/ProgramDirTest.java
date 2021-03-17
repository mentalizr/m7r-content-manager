package org.mentalizr.contentManager;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.contentRoot.MdpDir;
import org.mentalizr.contentManager.fileHierarchy.infopage.MdpInfoDir;
import org.mentalizr.contentManager.fileHierarchy.module.ModuleDirMdp;
import org.mentalizr.contentManager.fileHierarchy.program.ProgramDir;
import org.mentalizr.contentManager.utils.ContentFileUtils;
import org.mentalizr.serviceObjects.frontend.program.Program;
import org.mentalizr.serviceObjects.frontend.program.ProgramSOX;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgramDirTest {

    @Test
    public void getName() throws ProgramManagerException {
        ProgramDir programDir = new ProgramDir(new File("src/test/testPrograms/test1"));
        String name = programDir.getName();
        assertEquals("test1", name);
    }

    @Test
    public void getHtmlFiles() throws ProgramManagerException {
        ProgramDir programDir = new ProgramDir(new File("src/test/testPrograms/test1"));
        List<HtmlFile> htmlFilesFromHtmlDir = programDir.getHtmlDir().getContentFiles();
        List<HtmlFile> htmlFiles = programDir.getHtmlFiles();
        assertEquals(htmlFilesFromHtmlDir, htmlFiles);
    }

    @Test
    public void getMdpFiles() throws ProgramManagerException {
        ProgramDir programDir = new ProgramDir(new File("src/test/testPrograms/test1"));
        List<MdpFile> mdpFilesFromMdpDir = programDir.getMdpDir().getContentFiles();
        List<MdpFile> mdpFiles = programDir.getMdpFiles();
        assertEquals(mdpFilesFromMdpDir, mdpFiles);
    }

    @Test
    public void asProgram() throws ProgramManagerException {

        ProgramDir programDir = new ProgramDir(new File("src/test/testPrograms/test1"));
        Program program = programDir.asProgram();
//        System.out.println(ProgramSOX.toJsonWithFormatting(program));
//        System.out.println(ProgramSOX.toJson(program));

        assertEquals("{\"id\":\"test1\",\"infotexts\":[{\"id\":\"test1__info_info1\",\"name\":\"Info1\"}],\"modules\":[{\"id\":\"test1_m1\",\"name\":\"Module1\",\"submodules\":[{\"id\":\"test1_m1_sm1\",\"name\":\"Submodule1\",\"steps\":[{\"id\":\"test1_m1_sm1_s1\",\"name\":\"Step1\"},{\"id\":\"test1_m1_sm1_s2\",\"name\":\"Step2\"}]},{\"id\":\"test1_m1_sm2\",\"name\":\"Submodule2\",\"steps\":[{\"id\":\"test1_m1_sm2_s1\",\"name\":\"Step1\"}]}]},{\"id\":\"test1_m2\",\"name\":\"Module2\",\"submodules\":[{\"id\":\"test1_m2_sm1\",\"name\":\"Submodule1\",\"steps\":[{\"id\":\"test1_m2_sm1_s1\",\"name\":\"Step1\"}]}]},{\"id\":\"test1_m3\",\"name\":\"Module3\",\"submodules\":[{\"id\":\"test1_m3_sm1\",\"name\":\"Submodule1\",\"steps\":[{\"id\":\"test1_m3_sm1_s1\",\"name\":\"Step1\"}]}]}],\"name\":\"Test1\"}",
                ProgramSOX.toJson(program));

    }

}