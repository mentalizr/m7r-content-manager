package org.mentalizr.contentManager.fileHierarchy;

import java.util.Comparator;

public class ModuleDirMdpComparator implements Comparator<ModuleDirMdp> {

    @Override
    public int compare(ModuleDirMdp o1, ModuleDirMdp o2) {
        return o1.getName().compareTo(o2.getName());
    }

}
