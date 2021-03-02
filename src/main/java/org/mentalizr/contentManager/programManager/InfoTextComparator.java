package org.mentalizr.contentManager.programManager;


import org.mentalizr.serviceObjects.frontend.Infotext;

import java.util.Comparator;

public class InfoTextComparator implements Comparator<Infotext> {

    @Override
    public int compare(Infotext o1, Infotext o2) {

        if (o1 == null || o1.getId() == null || o2 == null || o2.getId() == null) throw new RuntimeException("Übergabeparameter oder zugehöriges ID-Attribut ist null.");

        return o1.getId().compareTo(o2.getId());
    }
}
