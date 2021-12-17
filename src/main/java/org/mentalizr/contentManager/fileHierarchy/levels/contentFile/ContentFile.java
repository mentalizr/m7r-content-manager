package org.mentalizr.contentManager.fileHierarchy.levels.contentFile;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.basics.RepoFile;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.HtmlDir;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.MdpDir;
import org.mentalizr.contentManager.fileHierarchy.levels.info.InfoDir;
import org.mentalizr.contentManager.helper.DirectiveInFileChecker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ContentFile extends RepoFile {

    private final String id;
    private final String name;
    private final String displayName;
    private final boolean exercise;
    private final boolean feedback;

    public ContentFile(File file) throws ContentManagerException {
        super(file);
        this.name = Strings.cutEnd(super.getName(), getFiletype().length());
        this.id = obtainId();
        this.displayName = obtainDisplayName();
        this.exercise = checkIfExercise();
        this.feedback = checkIfFeedback();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public boolean isExercise() {
        return this.exercise;
    }

    public boolean isFeedback() {
        return this.feedback;
    }

    private String obtainId() {
        List<String> names = new ArrayList<>();
        Path path = this.file.toPath();
        int nameCount = path.getNameCount();

        if (path.getName(nameCount - 2).toString().equals(InfoDir.DIR_NAME)) {
            String programName = path.getName(nameCount - 4).toString();
            return programName + "__info_" + getName();
        }

        for (int i = nameCount - 1; i >= nameCount - 5; i--) {
            String idFraction = path.getName(i).toString();
            if (i == nameCount - 1) idFraction = eliminateFilePostfix(idFraction);
            if (isNotContentRootDir(idFraction)) names.add(idFraction);
        }

        Collections.reverse(names);

        return Strings.listing(names, "_");
    }

    private String obtainDisplayName() throws ContentManagerException {
        try {
            return DirectiveInFileChecker.obtainDisplayName(this.file.toPath());
        } catch (IOException e) {
            throw new ContentManagerException("IOException when accessing " + getFiletype() + " file: [" + this.file.getAbsolutePath() + "]", e);
        }
    }

    private boolean checkIfFeedback() throws ContentManagerException {
        return DirectiveInFileChecker.hasFeedbackDirective(this.file.toPath());
    }

    private boolean checkIfExercise() throws ContentManagerException {
        return DirectiveInFileChecker.hasExerciseDirective(this.file.toPath());
    }

    private String eliminateFilePostfix(String name) {
        if (name.endsWith(MdpFile.FILETYPE)) {
            name = Strings.cutEnd(name, 4);
        } else if (name.endsWith(HtmlFile.FILETYPE)) {
            name = Strings.cutEnd(name, 5);
        }
        return name;
    }

    private boolean isNotContentRootDir(String name) {
        return (!name.equals(MdpDir.DIR_NAME) && !name.equals(HtmlDir.DIR_NAME));
    }



}
