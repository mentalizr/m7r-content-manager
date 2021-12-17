package org.mentalizr.contentManager;

import de.arthurpicht.utils.io.tempDir.TempDir;
import de.arthurpicht.utils.io.tempDir.TempDirs;
import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.levels.program.ProgramDir;
import org.mentalizr.contentManager.helper.ProgramStubs;
import org.mentalizr.contentManager.utils.ContentFileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgramsTest {

    @Test
    void plausibility() throws IOException, ContentManagerException {

        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        Path programRootPath = tempDir.asPath();

        ProgramStubs.createTestProgram(programRootPath);

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