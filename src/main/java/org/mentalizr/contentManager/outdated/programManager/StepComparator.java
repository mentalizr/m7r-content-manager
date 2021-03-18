package org.mentalizr.contentManager.outdated.programManager;

import java.util.Comparator;

import org.mentalizr.serviceObjects.frontend.program.Step;

@Deprecated
public class StepComparator implements Comparator<Step> {

    @Override
    public int compare(Step o1, Step o2) {

        if (o1 == null || o1.getId() == null || o2 == null || o2.getId() == null) throw new RuntimeException("Übergabeparameter oder zugehöriges ID-Attribut ist null.");

        return o1.getId().compareTo(o2.getId());
    }
}
