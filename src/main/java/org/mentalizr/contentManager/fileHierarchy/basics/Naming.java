package org.mentalizr.contentManager.fileHierarchy.basics;

public class Naming {

    public static boolean isValidProgramName(String programName) {
        return programName.matches("^[a-zA-Z][a-zA-Z-_0-9]*");
    }

}
