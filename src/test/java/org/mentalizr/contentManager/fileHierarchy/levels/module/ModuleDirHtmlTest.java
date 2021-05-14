package org.mentalizr.contentManager.fileHierarchy.levels.module;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;
import org.mentalizr.contentManager.utils.ContentFileUtils;
import org.mentalizr.serviceObjects.frontend.program.ModuleSO;
import org.mentalizr.serviceObjects.frontend.program.Submodule;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModuleDirHtmlTest {

    @Test
    public void getModuleConf() throws ContentManagerException {
        ModuleDirHtml moduleDirHtml = new ModuleDirHtml(new File("src/test/testPrograms/test1/html/m1"));
        assertEquals("Module1", moduleDirHtml.getModuleConfFile().getModuleConf().getName());
    }

    @Test
    public void getName() throws ContentManagerException {
        ModuleDirHtml moduleDirHtml = new ModuleDirHtml(new File("src/test/testPrograms/test1/html/m1"));
        assertEquals("m1", moduleDirHtml.getName());
    }

    @Test
    public void getDisplayName() throws ContentManagerException {
        ModuleDirHtml moduleDirHtml = new ModuleDirHtml(new File("src/test/testPrograms/test1/html/m1"));
        assertEquals("Module1", moduleDirHtml.getDisplayName());
    }

    @Test
    public void getId() throws ContentManagerException {
        ModuleDirHtml moduleDirHtml = new ModuleDirHtml(new File("src/test/testPrograms/test1/html/m1"));
        assertEquals("test1_m1", moduleDirHtml.getId());
    }

    @Test
    public void getContentFiles() throws ContentManagerException {
        ModuleDirHtml moduleDirHtml = new ModuleDirHtml(new File("src/test/testPrograms/test1/html/m1"));
        List<HtmlFile> htmlFiles = moduleDirHtml.getContentFiles();
        assertEquals(3, htmlFiles.size());
        assertTrue(ContentFileUtils.containsId(htmlFiles, "test1_m1_sm1_s1"));
        assertTrue(ContentFileUtils.containsId(htmlFiles, "test1_m1_sm1_s2"));
        assertTrue(ContentFileUtils.containsId(htmlFiles, "test1_m1_sm2_s1"));
    }

    @Test
    public void asModule() throws ContentManagerException {
        ModuleDirHtml moduleDirHtml = new ModuleDirHtml(new File("src/test/testPrograms/test1/html/m1"));
        ModuleSO module = moduleDirHtml.asModule();
        assertEquals("test1_m1", module.getId());
        assertEquals("Module1", module.getName());
        List<Submodule> submodules = module.getSubmodules();
        assertEquals(2, submodules.size());
    }

}