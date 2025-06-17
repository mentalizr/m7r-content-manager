package org.mentalizr.contentManager;

import de.arthurpicht.utils.core.strings.Strings;
import de.arthurpicht.utils.io.nio2.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("StringConcatenationArgumentToLogCall")
public class ProgramRootDirectories {

    private static final Logger logger = LoggerFactory.getLogger(ProgramRootDirectories.class);

    private final List<Path> programRootDirectories;

    public ProgramRootDirectories() {
        programRootDirectories = new ArrayList<>();
    }

    public void addContentRoot(Path contentRoot) throws IOException {
        logger.info("Scanning content root path [" + contentRoot + "] for program directories.");
        List<Path> programRootDirs = FileUtils.getSubdirectoriesNotEndingWithTilde(contentRoot);
        logger.info(
                "Found program directories: " +
                        (programRootDirs.isEmpty() ?
                                "NONE" :
                                Strings.listing(programRootDirs, " ", "", "", "[", "]")
                        )
        );
        this.programRootDirectories.addAll(programRootDirs);
    }

    public List<Path> getProgramRootDirectories() {
        return List.copyOf(this.programRootDirectories);
    }

}
