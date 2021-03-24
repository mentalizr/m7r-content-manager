package org.mentalizr.contentManager;

import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.contentManager.build.BuildHandler;
import org.mentalizr.contentManager.build.BuildProcessor;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.MalformedMediaResourceNameException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.NoSuchMediaResourceException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.levels.program.ProgramDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Program {

    private ProgramDir programDir;

    public Program(Path programPath) throws ProgramManagerException {
        this.programDir = new ProgramDir(programPath.toFile());
    }

    public String getName() {
        return this.programDir.getName();
    }

    public List<HtmlFile> getHtmlFiles() {
        return this.programDir.getHtmlFiles();
    }

    public org.mentalizr.serviceObjects.frontend.program.Program asProgram() {
        return this.programDir.asProgram();
    }

    public Path getMediaResource(String fileName) throws MalformedMediaResourceNameException, NoSuchMediaResourceException {
        return this.programDir.getMediaDir().getMediaResource(fileName);
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
