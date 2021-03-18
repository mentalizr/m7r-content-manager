package org.mentalizr.contentManager.build;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;
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
import java.util.List;

import static org.mentalizr.contentManager.helper.PathHelper.createDirectory;

public class BuildProcessor {

    public static void createHtmlDirSkeleton(ProgramDir programDir) throws ProgramManagerException, IOException {

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

    public static void compile(ProgramDir programDir, BuildHandler buildHandler) throws ProgramManagerException {

        if (!programDir.hasHtmlDir())
            throw new IllegalArgumentException("Program has no html directory: "
                    + programDir.asPath().toAbsolutePath().toString());

        Path mdpDirPath = programDir.getMdpDir().asPath();
        Path htmlDirPath = programDir.getHtmlDir().asPath();

        List<MdpFile> mdpFiles = programDir.getMdpFiles();


        for (MdpFile mdpFile : mdpFiles) {
            Path htmlFile = getHtmlDestination(mdpDirPath, htmlDirPath, mdpFile);
            List<String> htmlLines = buildHandler.compile(mdpFile);
            writeAllLines(htmlFile, htmlLines);
        }
    }

    private static void writeAllLines(Path file, List<String> lines) throws ProgramManagerException {
        try {
            Files.write(file, lines);
        } catch (IOException e) {
            throw new ProgramManagerException(e);
        }
    }

//    private static List<String> readAllLines(MdpFile mdpFile) throws ProgramManagerException {
//        try {
//            return Files.readAllLines(mdpFile.asPath());
//        } catch (IOException e) {
//            throw new ProgramManagerException(e);
//        }
//    }

    private static Path getHtmlDestination(Path mdpDir, Path htmlDir, MdpFile mdpFile) {
        Path relativePath = mdpDir.relativize(mdpFile.asPath());
        Path creationFile = htmlDir.resolve(relativePath);

        Path creationDir = creationFile.getParent();
        String htmlFileName = mdpFile.getName() + HtmlFile.FILETYPE;

        return creationDir.resolve(htmlFileName);
    }

}
