package org.mentalizr.contentManager.fileHierarchy;

import java.util.Comparator;

public class MdpFileComparator implements Comparator<MdpFile> {

    @Override
    public int compare(MdpFile file1, MdpFile file2) {
        return file1.getName().compareTo(file2.getName());
    }

}
