package org.mentalizr.contentManager;

import de.arthurpicht.utils.core.assertion.AssertMethodPrecondition;
import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.contentManager.exceptions.InconsistencyException;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.HtmlDir;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.MdpDir;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.ProgramConfFile;
import org.mentalizr.contentManager.fileHierarchy.levels.info.InfoDir;
import org.mentalizr.contentManager.fileHierarchy.levels.media.MediaDir;
import org.mentalizr.contentManager.fileHierarchy.levels.module.ModuleConfFile;
import org.mentalizr.contentManager.fileHierarchy.levels.program.ProgramDir;
import org.mentalizr.contentManager.fileHierarchy.levels.submodule.SubmoduleConfFile;
import org.mentalizr.contentManager.helper.Nio2Helper;
import org.mentalizr.contentManager.helper.PathAssertions;
import org.mentalizr.contentManager.helper.ProgramStubs;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static org.mentalizr.contentManager.helper.PathAssertions.assertIsExistingDirectory;
import static org.mentalizr.contentManager.helper.PathHelper.createDirectory;

public class Programs {

    public static void createProgram(Path contentRootPath, String programName, String programDisplayName) throws ContentManagerException {

        AssertMethodPrecondition.parameterNotNull("contentRootPath", contentRootPath);
        assertIsExistingDirectory(contentRootPath);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("programName", programName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("programDisplayName", programDisplayName);

        Path programPath = contentRootPath.resolve(programName);
        if (Files.exists(programPath))
            throw new ContentManagerException("Program [" + programName + "] already existing.");

        createDirectory(programPath);

        Path mdpPath = programPath.resolve(MdpDir.DIR_NAME);
        createDirectory(mdpPath);

        Path infoPath = mdpPath.resolve(InfoDir.DIR_NAME);
        createDirectory(infoPath);

        Path programConfPath = mdpPath.resolve(ProgramConfFile.FILE_NAME);
        ProgramStubs.createConfFile(programConfPath, programDisplayName);

        Path mediaPath = programPath.resolve(MediaDir.DIR_NAME);
        createDirectory(mediaPath);
    }

    public static void createModule(Path contentRootPath, String programName, String moduleName, String moduleDisplayName) throws ContentManagerException {

        AssertMethodPrecondition.parameterNotNull("contentRootPath", contentRootPath);
        assertIsExistingDirectory(contentRootPath);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("programName", programName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("moduleName", moduleName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("moduleDisplayName", moduleDisplayName);

        Path programPath = contentRootPath.resolve(programName);
        assertIsExistingDirectory(programPath);

        Path mdpPath = programPath.resolve(MdpDir.DIR_NAME);
        assertIsExistingDirectory(mdpPath);

        Path modulePath = mdpPath.resolve(moduleName);
        createDirectory(modulePath);

        Path moduleConfPath = modulePath.resolve(ModuleConfFile.FILE_NAME);
        ProgramStubs.createConfFile(moduleConfPath, moduleDisplayName);
    }

    public static void createSubmodule(Path contentRootPath, String programName, String moduleName, String submoduleName, String submoduleDisplayName) throws ContentManagerException {

        AssertMethodPrecondition.parameterNotNull("contentRootPath", contentRootPath);
        assertIsExistingDirectory(contentRootPath);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("programName", programName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("moduleName", moduleName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("submoduleName", submoduleName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("submoduleDisplayName", submoduleDisplayName);

        Path programPath = contentRootPath.resolve(programName);
        assertIsExistingDirectory(programPath);

        Path mdpPath = programPath.resolve(MdpDir.DIR_NAME);
        assertIsExistingDirectory(mdpPath);

        Path modulePath = mdpPath.resolve(moduleName);
        assertIsExistingDirectory(modulePath);

        Path submodulePath = modulePath.resolve(submoduleName);
        createDirectory(submodulePath);

        Path submoduleConfPath = submodulePath.resolve(SubmoduleConfFile.FILE_NAME);
        ProgramStubs.createConfFile(submoduleConfPath, submoduleDisplayName);
    }

    public static void createHtmlDirSkeleton(ProgramDir programDir) throws ContentManagerException, IOException {

        Path programDirPath = programDir.asPath();
        Path mdpDirPath = programDir.getMdpDir().asPath();
        Path htmlDirPath = programDirPath.resolve(HtmlDir.DIR_NAME);

        PathAssertions.assertPathNotExisting(htmlDirPath);

        createDirectory(htmlDirPath);

        Files.walkFileTree(mdpDirPath, new FileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path relativePath = mdpDirPath.relativize(dir);

                if (relativePath.toString().equals("")) return FileVisitResult.CONTINUE;

                Path creationPath = htmlDirPath.resolve(relativePath);
                Files.createDirectory(creationPath);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path relativePath = mdpDirPath.relativize(file);
                if (isConfigurationFile(relativePath)) {
                    Path creationFile = htmlDirPath.resolve(relativePath);
                    Files.copy(file, creationFile);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                return FileVisitResult.CONTINUE;
            }

            private boolean isConfigurationFile(Path relativePath) {
                String fileName = relativePath.getFileName().toString();
                if (fileName.endsWith(".conf")) {
                    int depth = relativePath.getNameCount();
                    return ((depth == 1 && fileName.equals("program.conf"))
                            || (depth == 2 && fileName.equals("module.conf"))
                            || (depth == 3 && fileName.equals("submodule.conf")));

                }
                return false;
            }

        });
    }

    public static void forceClean(Path programPath) throws ContentManagerException {
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

    public static void assertIsProgramDirByPlausibility(Path programPath) throws InconsistencyException {

        if (!Nio2Helper.isExistingDir(programPath))
            throw new InconsistencyException("Program repo not existing. [" + programPath.toAbsolutePath() + "]");

        Path mdpDir = programPath.resolve(MdpDir.DIR_NAME);
        if (!Nio2Helper.isExistingDir(mdpDir))
            throw new InconsistencyException("No program repo. mdp directory missing. [" + mdpDir.toAbsolutePath() + "]");

        Path mediaDir = programPath.resolve(MediaDir.DIR_NAME);
        if (!Nio2Helper.isExistingDir(mediaDir))
            throw new InconsistencyException("No program repo. media directory missing. [" + mediaDir.toAbsolutePath() + "]");

        Path infoDir = mdpDir.resolve(InfoDir.DIR_NAME);
        if (!Nio2Helper.isExistingDir(infoDir))
            throw new InconsistencyException("No program repo. info directory missing. [" + infoDir.toAbsolutePath() + "]");

        Path programConfig = mdpDir.resolve(ProgramConfFile.FILE_NAME);
        if (!Nio2Helper.isExistingRegularFile(programConfig))
            throw new InconsistencyException("No program repo. program.config file missing. [" + programConfig.toAbsolutePath() + "]");
    }

}
