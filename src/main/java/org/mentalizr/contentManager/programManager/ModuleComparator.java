package org.mentalizr.contentManager.programManager;

import java.util.Comparator;
import org.mentalizr.serviceObjects.frontend.Module;

public class ModuleComparator implements Comparator<Module> {

    @Override
    public int compare(Module o1, Module o2) {

        if (o1 == null || o1.getId() == null || o2 == null || o2.getId() == null) throw new RuntimeException("Übergabeparameter oder zugehöriges ID-Attribut ist null.");

        return o1.getId().compareTo(o2.getId());
    }
}
