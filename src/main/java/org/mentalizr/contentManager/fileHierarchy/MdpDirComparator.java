package org.mentalizr.contentManager.fileHierarchy;

import java.util.Comparator;

public class MdpDirComparator implements Comparator<MdpDir> {

    @Override
    public int compare(MdpDir mdpDir1, MdpDir mdpDir2) {
        return mdpDir1.getName().compareTo(mdpDir2.getName());
    }

}
