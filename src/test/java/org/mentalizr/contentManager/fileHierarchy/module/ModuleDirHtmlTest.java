package org.mentalizr.contentManager.fileHierarchy.module;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.submodule.SubmoduleDirHtml;
import org.mentalizr.contentManager.utils.ContentFileUtils;
import org.mentalizr.serviceObjects.frontend.program.Module;
import org.mentalizr.serviceObjects.frontend.program.Submodule;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModuleDirHtmlTest {

    @Test
    public void getModuleConf() throws ProgramManagerException {
        ModuleDirHtml moduleDirHtml = new ModuleDirHtml(new File("src/test/testPrograms/test1/html/m1"));
        assertEquals("Module1", moduleDirHtml.getModuleConfFile().getModuleConf().getName());
    }

    @Test
    public void getName() throws ProgramManagerException {
        ModuleDirHtml moduleDirHtml = new ModuleDirHtml(new File("src/test/testPrograms/test1/html/m1"));
        assertEquals("m1", moduleDirHtml.getName());
    }

    @Test
    public void getDisplayName() throws ProgramManagerException {
        ModuleDirHtml moduleDirHtml = new ModuleDirHtml(new File("src/test/testPrograms/test1/html/m1"));
        assertEquals("Module1", moduleDirHtml.getDisplayName());
    }

    @Test
    public void getId() throws ProgramManagerException {
        ModuleDirHtml moduleDirHtml = new ModuleDirHtml(new File("src/test/testPrograms/test1/html/m1"));
        assertEquals("test1_m1", moduleDirHtml.getId());
    }

    @Test
    public void getContentFiles() throws ProgramManagerException {
        ModuleDirHtml moduleDirHtml = new ModuleDirHtml(new File("src/test/testPrograms/test1/html/m1"));
        List<HtmlFile> htmlFiles = moduleDirHtml.getContentFiles();
        assertEquals(3, htmlFiles.size());
        assertTrue(ContentFileUtils.containsId(htmlFiles, "test1_m1_sm1_s1"));
        assertTrue(ContentFileUtils.containsId(htmlFiles, "test1_m1_sm1_s2"));
        assertTrue(ContentFileUtils.containsId(htmlFiles, "test1_m1_sm2_s1"));
    }

    @Test
    public void asModule() throws ProgramManagerException {
        ModuleDirHtml moduleDirHtml = new ModuleDirHtml(new File("src/test/testPrograms/test1/html/m1"));
        Module module = moduleDirHtml.asModule();
        assertEquals("test1_m1", module.getId());
        assertEquals("Module1", module.getName());
        List<Submodule> submodules = module.getSubmodules();
        assertEquals(2, submodules.size());
    }

}