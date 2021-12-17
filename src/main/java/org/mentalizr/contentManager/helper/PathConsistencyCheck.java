package org.mentalizr.contentManager.helper;

import org.mentalizr.contentManager.exceptions.InconsistencyException;

import java.nio.file.Path;

public class PathConsistencyCheck {

    public static void assertIsExistingDirectory(Path dir) throws InconsistencyException {
        if (!Nio2Helper.isExistingDir(dir))
            throw new InconsistencyException("No valid directory: [" + dir.toAbsolutePath() + "].");
    }

}
