package org.mentalizr.contentManager.testUtils;

import de.arthurpicht.utils.io.nio2.FileUtils;

import java.io.File;
import java.nio.file.Path;

public class TempDir {

    private final Path tempDir;
    private final boolean autoClean;

    public TempDir(Path tempPath) {
        this.tempDir = tempPath;
        this.autoClean = false;
    }

    public TempDir(Path tempPath, boolean autoclean) {
        this.tempDir = tempPath;
        this.autoClean = autoclean;
    }

    public Path asPath() {
        return this.tempDir;
    }

    public File asFile() {
        return this.tempDir.toFile();
    }

    public boolean isAutoClean() {
        return this.autoClean;
    }

    public void clean() {
        FileUtils.forceDeleteSilently(this.tempDir);
    }

}
