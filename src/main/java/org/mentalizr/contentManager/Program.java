package org.mentalizr.contentManager;

import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.contentManager.build.BuildProcessor;
import org.mentalizr.contentManager.build.BuildSummary;
import org.mentalizr.contentManager.buildHandler.BuildHandler;
import org.mentalizr.contentManager.buildHandler.BuildHandlerException;
import org.mentalizr.contentManager.buildHandler.BuildHandlerFactory;
import org.mentalizr.contentManager.exceptions.ConsistencyException;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.MalformedMediaResourceNameException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.NoSuchMediaResourceException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.HtmlDir;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.MdpDir;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.ProgramConfFile;
import org.mentalizr.contentManager.fileHierarchy.levels.info.InfoDir;
import org.mentalizr.contentManager.fileHierarchy.levels.media.MediaDir;
import org.mentalizr.contentManager.fileHierarchy.levels.program.ProgramDir;
import org.mentalizr.contentManager.helper.Nio2Helper;
import org.mentalizr.contentManager.programStructure.ProgramStructure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Program {

    private ProgramDir programDir;

    public Program(Path programPath) throws ContentManagerException {
        this.programDir = new ProgramDir(programPath.toFile());
    }

    public String getName() {
        return this.programDir.getName();
    }

    public ProgramDir getProgramDir() {
        return this.programDir;
    }

    public List<HtmlFile> getHtmlFiles() {
        return this.programDir.getHtmlFiles();
    }

    public List<MdpFile> getMdpFiles() {
        return this.programDir.getMdpFiles();
    }

    public ProgramStructure asProgramStructure() {
        return this.programDir.asProgramStructure();
    }

    public Path getMediaResource(String fileName) throws MalformedMediaResourceNameException, NoSuchMediaResourceException {
        return this.programDir.getMediaDir().getMediaResource(fileName);
    }

    public boolean isBuilt() {
        return this.programDir.hasHtmlDir();
    }

    public void clean() throws ContentManagerException {
        if (this.isBuilt()) {
            removeHtmlDir();
            reinitializeProgramDir();
        }
    }

    public BuildSummary build(BuildHandlerFactory buildHandlerFactory) throws ContentManagerException {
        clean();
        createHtmlDirSkeleton();

        BuildSummary buildSummary = BuildProcessor.compile(this, buildHandlerFactory);
        if (!buildSummary.isSuccess()) {
            forceClean(this.programDir.asPath());
        }

        reinitializeProgramDir();
        return buildSummary;
    }

    public Set<String> getAllMediaResourceNames() throws ContentManagerException {
        return this.programDir.getMediaDir().getAllMediaResourceNames();
    }

    public static void forceClean(Path programPath) throws ContentManagerException {

        Program.assertProgramDirByPlausibility(programPath);

        Path htmlDir = programPath.resolve(HtmlDir.DIR_NAME);
        if (Files.exists(htmlDir)) {
            try {
                FileUtils.rmDir(htmlDir);
            } catch (IOException e) {
                throw new ContentManagerException("Exception on cleaning html file." +
                        " [" + htmlDir.toAbsolutePath() + "]", e);
            }
        }
    }

    public static void assertProgramDirByPlausibility(Path programPath) throws ConsistencyException {

        if (!Nio2Helper.isExistingDir(programPath))
            throw new ConsistencyException("Program repo not existing. [" + programPath.toAbsolutePath() + "]");

        Path mdpDir = programPath.resolve(MdpDir.DIR_NAME);
        if (!Nio2Helper.isExistingDir(mdpDir))
            throw new ConsistencyException("No program repo. mdp directory missing. [" + mdpDir.toAbsolutePath() + "]");

        Path mediaDir = programPath.resolve(MediaDir.DIR_NAME);
        if (!Nio2Helper.isExistingDir(mediaDir))
            throw new ConsistencyException("No program repo. media directory missing. [" + mediaDir.toAbsolutePath() + "]");

        Path infoDir = mdpDir.resolve(InfoDir.DIR_NAME);
        if (!Nio2Helper.isExistingDir(infoDir))
            throw new ConsistencyException("No program repo. info directory missing. [" + infoDir.toAbsolutePath() + "]");

        Path programConfig = mdpDir.resolve(ProgramConfFile.FILE_NAME);
        if (!Nio2Helper.isExistingRegularFile(programConfig))
            throw new ConsistencyException("No program repo. program.config file missing. [" + programConfig.toAbsolutePath() + "]");
    }

    public static void assertHasHtmlDir(Path programPath) throws ContentManagerException {
        if (!Nio2Helper.isExistingDir(programPath))
            throw new ContentManagerException("Program repo not existing. [" + programPath.toAbsolutePath() + "]");

        Path htmlDir = programPath.resolve(HtmlDir.DIR_NAME);
        if (!Nio2Helper.isExistingDir(htmlDir))
            throw new ContentManagerException("No html directory in program repo. [" + htmlDir.toAbsolutePath() + "]");
    }

    public static Path getHtmlDestinationForMdpFile(Path mdpDir, Path htmlDir, MdpFile mdpFile) {
        Path relativePath = mdpDir.relativize(mdpFile.asPath());
        Path creationFile = htmlDir.resolve(relativePath);

        Path creationDir = creationFile.getParent();
        String htmlFileName = mdpFile.getName() + HtmlFile.FILETYPE;

        return creationDir.resolve(htmlFileName);
    }

    public Path getHtmlDestinationForMdpFile(MdpFile mdpFile) {
        Path relativePath = this.programDir.getMdpDir().asPath().relativize(mdpFile.asPath());
        Path creationFile = this.programDir.getHtmlDir().asPath().resolve(relativePath);

        Path creationDir = creationFile.getParent();
        String htmlFileName = mdpFile.getName() + HtmlFile.FILETYPE;

        return creationDir.resolve(htmlFileName);
    }

    public Set<String> getReferencedMediaResources(BuildHandlerFactory buildHandlerFactory) throws ContentManagerException {
        if (!isBuilt()) throw new RuntimeException("Program not built yet. Check before calling.");

        List<MdpFile> mdpFiles = programDir.getMdpFiles();
        Set<String> referencesMediaResources = new HashSet<>();

        for (MdpFile mdpFile : mdpFiles) {
            Set<String> mediaFileOfSingleMdpFile;
            try {
                BuildHandler buildHandler = buildHandlerFactory.createBuildHandler(this, mdpFile);
                mediaFileOfSingleMdpFile = buildHandler.getReferencedMediaResources();
            } catch (BuildHandlerException e) {
                throw new ContentManagerException(e);
            }
            referencesMediaResources.addAll(mediaFileOfSingleMdpFile);
        }

        return referencesMediaResources;
    }

    private void removeHtmlDir() throws ContentManagerException {
        Path htmlDir = this.programDir.getHtmlDir().asPath();
        try {
            FileUtils.rmDir(htmlDir);
        } catch (IOException e) {
            throw new ContentManagerException("Exception on cleaning html file." +
                    " [" + htmlDir.toAbsolutePath() + "]", e);
        }
    }

    private void createHtmlDirSkeleton() throws ContentManagerException {
        try {
            BuildProcessor.createHtmlDirSkeleton(this.programDir);
            this.programDir = ProgramDir.reinitializeHtmlDir(this.programDir);
        } catch (IOException e) {
            throw new ContentManagerException(e);
        }
    }

    private void reinitializeProgramDir() throws ContentManagerException {
        Path programPath = this.programDir.asPath();
        this.programDir = new ProgramDir(programPath.toFile());
    }
}
