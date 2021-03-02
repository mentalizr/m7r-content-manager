package org.mentalizr.contentManager.contentManager;

import de.arthurpicht.utils.core.strings.Strings;

import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;

public class ContentFileVisitor<T> extends SimpleFileVisitor<Path> {

    private static final String HTML_POSTFIX = ".html";
    private static final String INFO_DIR_NAME = "_info";

    private Map<String, File> stepContentMap;
    private Map<String, File> infoContentMap;
    private File contentRoot;

    public ContentFileVisitor(Map<String, File> stepContentMap, Map<String, File> infoContentMap, File contentRoot) {
        this.stepContentMap = stepContentMap;
        this.infoContentMap = infoContentMap;
        this.contentRoot = contentRoot;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) {

        if (!isHtmlFile(path)) return FileVisitResult.CONTINUE;

        if (isStepContent(path)) processStepContent(path);
        if (isInfoContentLevel(path)) processInfoContent(path);

        return FileVisitResult.CONTINUE;
    }

    private void processStepContent(Path path) {
        Path relativePath = contentRoot.toPath().relativize(path);
        String id = buildId(relativePath);
        stepContentMap.put(id, path.toFile());
    }

    private void processInfoContent(Path path) {
        Path relativePath = contentRoot.toPath().relativize(path);
        String id = buildId(relativePath);
        infoContentMap.put(id, path.toFile());
    }

    private String buildId(Path relativePath) {

        String id = "";
        for (Path namePath : relativePath) {
            if (!id.equals("")) id = id + "_";
            id = id + namePath.toString();
        }

        id = Strings.cutEnd(id, HTML_POSTFIX.length());

        return id;
    }

    private boolean isHtmlFile(Path path) {
        return path.getFileName().toString().endsWith(HTML_POSTFIX);
    }

    private boolean isStepContent(Path path) {
        Path relativePath = contentRoot.toPath().relativize(path);
        return (relativePath.getNameCount() == 4);
    }

    private boolean isInfoContentLevel(Path path) {
        Path relativePath = contentRoot.toPath().relativize(path);
        return (relativePath.getNameCount() == 3 && relativePath.getName(1).toString().equals(INFO_DIR_NAME));
    }

}
