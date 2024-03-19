package org.mentalizr.contentManager.fileHierarchy.levels.submodule;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;
import org.mentalizr.contentManager.programStructure.Step;
import org.mentalizr.contentManager.programStructure.Submodule;
import org.mentalizr.contentManager.utils.ContentFileUtils;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubmoduleDirHtmlTest {

    @Test
    public void getSubmoduleConf() throws ContentManagerException {
        SubmoduleDirHtml submoduleDirMdp = new SubmoduleDirHtml(new File("src/test/testPrograms/test1/html/m1/sm1"));
        assertEquals("Submodule1", submoduleDirMdp.getSubmoduleConfFile().getSubmoduleConf().getName());
    }

    @Test
    public void getName() throws ContentManagerException {
        SubmoduleDirHtml submoduleDirMdp = new SubmoduleDirHtml(new File("src/test/testPrograms/test1/html/m1/sm1"));
        assertEquals("sm1", submoduleDirMdp.getName());
    }

    @Test
    public void getDisplayName() throws ContentManagerException {
        SubmoduleDirHtml submoduleDirMdp = new SubmoduleDirHtml(new File("src/test/testPrograms/test1/html/m1/sm1"));
        assertEquals("Submodule1", submoduleDirMdp.getDisplayName());
    }

    @Test
    public void getId() throws ContentManagerException {
        SubmoduleDirHtml submoduleDirMdp = new SubmoduleDirHtml(new File("src/test/testPrograms/test1/html/m1/sm1"));
        assertEquals("test1_m1_sm1", submoduleDirMdp.getId());
    }

    @Test
    public void getContentFiles() throws ContentManagerException {
        SubmoduleDirHtml submoduleDirMdp = new SubmoduleDirHtml(new File("src/test/testPrograms/test1/html/m1/sm1"));
        List<HtmlFile> htmlFiles = submoduleDirMdp.getContentFiles();
        assertEquals(2, htmlFiles.size());
        assertTrue(ContentFileUtils.containsFileName(htmlFiles, "s1.html"));
        assertTrue(ContentFileUtils.containsFileName(htmlFiles, "s2.html"));
    }

    @Test
    public void asSubmodule() throws ContentManagerException {
        SubmoduleDirHtml submoduleDirMdp = new SubmoduleDirHtml(new File("src/test/testPrograms/test1/html/m1/sm1"));
        Submodule submodule = submoduleDirMdp.asSubmodule();
        assertEquals("test1_m1_sm1", submodule.id());
        assertEquals("Submodule1", submodule.name());
        List<Step> stepList = submodule.steps();
        assertEquals(2, stepList.size());
        Step step = stepList.get(0);
        assertEquals("test1_m1_sm1_s1", step.id());
        assertEquals("Step1", step.name());
        step = stepList.get(1);
        assertEquals("test1_m1_sm1_s2", step.id());
        assertEquals("Step2", step.name());
    }

}