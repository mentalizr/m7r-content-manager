package org.mentalizr.contentManager;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.levels.program.ProgramDir;
import org.mentalizr.contentManager.programStructure.ProgramStructure;

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
    public void getHtmlInfoTextFile() throws ContentManagerException {
        ProgramDir programDir = new ProgramDir(new File("src/test/testPrograms/test1"));
        List<HtmlFile> htmlInfoTextFilesFromHtmlDir = programDir.getHtmlDir().getInfoDir().getContentFiles();
        List<HtmlFile> htmlInfoTextFiles = programDir.getHtmlInfoTextFiles();
        assertEquals(htmlInfoTextFilesFromHtmlDir, htmlInfoTextFiles);
        assertEquals(1, htmlInfoTextFiles.size());
        System.out.println(htmlInfoTextFiles.get(0).getId());
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
        ProgramStructure programStructure = programDir.asProgramStructure();

        assertEquals("test1", programStructure.id());
        assertEquals(1, programStructure.infotexts().size());
        assertEquals("test1__info_info1", programStructure.infotexts().get(0).id());
        assertEquals("Info1", programStructure.infotexts().get(0).name());
        assertEquals(3, programStructure.modules().size());
        assertEquals("test1_m1", programStructure.modules().get(0).id());

        assertEquals("Module1", programStructure.modules().get(0).name());
        assertEquals(2, programStructure.modules().get(0).submodules().size());
        assertEquals("test1_m1_sm1", programStructure.modules().get(0).submodules().get(0).id());
        assertEquals("Submodule1", programStructure.modules().get(0).submodules().get(0).name());
        assertEquals(2, programStructure.modules().get(0).submodules().get(0).steps().size());
        assertEquals("test1_m1_sm1_s1", programStructure.modules().get(0).submodules().get(0).steps().get(0).id());
        assertEquals("Step1", programStructure.modules().get(0).submodules().get(0).steps().get(0).name());
        assertEquals("test1_m1_sm1_s2", programStructure.modules().get(0).submodules().get(0).steps().get(1).id());
        assertEquals("Step2", programStructure.modules().get(0).submodules().get(0).steps().get(1).name());
        assertEquals("test1_m2", programStructure.modules().get(1).id());

        assertEquals("Module2", programStructure.modules().get(1).name());
        assertEquals(1, programStructure.modules().get(1).submodules().size());
        assertEquals("test1_m2_sm1", programStructure.modules().get(1).submodules().get(0).id());
        assertEquals("Submodule1", programStructure.modules().get(1).submodules().get(0).name());
        assertEquals(1, programStructure.modules().get(1).submodules().get(0).steps().size());
        assertEquals("test1_m2_sm1_s1", programStructure.modules().get(1).submodules().get(0).steps().get(0).id());
        assertEquals("Step1", programStructure.modules().get(1).submodules().get(0).steps().get(0).name());

        assertEquals("Module3", programStructure.modules().get(2).name());
        assertEquals(1, programStructure.modules().get(2).submodules().size());
        assertEquals("test1_m3_sm1", programStructure.modules().get(2).submodules().get(0).id());
        assertEquals("Submodule1", programStructure.modules().get(2).submodules().get(0).name());
        assertEquals(1, programStructure.modules().get(2).submodules().get(0).steps().size());
        assertEquals("test1_m3_sm1_s1", programStructure.modules().get(2).submodules().get(0).steps().get(0).id());
        assertEquals("Step1", programStructure.modules().get(2).submodules().get(0).steps().get(0).name());

//        assertEquals("{\"id\":\"test1\",\"infotexts\":[{\"id\":\"test1__info_info1\",\"name\":\"Info1\"}],\"" +
//                        "modules\":[{\"id\":\"test1_m1\",\"name\":\"Module1\"," +
//                        "\"submodules\":[{\"id\":\"test1_m1_sm1\",\"name\":\"Submodule1\"," +
//                        "\"steps\":[{\"id\":\"test1_m1_sm1_s1\",\"name\":\"Step1\"},{\"id\":\"test1_m1_sm1_s2\",\"name\":\"Step2\"}]}" +
//                        ",{\"id\":\"test1_m1_sm2\",\"name\":\"Submodule2\",\"steps\":[{\"id\":\"test1_m1_sm2_s1\",\"name\":\"Step1\"}]}]}," +
//
//                        "{\"id\":\"test1_m2\",\"name\":\"Module2\"," +
//                        "\"submodules\":[{\"id\":\"test1_m2_sm1\",\"name\":\"Submodule1\"," +
//                        "\"steps\":[{\"id\":\"test1_m2_sm1_s1\",\"name\":\"Step1\"}]}]}," +
//                        "" +
//                        "{\"id\":\"test1_m3\",\"name\":\"Module3\"," +
//                        "\"submodules\":[{\"id\":\"test1_m3_sm1\",\"name\":" +
//                        "\"Submodule1\",\"steps\":[{\"id\":\"test1_m3_sm1_s1\",\"name\":\"Step1\"}]}]}],\"name\":\"Test1\"}",
//                ProgramSOX.toJson(programStructure));

    }

}