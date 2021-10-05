package org.mentalizr.contentManager.build;

import de.arthurpicht.utils.core.collection.Lists;
import org.mentalizr.contentManager.ContentManager;
import org.mentalizr.contentManager.Program;
import org.mentalizr.contentManager.buildHandler.BuildHandler;
import org.mentalizr.contentManager.buildHandler.BuildHandlerException;
import org.mentalizr.contentManager.buildHandler.BuildHandlerFactory;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.HtmlDir;
import org.mentalizr.contentManager.fileHierarchy.levels.program.ProgramDir;
import org.mentalizr.contentManager.helper.PathAssertions;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static org.mentalizr.contentManager.helper.PathHelper.createDirectory;

public class BuildProcessor {

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

    public static BuildSummary compile(Program program, BuildHandlerFactory buildHandlerFactory) throws ContentManagerException {
        ProgramDir programDir = program.getProgramDir();
        assertHtmlDir(programDir);

        Path mdpDirPath = programDir.getMdpDir().asPath();
        Path htmlDirPath = programDir.getHtmlDir().asPath();

        List<MdpFile> mdpFiles = programDir.getMdpFiles();
        BuildSummary buildSummary = new BuildSummary();

        for (MdpFile mdpFile : mdpFiles) {
            Path htmlFile = Program.getHtmlDestinationForMdpFile(mdpDirPath, htmlDirPath, mdpFile);
            List<String> htmlLines = compileMdpFile(mdpFile, program, buildSummary, buildHandlerFactory);
            writeAllLines(htmlFile, htmlLines);
        }

        // perform validation
        new ContentManager(Lists.newArrayList(program.getProgramDir().asPath()));

        return buildSummary;
    }

    private static List<String> compileMdpFile(MdpFile mdpFile, Program program, BuildSummary buildSummary, BuildHandlerFactory buildHandlerFactory) {
        try {
            BuildHandler buildHandler = buildHandlerFactory.createBuildHandler(program, mdpFile);
            List<String> htmlLines = buildHandler.compile();
            buildSummary.addSuccessfulMdpFile(mdpFile);
            return htmlLines;
        } catch (BuildHandlerException buildHandlerException) {
            Exception exception
                    = (buildHandlerException.getCause() != null) && (buildHandlerException.getCause() instanceof  Exception)
                    ? (Exception) buildHandlerException.getCause() : buildHandlerException;
            buildSummary.addFailedMdpFiles(mdpFile, exception);
            return new ArrayList<>();
        }
    }

    private static void writeAllLines(Path file, List<String> lines) throws ContentManagerException {
        try {
            Files.write(file, lines);
        } catch (IOException e) {
            throw new ContentManagerException(e);
        }
    }

    public static void assertHtmlDir(ProgramDir programDir) {
        if (!programDir.hasHtmlDir())
            throw new IllegalArgumentException("Program has no html directory: "
                    + programDir.asPath().toAbsolutePath());
    }

}
