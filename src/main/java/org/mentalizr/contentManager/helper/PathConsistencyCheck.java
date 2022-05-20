package org.mentalizr.contentManager.helper;

import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.contentManager.exceptions.InconsistencyException;

import java.nio.file.Path;

public class PathConsistencyCheck {

    public static void assertIsExistingDirectory(Path dir) throws InconsistencyException {
        if (!FileUtils.isExistingDirectory(dir))
            throw new InconsistencyException("No valid directory: [" + dir.toAbsolutePath() + "].");
    }

}
