package org.mentalizr.contentManager.build;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BuildProcessorTest {

//    @Test
//    void createHtmlDirSkeleton() throws ContentManagerException, IOException {
//
//        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
//        Path programRootPath = tempDir.asPath();
//        TestPrograms.createTest(programRootPath);
//        ProgramDir programDir = new ProgramDir(programRootPath.resolve("test").toFile());
//        Programs.createHtmlDirSkeleton(programDir);
//
//        Path htmlPath = programDir.asPath().resolve(HtmlDir.DIR_NAME);
//
//        Path programConf = htmlPath.resolve(ProgramConfFile.FILE_NAME);
//        assertTrue(Files.exists(programConf));
//
//        Path infoDirPath = htmlPath.resolve(InfoDir.DIR_NAME);
//        assertTrue(Files.exists(infoDirPath));
//
//        Path m1Path = htmlPath.resolve("m1").resolve(ModuleConfFile.FILE_NAME);
//        assertTrue(Files.exists(m1Path));
//
//        Path m1sm1Path = htmlPath.resolve("m1").resolve("sm1").resolve(SubmoduleConfFile.FILE_NAME);
//        assertTrue(Files.exists(m1sm1Path));
//
//        Path m1sm2Path = htmlPath.resolve("m1").resolve("sm2").resolve(SubmoduleConfFile.FILE_NAME);
//        assertTrue(Files.exists(m1sm2Path));
//
//        Path m2Path = htmlPath.resolve("m2").resolve(ModuleConfFile.FILE_NAME);
//        assertTrue(Files.exists(m2Path));
//
//        Path m2sm1Path = htmlPath.resolve("m2").resolve("sm1").resolve(SubmoduleConfFile.FILE_NAME);
//        assertTrue(Files.exists(m2sm1Path));
//    }
//
//    @Test
//    void build() throws IOException, ContentManagerException {
//
//        TempDir tempDir = TempDirs.createUniqueTempDirAutoClean();
//        Path programRootPath = tempDir.asPath();
//
//        TestPrograms.createTest(programRootPath);
//
//        ProgramDir programDir = new ProgramDir(programRootPath.resolve("test").toFile());
//        Programs.createHtmlDirSkeleton(programDir);
//
//        programDir = new ProgramDir(programRootPath.resolve("test").toFile());
//        assertTrue(programDir.hasHtmlDir());
//
//        Program program = new Program(programDir.asPath());
//
//        BuildSummary buildSummary = BuildProcessor.compile(program, new TestBuildHandlerFactory());
//
//        Path htmlPath = programDir.getHtmlDir().asPath();
//
//        assertTrue(Files.exists(htmlPath.resolve(InfoDir.DIR_NAME).resolve("info01.html")));
//        assertTrue(Files.exists(htmlPath.resolve("m1").resolve("sm1").resolve("step01.html")));
//        assertTrue(Files.exists(htmlPath.resolve("m1").resolve("sm1").resolve("step02.html")));
//        assertTrue(Files.exists(htmlPath.resolve("m1").resolve("sm2").resolve("step01.html")));
//        assertTrue(Files.exists(htmlPath.resolve("m1").resolve("sm2").resolve("step02.html")));
//        assertTrue(Files.exists(htmlPath.resolve("m2").resolve("sm1").resolve("step01.html")));
//
//        assertEquals(0, buildSummary.getNrOfFailedMdpFiles());
//        assertEquals(6, buildSummary.getNrOfSuccessfulMdpFiles());
//
//    }

}