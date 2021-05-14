package org.mentalizr.contentManager;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.levels.program.ProgramDir;
import org.mentalizr.serviceObjects.frontend.program.ProgramSO;
import org.mentalizr.serviceObjects.frontend.program.ProgramSOX;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgramDirTest {

    @Test
    public void getName() throws ContentManagerException {
        ProgramDir programDir = new ProgramDir(new File("src/test/testPrograms/test1"));
        String name = programDir.getName();
        assertEquals("test1", name);
    }

    @Test
    public void getHtmlFiles() throws ContentManagerException {
        ProgramDir programDir = new ProgramDir(new File("src/test/testPrograms/test1"));
        List<HtmlFile> htmlFilesFromHtmlDir = programDir.getHtmlDir().getContentFiles();
        List<HtmlFile> htmlFiles = programDir.getHtmlFiles();
        assertEquals(htmlFilesFromHtmlDir, htmlFiles);
    }

    @Test
    public void getMdpFiles() throws ContentManagerException {
        ProgramDir programDir = new ProgramDir(new File("src/test/testPrograms/test1"));
        List<MdpFile> mdpFilesFromMdpDir = programDir.getMdpDir().getContentFiles();
        List<MdpFile> mdpFiles = programDir.getMdpFiles();
        assertEquals(mdpFilesFromMdpDir, mdpFiles);
    }

    @Test
    public void asProgram() throws ContentManagerException {

        ProgramDir programDir = new ProgramDir(new File("src/test/testPrograms/test1"));
        ProgramSO programSO = programDir.asProgram();
//        System.out.println(ProgramSOX.toJsonWithFormatting(program));
//        System.out.println(ProgramSOX.toJson(program));

        assertEquals("{\"id\":\"test1\",\"infotexts\":[{\"id\":\"test1__info_info1\",\"name\":\"Info1\"}],\"modules\":[{\"id\":\"test1_m1\",\"name\":\"Module1\",\"submodules\":[{\"id\":\"test1_m1_sm1\",\"name\":\"Submodule1\",\"steps\":[{\"id\":\"test1_m1_sm1_s1\",\"name\":\"Step1\"},{\"id\":\"test1_m1_sm1_s2\",\"name\":\"Step2\"}]},{\"id\":\"test1_m1_sm2\",\"name\":\"Submodule2\",\"steps\":[{\"id\":\"test1_m1_sm2_s1\",\"name\":\"Step1\"}]}]},{\"id\":\"test1_m2\",\"name\":\"Module2\",\"submodules\":[{\"id\":\"test1_m2_sm1\",\"name\":\"Submodule1\",\"steps\":[{\"id\":\"test1_m2_sm1_s1\",\"name\":\"Step1\"}]}]},{\"id\":\"test1_m3\",\"name\":\"Module3\",\"submodules\":[{\"id\":\"test1_m3_sm1\",\"name\":\"Submodule1\",\"steps\":[{\"id\":\"test1_m3_sm1_s1\",\"name\":\"Step1\"}]}]}],\"name\":\"Test1\"}",
                ProgramSOX.toJson(programSO));

    }

}