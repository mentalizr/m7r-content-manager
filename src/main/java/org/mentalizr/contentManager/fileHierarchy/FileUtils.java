package org.mentalizr.contentManager.fileHierarchy;

import java.io.File;

public class FileUtils {

    public static void assertFileName(File file, String fileName) {
        if (!file.getName().equals(fileName))
            throw new RuntimeException("Assertion failed. File name [" + fileName + "] expected " +
                    "but is [" + file.getName() + "]");
    }

}
