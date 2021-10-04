package org.mentalizr.contentManager.programStructure;

import java.util.ArrayList;
import java.util.List;

public class ProgramStructures {

    public static List<Step> stepList(ProgramStructure programStructure) {
        List<Step> stepList = new ArrayList<>();
        for (Module module : programStructure.getModules()) {
            for (Submodule submodule : module.getSubmodules()) {
                stepList.addAll(submodule.getSteps());
            }
        }
        return stepList;
    }

}
