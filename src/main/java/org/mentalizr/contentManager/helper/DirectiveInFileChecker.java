package org.mentalizr.contentManager.helper;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.exceptions.InconsistencyException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public class DirectiveInFileChecker {

    private static final String feedbackDirective = "@@feedback";
    private static final String exerciseDirective = "@@exercise";
    private static final String nameDirective = "@@name";

    public static boolean hasFeedbackDirective(Path path) throws ContentManagerException {
        return hasDirective(path, feedbackDirective);
    }

    public static boolean hasExerciseDirective(Path path) throws ContentManagerException {
        return hasDirective(path, exerciseDirective);
    }

    public static boolean hasDirective(Path path, String directive) throws ContentManagerException {
        Stream<String> lines;
        try {
            lines = Files.lines(path);
        } catch (IOException e) {
            throw new ContentManagerException("IOException when accessing [" + path.toAbsolutePath() + "]", e);
        }
        return lines.limit(10).anyMatch(line -> line.startsWith(directive));
    }

    public static String obtainDisplayName(Path path) throws IOException, InconsistencyException {
        Stream<String> lines = Files.lines(path);
        String token = nameDirective + "=";
        Optional<String> nameLine = lines.limit(10).filter(line -> line.startsWith(token)).findFirst();
        if (nameLine.isEmpty())
            throw new InconsistencyException("Syntax error in [" + path.toAbsolutePath() + "]. Tag @@name not found.");
        String[] splitString = Strings.splitAtDelimiter(nameLine.get(), token);
        return splitString[1];
    }

}