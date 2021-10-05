package org.mentalizr.contentManager.programStructure;

public class Modules {

    public static boolean hasSubmodules(Module module) {
        return module.getSubmodules() != null && !module.getSubmodules().isEmpty();
    }
}
