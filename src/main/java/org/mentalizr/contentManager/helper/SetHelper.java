package org.mentalizr.contentManager.helper;

import java.util.HashSet;
import java.util.Set;

public class SetHelper {

    // TODO move to core utils

    public static <E> Set<E> subtract(Set<E> minuend, Set<E> subtrahend) {
        Set<E> set = new HashSet<>(minuend);
        set.removeIf(subtrahend::contains);
        return set;
    }

}
