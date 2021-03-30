package org.mentalizr.contentManager;

import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.contentManager.build.BuildHandler;
import org.mentalizr.contentManager.build.BuildProcessor;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.MalformedMediaResourceNameException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.NoSuchMediaResourceException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.HtmlDir;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.MdpDir;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.ProgramConfFile;
import org.mentalizr.contentManager.fileHierarchy.levels.info.InfoDir;
import org.mentalizr.contentManager.fileHierarchy.levels.media.MediaDir;
import org.mentalizr.contentManager.fileHierarchy.levels.program.ProgramDir;
import org.mentalizr.contentManager.helper.Nio2Helper;
import org.mentalizr.contentManager.helper.PathAssertions;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Program {

    private ProgramDir programDir;

    public Program(Path programPath) throws ProgramManagerException {
        this.programDir = new ProgramDir(programPath.toFile());
    }

    public static void assertProgramDirByPlausibility(Path programPath) throws ProgramManagerException {
        if (!Nio2Helper.isExistingDir(programPath))
            throw new ProgramManagerException("Program repo not existing. [" + programPath.toAbsolutePath() + "]");

        Path mdpDir = programPath.resolve(MdpDir.DIR_NAME);
        if (!Nio2Helper.isExistingDir(mdpDir))
            throw new ProgramManagerException("No program repo. mdp directory missing. [" + mdpDir.toAbsolutePath() + "]");

        Path mediaDir = programPath.resolve(MediaDir.DIR_NAME);
        if (!Nio2Helper.isExistingDir(mediaDir))
            throw new ProgramManagerException("No program repo. media directory missing. [" + mediaDir.toAbsolutePath() + "]");

        Path infoDir = mdpDir.resolve(InfoDir.DIR_NAME);
        if (!Nio2Helper.isExistingDir(infoDir))
            throw new ProgramManagerException("No program repo. info directory missing. [" + infoDir.toAbsolutePath() + "]");

        Path programConfig = mdpDir.resolve(ProgramConfFile.FILE_NAME);
        if (!Nio2Helper.isExistingRegularFile(programConfig))
            throw new ProgramManagerException("No program repo. program.config file missing. [" + programConfig.toAbsolutePath() + "]");
    }

    public static void assertHasHtmlDir(Path programPath) throws ProgramManagerException {
        if (!Nio2Helper.isExistingDir(programPath))
            throw new ProgramManagerException("Program repo not existing. [" + programPath.toAbsolutePath() + "]");

        Path htmlDir = programPath.resolve(HtmlDir.DIR_NAME);
        if (!Nio2Helper.isExistingDir(htmlDir))
            throw new ProgramManagerException("No html directory in program repo. [" + htmlDir.toAbsolutePath() + "]");
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

    public boolean isBuilt() {
        return this.programDir.hasHtmlDir();
    }

    public void clean() throws ProgramManagerException {
        if (this.isBuilt()) {
            removeHtmlDir();
            reinitializeProgramDir();
        }
    }

    public void build(BuildHandler buildHandler) throws ProgramManagerException {
        clean();
        try {
            BuildProcessor.createHtmlDirSkeleton(this.programDir);
            this.programDir = ProgramDir.reinitializeHtmlDir(this.programDir);
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
