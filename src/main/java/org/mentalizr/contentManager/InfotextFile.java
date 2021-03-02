package org.mentalizr.contentManager;

import java.io.File;

public class InfotextFile {

    private final File infotextFile;

    public InfotextFile(File infotextFile) {
        this.infotextFile = infotextFile;
    }

    public String getIdPart() {
        return this.infotextFile.getName();
    }

}
