package org.mentalizr.contentManager.helper;

import org.mentalizr.contentManager.Programs;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.MdpDir;
import org.mentalizr.contentManager.fileHierarchy.levels.info.InfoDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNull;
import static de.arthurpicht.utils.core.assertion.MethodPreconditions.assertArgumentNotNullAndNotEmpty;
import static de.arthurpicht.utils.io.assertions.PathAssertions.assertIsExistingDirectory;

public class ProgramStubs {

    public static void createConfFile(Path path, String displayName) throws ContentManagerException {
        try {
            Files.writeString(path, "name=" + displayName + "\n");
        } catch (IOException e) {
            throw new ContentManagerException("Exception when writing configuration file ["
                    + path.toAbsolutePath().toString() + "]: "
                    + e.getMessage());
        }
    }

    public static void createMdpFile(Path path, String displayName) throws ContentManagerException {
        try {
            Files.writeString(path, "@@name=" + displayName + "\n\n// automatically generated mdp file stub/n");
        } catch (IOException e) {
            throw new ContentManagerException("Exception when writing configuration file ["
                    + path.toAbsolutePath().toString() + "]: "
                    + e.getMessage());
        }
    }

    public static void createMdpStub(
            Path contentRootPath,
            String programName,
            String moduleName,
            String submoduleName,
            String mdpFileName,
            String mdpFileDisplayName) throws ContentManagerException {

        assertArgumentNotNull("contentRootPath", contentRootPath);
        assertIsExistingDirectory(contentRootPath);
        assertArgumentNotNullAndNotEmpty("programName", programName);
        assertArgumentNotNullAndNotEmpty("moduleName", moduleName);
        assertArgumentNotNullAndNotEmpty("submoduleName", submoduleName);
        assertArgumentNotNullAndNotEmpty("mdpFileName", mdpFileName);
        assertArgumentNotNullAndNotEmpty("mdpFileDisplayName", mdpFileDisplayName);

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

        assertArgumentNotNull("contentRootPath", contentRootPath);
        assertIsExistingDirectory(contentRootPath);
        assertArgumentNotNullAndNotEmpty("programName", programName);
        assertArgumentNotNullAndNotEmpty("mdpFileName", mdpFileName);
        assertArgumentNotNullAndNotEmpty("mdpFileDisplayName", mdpFileDisplayName);

        Path programPath = contentRootPath.resolve(programName);
        assertIsExistingDirectory(programPath);

        Path mdpPath = programPath.resolve(MdpDir.DIR_NAME);
        assertIsExistingDirectory(mdpPath);

        Path infoPath = mdpPath.resolve(InfoDir.DIR_NAME);
        assertIsExistingDirectory(infoPath);

        Path mdpFilePath = infoPath.resolve(mdpFileName + ".mdp");
        createMdpFile(mdpFilePath, mdpFileDisplayName);
    }

    public static void createTestProgram(Path programRootPath) throws ContentManagerException {
        Programs.createProgram(programRootPath, "test", "Test");
        Programs.createModule(programRootPath, "test", "m1", "Module1");
        Programs.createSubmodule(programRootPath, "test", "m1", "sm1", "Submodule1");
        createMdpStub(programRootPath, "test", "m1", "sm1", "step01", "Step01");
        createMdpStub(programRootPath, "test", "m1", "sm1", "step02", "Step02");
        Programs.createSubmodule(programRootPath, "test", "m1", "sm2", "Submodule2");
        createMdpStub(programRootPath, "test", "m1", "sm2", "step01", "Step01");
        createMdpStub(programRootPath, "test", "m1", "sm2", "step02", "Step02");
        Programs.createModule(programRootPath, "test", "m2", "Module2");
        Programs.createSubmodule(programRootPath, "test", "m2", "sm1", "Submodule1");
        createMdpStub(programRootPath, "test", "m2", "sm1", "step01", "Step01");
        createMdpInfoStub(programRootPath, "test", "info01", "Info01");
    }

}
