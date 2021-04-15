package org.mentalizr.contentManager.helper;

import org.mentalizr.contentManager.exceptions.ConsistencyException;

import java.nio.file.Path;

public class PathConsistencyCheck {

    public static void assertIsExistingDirectory(Path dir) throws ConsistencyException {
        if (!Nio2Helper.isExistingDir(dir))
            throw new ConsistencyException("Inconsistent content repository. Existing directory expected: ["
                    + dir.toAbsolutePath() + "].");
    }

}
