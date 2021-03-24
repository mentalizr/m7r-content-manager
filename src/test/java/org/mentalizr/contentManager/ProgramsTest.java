package org.mentalizr.contentManager;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.levels.program.ProgramDir;
import org.mentalizr.contentManager.helper.TestPrograms;
import org.mentalizr.contentManager.testUtils.TempDir;
import org.mentalizr.contentManager.testUtils.TempDirs;
import org.mentalizr.contentManager.utils.ContentFileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgramsTest {

    @Test
    void plausibility() throws IOException, ProgramManagerException {

        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
        Path programRootPath = tempDir.asPath();

        TestPrograms.createTest(programRootPath);

        ProgramDir programDir = new ProgramDir(programRootPath.resolve("test").toFile());

        List<MdpFile> mdpFiles = programDir.getMdpFiles();
        assertEquals(6, mdpFiles.size());
        assertTrue(ContentFileUtils.containsId(mdpFiles, "test_m1_sm1_step01"));
        assertTrue(ContentFileUtils.containsId(mdpFiles, "test_m1_sm1_step02"));
        assertTrue(ContentFileUtils.containsId(mdpFiles, "test_m1_sm2_step01"));
        assertTrue(ContentFileUtils.containsId(mdpFiles, "test_m1_sm2_step02"));
        assertTrue(ContentFileUtils.containsId(mdpFiles, "test_m2_sm1_step01"));
        assertTrue(ContentFileUtils.containsId(mdpFiles, "test__info_info01"));
        assertFalse(programDir.hasHtmlDir());
    }
}