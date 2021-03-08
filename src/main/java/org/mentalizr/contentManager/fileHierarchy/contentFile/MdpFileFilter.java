package org.mentalizr.contentManager.fileHierarchy.contentFile;

import org.mentalizr.contentManager.fileHierarchy.RepoFile;

import java.io.File;
import java.io.FileFilter;

public class MdpFileFilter implements FileFilter {

    @Override
    public boolean accept(File file) {
        return RepoFile.isTypeAppropriate(file, MdpFile.FILETYPE);
    }
}