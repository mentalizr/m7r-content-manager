package org.mentalizr.contentManager;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.contentFile.MdpFile;
import org.mentalizr.contentManager.build.HtmlDirSkeleton;
import org.mentalizr.contentManager.fileHierarchy.program.ProgramDir;
import org.mentalizr.contentManager.testUtils.TempDir;
import org.mentalizr.contentManager.testUtils.TempDirs;
import org.mentalizr.contentManager.utils.ContentFileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProgramsTest {

    @Test
    void createProgram() throws IOException, ProgramManagerException {

        TempDir tempDir = TempDirs.createUniqueTempDir();
        Path programRootPath = tempDir.getPath();

        Programs.createProgram(programRootPath, "test", "Test");
        Programs.createModule(programRootPath, "test", "m1", "Module1");
        Programs.createSubmodule(programRootPath, "test", "m1", "sm1", "Submodule1");
        Programs.createMdpStub(programRootPath, "test", "m1", "sm1", "step01", "Step01");
        Programs.createMdpStub(programRootPath, "test", "m1", "sm1", "step02", "Step02");
        Programs.createSubmodule(programRootPath, "test", "m1", "sm2", "Submodule2");
        Programs.createMdpStub(programRootPath, "test", "m1", "sm2", "step01", "Step01");
        Programs.createMdpStub(programRootPath, "test", "m1", "sm2", "step02", "Step02");

        Programs.createModule(programRootPath, "test", "m2", "Module2");
        Programs.createSubmodule(programRootPath, "test", "m2", "sm1", "Submodule1");
        Programs.createMdpStub(programRootPath, "test", "m2", "sm1", "step01", "Step01");

        ProgramDir programDir = new ProgramDir(programRootPath.resolve("test").toFile());

        List<MdpFile> mdpFiles = programDir.getMdpFiles();
        assertEquals(5, mdpFiles.size());
        assertTrue(ContentFileUtils.containsId(mdpFiles, "test_m1_sm1_step01"));
        assertTrue(ContentFileUtils.containsId(mdpFiles, "test_m1_sm1_step02"));
        assertTrue(ContentFileUtils.containsId(mdpFiles, "test_m1_sm2_step01"));
        assertTrue(ContentFileUtils.containsId(mdpFiles, "test_m1_sm2_step02"));
        assertTrue(ContentFileUtils.containsId(mdpFiles, "test_m2_sm1_step01"));

        HtmlDirSkeleton.createCleanHtmlDirSkeleton(programDir);

    }
}