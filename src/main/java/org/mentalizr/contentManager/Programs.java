package org.mentalizr.contentManager;

import de.arthurpicht.utils.core.assertion.AssertMethodPrecondition;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.MdpDir;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.ProgramConfFile;
import org.mentalizr.contentManager.fileHierarchy.levels.info.InfoDir;
import org.mentalizr.contentManager.fileHierarchy.levels.media.MediaDir;
import org.mentalizr.contentManager.fileHierarchy.levels.module.ModuleConfFile;
import org.mentalizr.contentManager.fileHierarchy.levels.submodule.SubmoduleConfFile;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.mentalizr.contentManager.helper.PathAssertions.assertIsExistingDirectory;
import static org.mentalizr.contentManager.helper.PathHelper.*;

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
        createConfFile(programConfPath, programDisplayName);

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
        createConfFile(moduleConfPath, moduleDisplayName);
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
        createConfFile(submoduleConfPath, submoduleDisplayName);
    }

    public static void createMdpStub(
            Path contentRootPath,
            String programName,
            String moduleName,
            String submoduleName,
            String mdpFileName,
            String mdpFileDisplayName) throws ContentManagerException {

        AssertMethodPrecondition.parameterNotNull("contentRootPath", contentRootPath);
        assertIsExistingDirectory(contentRootPath);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("programName", programName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("moduleName", moduleName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("submoduleName", submoduleName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("mdpFileName", mdpFileName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("mdpFileDisplayName", mdpFileDisplayName);

        Path programPath = contentRootPath.resolve(programName);
        assertIsExistingDirectory(programPath);

        Path mdpPath = programPath.resolve(MdpDir.DIR_NAME);
        assertIsExistingDirectory(mdpPath);

        Path modulePath = mdpPath.resolve(moduleName);
        assertIsExistingDirectory(modulePath);

        Path submodulePath = modulePath.resolve(submoduleName);
        assertIsExistingDirectory(submodulePath);

        Path mdpFilePath = submodulePath.resolve(mdpFileName + ".mdp");
        createMdpFile(mdpFilePath, mdpFileDisplayName);
    }

    public static void createMdpInfoStub(
            Path contentRootPath,
            String programName,
            String mdpFileName,
            String mdpFileDisplayName) throws ContentManagerException {

        AssertMethodPrecondition.parameterNotNull("contentRootPath", contentRootPath);
        assertIsExistingDirectory(contentRootPath);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("programName", programName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("mdpFileName", mdpFileName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("mdpFileDisplayName", mdpFileDisplayName);

        Path programPath = contentRootPath.resolve(programName);
        assertIsExistingDirectory(programPath);

        Path mdpPath = programPath.resolve(MdpDir.DIR_NAME);
        assertIsExistingDirectory(mdpPath);

        Path infoPath = mdpPath.resolve(InfoDir.DIR_NAME);
        assertIsExistingDirectory(infoPath);

        Path mdpFilePath = infoPath.resolve(mdpFileName + ".mdp");
        createMdpFile(mdpFilePath, mdpFileDisplayName);
    }

}
