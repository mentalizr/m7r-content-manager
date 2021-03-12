package org.mentalizr.contentManager.fileHierarchy.submodule;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.contentFile.HtmlFile;
import org.mentalizr.contentManager.utils.ContentFileUtils;
import org.mentalizr.serviceObjects.frontend.program.Step;
import org.mentalizr.serviceObjects.frontend.program.Submodule;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubmoduleDirHtmlTest {

    @Test
    public void getSubmoduleConf() throws ProgramManagerException {
        SubmoduleDirHtml submoduleDirMdp = new SubmoduleDirHtml(new File("src/test/testPrograms/test1/html/m1/sm1"));
        assertEquals("Submodule1", submoduleDirMdp.getSubmoduleConfFile().getSubmoduleConf().getName());
    }

    @Test
    public void getName() throws ProgramManagerException {
        SubmoduleDirHtml submoduleDirMdp = new SubmoduleDirHtml(new File("src/test/testPrograms/test1/html/m1/sm1"));
        assertEquals("sm1", submoduleDirMdp.getName());
    }

    @Test
    public void getDisplayName() throws ProgramManagerException {
        SubmoduleDirHtml submoduleDirMdp = new SubmoduleDirHtml(new File("src/test/testPrograms/test1/html/m1/sm1"));
        assertEquals("Submodule1", submoduleDirMdp.getDisplayName());
    }

    @Test
    public void getId() throws ProgramManagerException {
        SubmoduleDirHtml submoduleDirMdp = new SubmoduleDirHtml(new File("src/test/testPrograms/test1/html/m1/sm1"));
        assertEquals("test1_m1_sm1", submoduleDirMdp.getId());
    }

    @Test
    public void getContentFiles() throws ProgramManagerException {
        SubmoduleDirHtml submoduleDirMdp = new SubmoduleDirHtml(new File("src/test/testPrograms/test1/html/m1/sm1"));
        List<HtmlFile> htmlFiles = submoduleDirMdp.getContentFiles();
        assertEquals(2, htmlFiles.size());
        assertTrue(ContentFileUtils.containsFileName(htmlFiles, "s1.html"));
        assertTrue(ContentFileUtils.containsFileName(htmlFiles, "s2.html"));
    }

    @Test
    public void asSubmodule() throws ProgramManagerException {
        SubmoduleDirHtml submoduleDirMdp = new SubmoduleDirHtml(new File("src/test/testPrograms/test1/html/m1/sm1"));
        Submodule submodule = submoduleDirMdp.asSubmodule();
        assertEquals("test1_m1_sm1", submodule.getId());
        assertEquals("Submodule1", submodule.getName());
        List<Step> stepList = submodule.getSteps();
        assertEquals(2, stepList.size());
        Step step = stepList.get(0);
        assertEquals("test1_m1_sm1_s1", step.getId());
        assertEquals("Step1", step.getName());
        step = stepList.get(1);
        assertEquals("test1_m1_sm1_s2", step.getId());
        assertEquals("Step2", step.getName());
    }

}