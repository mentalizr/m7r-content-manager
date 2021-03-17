package org.mentalizr.contentManager;

import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.contentManager.build.BuildHandler;
import org.mentalizr.contentManager.build.BuildProcessor;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoDirectory;
import org.mentalizr.contentManager.fileHierarchy.program.ProgramDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Program {

    private ProgramDir programDir;

    public Program(Path programPath) throws ProgramManagerException {
        this.programDir = new ProgramDir(programPath.toFile());
    }

    public void clean() throws ProgramManagerException {
        if (this.programDir.hasHtmlDir()) {
            removeHtmlDir();
            reinitializeProgramDir();
        }
    }

    public void build(BuildHandler buildHandler) throws ProgramManagerException {
        clean();
        try {
            BuildProcessor.createHtmlDirSkeleton(this.programDir);
            BuildProcessor.compile(this.programDir, buildHandler);
        } catch (IOException e) {
            throw new ProgramManagerException(e);
        }
        reinitializeProgramDir();
    }

    private void removeHtmlDir() throws ProgramManagerException {
        Path htmlDir = this.programDir.getHtmlDir().asPath();
        try {
            FileUtils.rmDir(htmlDir);
        } catch (IOException e) {
            throw new ProgramManagerException("Exception on cleaning html file." +
                    " [" + htmlDir.toAbsolutePath().toString() + "]", e);
        }
    }

    private void reinitializeProgramDir() throws ProgramManagerException {
        Path programPath = this.programDir.asPath();
        this.programDir = new ProgramDir(programPath.toFile());
    }

}
