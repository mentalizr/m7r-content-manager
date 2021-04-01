package org.mentalizr.contentManager.build;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.HtmlDir;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.ProgramConfFile;
import org.mentalizr.contentManager.fileHierarchy.levels.info.InfoDir;
import org.mentalizr.contentManager.fileHierarchy.levels.module.ModuleConfFile;
import org.mentalizr.contentManager.fileHierarchy.levels.program.ProgramDir;
import org.mentalizr.contentManager.fileHierarchy.levels.submodule.SubmoduleConfFile;
import org.mentalizr.contentManager.helper.TestPrograms;
import org.mentalizr.contentManager.testUtils.TempDir;
import org.mentalizr.contentManager.testUtils.TempDirs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BuildProcessorTest {

    @Test
    void createHtmlDirSkeleton() throws ProgramManagerException, IOException {

        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
        Path programRootPath = tempDir.asPath();
        TestPrograms.createTest(programRootPath);
        ProgramDir programDir = new ProgramDir(programRootPath.resolve("test").toFile());
        BuildProcessor.createHtmlDirSkeleton(programDir);

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

    @Test
    void build() throws IOException, ProgramManagerException {

        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
        Path programRootPath = tempDir.asPath();

        TestPrograms.createTest(programRootPath);

        ProgramDir programDir = new ProgramDir(programRootPath.resolve("test").toFile());
        BuildProcessor.createHtmlDirSkeleton(programDir);

        programDir = new ProgramDir(programRootPath.resolve("test").toFile());
        assertTrue(programDir.hasHtmlDir());

        BuildSummary buildSummary = BuildProcessor.compile(programDir, new TestBuildHandler());

        Path htmlPath = programDir.getHtmlDir().asPath();

        assertTrue(Files.exists(htmlPath.resolve(InfoDir.DIR_NAME).resolve("info01.html")));
        assertTrue(Files.exists(htmlPath.resolve("m1").resolve("sm1").resolve("step01.html")));
        assertTrue(Files.exists(htmlPath.resolve("m1").resolve("sm1").resolve("step02.html")));
        assertTrue(Files.exists(htmlPath.resolve("m1").resolve("sm2").resolve("step01.html")));
        assertTrue(Files.exists(htmlPath.resolve("m1").resolve("sm2").resolve("step02.html")));
        assertTrue(Files.exists(htmlPath.resolve("m2").resolve("sm1").resolve("step01.html")));

        assertEquals(0, buildSummary.getNrOfFailedMdpFiles());
        assertEquals(6, buildSummary.getNrOfSuccessfulMdpFiles());

    }

}