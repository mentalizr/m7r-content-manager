package org.mentalizr.contentManager.helper;

import de.arthurpicht.utils.io.tempDir.TempDir;
import de.arthurpicht.utils.io.tempDir.TempDirs;
import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.Programs;
import org.mentalizr.contentManager.TestConfig;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.HtmlDir;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.ProgramConfFile;
import org.mentalizr.contentManager.fileHierarchy.levels.info.InfoDir;
import org.mentalizr.contentManager.fileHierarchy.levels.module.ModuleConfFile;
import org.mentalizr.contentManager.fileHierarchy.levels.program.ProgramDir;
import org.mentalizr.contentManager.fileHierarchy.levels.submodule.SubmoduleConfFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ProgramStubsTest {

    @Test
    void createHtmlDirSkeleton() throws ContentManagerException, IOException {

        // prepare
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        Path programRootPath = tempDir.asPath();
        ProgramStubs.createTestProgram(programRootPath);
        ProgramDir programDir = new ProgramDir(programRootPath.resolve("test").toFile());

        // execute
        Programs.createHtmlDirSkeleton(programDir);

        // assert
        Path htmlPath = programDir.asPath().resolve(HtmlDir.DIR_NAME);

        Path programConf = htmlPath.resolve(ProgramConfFile.FILE_NAME);
        assertTrue(Files.exists(programConf));

        Path infoDirPath = htmlPath.resolve(InfoDir.DIR_NAME);
        assertTrue(Files.exists(infoDirPath));

        Path m1Path = htmlPath.resolve("m1").resolve(ModuleConfFile.FILE_NAME);
        assertTrue(Files.exists(m1Path));

        Path m1sm1Path = htmlPath.resolve("m1").resolve("sm1").resolve(SubmoduleConfFile.FILE_NAME);
        assertTrue(Files.exists(m1sm1Path));

        Path m1sm2Path = htmlPath.resolve("m1").resolve("sm2").resolve(SubmoduleConfFile.FILE_NAME);
        assertTrue(Files.exists(m1sm2Path));

        Path m2Path = htmlPath.resolve("m2").resolve(ModuleConfFile.FILE_NAME);
        assertTrue(Files.exists(m2Path));

        Path m2sm1Path = htmlPath.resolve("m2").resolve("sm1").resolve(SubmoduleConfFile.FILE_NAME);
        assertTrue(Files.exists(m2sm1Path));
    }


}