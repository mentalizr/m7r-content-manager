package org.mentalizr.contentManager.fileHierarchy;

import java.util.Comparator;

public class MdpFileComparator implements Comparator<MdpFile> {

    @Override
    public int compare(MdpFile file1, MdpFile file2) {
        return file1.getFileName().compareTo(file2.getFileName());
    }

}
