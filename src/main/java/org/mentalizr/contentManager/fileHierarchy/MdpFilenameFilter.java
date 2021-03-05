package org.mentalizr.contentManager.fileHierarchy;

import java.io.File;
import java.io.FilenameFilter;

public class MdpFilenameFilter implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {
        return MdpFile.isMdpFile(name);
    }

}
