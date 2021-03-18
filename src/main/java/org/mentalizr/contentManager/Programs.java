package org.mentalizr.contentManager;

import de.arthurpicht.utils.core.assertion.AssertMethodPrecondition;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.contentRoot.MdpDir;
import org.mentalizr.contentManager.fileHierarchy.contentRoot.ProgramConfFile;
import org.mentalizr.contentManager.fileHierarchy.infopage.InfoDir;
import org.mentalizr.contentManager.fileHierarchy.media.MediaDir;
import org.mentalizr.contentManager.fileHierarchy.module.ModuleConfFile;
import org.mentalizr.contentManager.fileHierarchy.submodule.SubmoduleConfFile;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.mentalizr.contentManager.helper.PathHelper.*;
import static org.mentalizr.contentManager.helper.PathAssertions.assertExistingDirectory;

public class Programs {

    public static void createProgram(Path contentRootPath, String programName, String programDisplayName) throws ProgramManagerException {

        AssertMethodPrecondition.parameterNotNull("contentRootPath", contentRootPath);
        assertExistingDirectory(contentRootPath);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("programName", programName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("programDisplayName", programDisplayName);

        Path programPath = contentRootPath.resolve(programName);
        if (Files.exists(programPath))
            throw new ProgramManagerException("Program [" + programName + "] already existing.");

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

    public static void createModule(Path contentRootPath, String programName, String moduleName, String moduleDisplayName) throws ProgramManagerException {

        AssertMethodPrecondition.parameterNotNull("contentRootPath", contentRootPath);
        assertExistingDirectory(contentRootPath);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("programName", programName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("moduleName", moduleName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("moduleDisplayName", moduleDisplayName);

        Path programPath = contentRootPath.resolve(programName);
        assertExistingDirectory(programPath);

        Path mdpPath = programPath.resolve(MdpDir.DIR_NAME);
        assertExistingDirectory(mdpPath);

        Path modulePath = mdpPath.resolve(moduleName);
        createDirectory(modulePath);

        Path moduleConfPath = modulePath.resolve(ModuleConfFile.FILE_NAME);
        createConfFile(moduleConfPath, moduleDisplayName);
    }

    public static void createSubmodule(Path contentRootPath, String programName, String moduleName, String submoduleName, String submoduleDisplayName) throws ProgramManagerException {

        AssertMethodPrecondition.parameterNotNull("contentRootPath", contentRootPath);
        assertExistingDirectory(contentRootPath);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("programName", programName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("moduleName", moduleName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("submoduleName", submoduleName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("submoduleDisplayName", submoduleDisplayName);

        Path programPath = contentRootPath.resolve(programName);
        assertExistingDirectory(programPath);

        Path mdpPath = programPath.resolve(MdpDir.DIR_NAME);
        assertExistingDirectory(mdpPath);

        Path modulePath = mdpPath.resolve(moduleName);
        assertExistingDirectory(modulePath);

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
            String mdpFileDisplayName) throws ProgramManagerException {

        AssertMethodPrecondition.parameterNotNull("contentRootPath", contentRootPath);
        assertExistingDirectory(contentRootPath);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("programName", programName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("moduleName", moduleName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("submoduleName", submoduleName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("mdpFileName", mdpFileName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("mdpFileDisplayName", mdpFileDisplayName);

        Path programPath = contentRootPath.resolve(programName);
        assertExistingDirectory(programPath);

        Path mdpPath = programPath.resolve(MdpDir.DIR_NAME);
        assertExistingDirectory(mdpPath);

        Path modulePath = mdpPath.resolve(moduleName);
        assertExistingDirectory(modulePath);

        Path submodulePath = modulePath.resolve(submoduleName);
        assertExistingDirectory(submodulePath);

        Path mdpFilePath = submodulePath.resolve(mdpFileName + ".mdp");
        createMdpFile(mdpFilePath, mdpFileDisplayName);
    }

    public static void createMdpInfoStub(
            Path contentRootPath,
            String programName,
            String mdpFileName,
            String mdpFileDisplayName) throws ProgramManagerException {

        AssertMethodPrecondition.parameterNotNull("contentRootPath", contentRootPath);
        assertExistingDirectory(contentRootPath);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("programName", programName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("mdpFileName", mdpFileName);
        AssertMethodPrecondition.parameterNotNullAndNotEmpty("mdpFileDisplayName", mdpFileDisplayName);

        Path programPath = contentRootPath.resolve(programName);
        assertExistingDirectory(programPath);

        Path mdpPath = programPath.resolve(MdpDir.DIR_NAME);
        assertExistingDirectory(mdpPath);

        Path infoPath = mdpPath.resolve(InfoDir.DIR_NAME);
        assertExistingDirectory(infoPath);

        Path mdpFilePath = infoPath.resolve(mdpFileName + ".mdp");
        createMdpFile(mdpFilePath, mdpFileDisplayName);
    }
}
