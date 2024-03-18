package org.mentalizr.contentManager.programStructure;

@Deprecated
public class Submodules {

    public static boolean hasSteps(Submodule submodule) {
        return submodule.steps() != null && !submodule.steps().isEmpty();
    }

}
