package org.mentalizr.contentManager.programStructure;

public class Submodules {

    public static boolean hasSteps(Submodule submodule) {
        return submodule.steps() != null && !submodule.steps().isEmpty();
    }

}
