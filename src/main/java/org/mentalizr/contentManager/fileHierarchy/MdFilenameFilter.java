package org.mentalizr.contentManager.fileHierarchy;

import java.io.File;
import java.io.FilenameFilter;

public class MdFilenameFilter implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {
        return MdpFile.isMdFile(name);
    }

}
