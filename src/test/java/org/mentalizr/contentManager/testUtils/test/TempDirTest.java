package org.mentalizr.contentManager.testUtils.test;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.testUtils.TempDir;
import org.mentalizr.contentManager.testUtils.TempDirs;

import java.io.IOException;

public class TempDirTest {

    @Test
    void getTempDir() throws IOException {
        TempDir myTempDir = TempDirs.createUniqueTempDir();
        // System.out.println("tempDir is " + myTempDir.getPath().toString());
    }

    @Test
    void getTempDirAndClean() throws IOException {
        TempDir myTempDir = TempDirs.createUniqueTempDir();
//        System.out.println("tempDir is " + myTempDir.getPath().toString());
        myTempDir.clean();
    }

    @Test
    void getTempDirAutoClean() throws IOException {
        TempDir myTempDir = TempDirs.createUniqueTempDirAutoClean();
//        System.out.println("tempDir is " + myTempDir.getPath().toString());
    }

}
