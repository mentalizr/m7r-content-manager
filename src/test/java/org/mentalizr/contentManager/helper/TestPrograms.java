package org.mentalizr.contentManager.helper;

import org.mentalizr.contentManager.Programs;
import org.mentalizr.contentManager.exceptions.ContentManagerException;

import java.nio.file.Path;

public class TestPrograms {

    public static void createTest(Path programRootPath) throws ContentManagerException {

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
        Programs.createMdpInfoStub(programRootPath, "test", "info01", "Info01");
    }

}
