package org.mentalizr.contentManager.outdated.programManager;

import org.mentalizr.serviceObjects.frontend.program.Submodule;

import java.util.Comparator;

@Deprecated
public class SubmoduleComparator implements Comparator<Submodule> {

    @Override
    public int compare(Submodule o1, Submodule o2) {

        if (o1 == null || o1.getId() == null || o2 == null || o2.getId() == null) throw new RuntimeException("Übergabeparameter oder zugehöriges ID-Attribut ist null.");

        return o1.getId().compareTo(o2.getId());
    }
}
