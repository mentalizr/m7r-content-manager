package org.mentalizr.contentManager.fileHierarchy;

import java.util.Comparator;

public class SubmoduleDirMdpComparator implements Comparator<SubmoduleDirMdp> {

    @Override
    public int compare(SubmoduleDirMdp o1, SubmoduleDirMdp o2) {
        return o1.getDirName().compareTo(o2.getDirName());
    }

}
