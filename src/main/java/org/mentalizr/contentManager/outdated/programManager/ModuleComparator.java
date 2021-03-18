package org.mentalizr.contentManager.outdated.programManager;

import org.mentalizr.serviceObjects.frontend.program.Module;

import java.util.Comparator;

@Deprecated
public class ModuleComparator implements Comparator<Module> {

    @Override
    public int compare(Module o1, Module o2) {

        if (o1 == null || o1.getId() == null || o2 == null || o2.getId() == null) throw new RuntimeException("Übergabeparameter oder zugehöriges ID-Attribut ist null.");

        return o1.getId().compareTo(o2.getId());
    }
}
