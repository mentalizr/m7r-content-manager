package org.mentalizr.contentManager.outdated.programManager;


import org.mentalizr.serviceObjects.frontend.program.Infotext;

import java.util.Comparator;

@Deprecated
public class InfoTextComparator implements Comparator<Infotext> {

    @Override
    public int compare(Infotext o1, Infotext o2) {

        if (o1 == null || o1.getId() == null || o2 == null || o2.getId() == null) throw new RuntimeException("Übergabeparameter oder zugehöriges ID-Attribut ist null.");

        return o1.getId().compareTo(o2.getId());
    }
}