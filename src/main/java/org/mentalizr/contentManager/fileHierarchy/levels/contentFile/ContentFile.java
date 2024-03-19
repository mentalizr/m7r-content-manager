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

import static org.mentalizr.contentManager.fileHierarchy.levels.contentFile.ContentFile.ContentFileType.INFO;
import static org.mentalizr.contentManager.fileHierarchy.levels.contentFile.ContentFile.ContentFileType.STEP;

public abstract class ContentFile extends RepoFile {

    public enum ContentFileType { STEP, INFO }

    protected final ContentFileType contentFileType;
    protected final String id;
    private final String name;
    private final String displayName;
    private final boolean exercise;
    private final boolean feedback;

    public ContentFile(File file) throws ContentManagerException {
        super(file);
        this.contentFileType = obtainContentFileType(file.toPath());
        this.name = Strings.cutEnd(super.getName(), getFiletype().length());
        this.id = obtainId(file.toPath(), this.contentFileType);
        this.displayName = obtainDisplayName();
        this.exercise = checkIfExercise();
        this.feedback = checkIfFeedback();
    }

    public ContentFileType getContentFileType() {
        return this.contentFileType;
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

    private String obtainId(Path path, ContentFileType contentFileType) {
        List<String> names = new ArrayList<>();
        int nameCount = path.getNameCount();

        if (contentFileType == INFO) {
            String programName = path.getName(nameCount - 4).toString();
            return programName + "__info_" + getName();
        } else if (contentFileType == STEP) {
            for (int i = nameCount - 1; i >= nameCount - 5; i--) {
                String idFraction = path.getName(i).toString();
                if (i == nameCount - 1) idFraction = eliminateFilePostfix(idFraction);
                if (isNotContentRootDir(idFraction)) names.add(idFraction);
            }
            Collections.reverse(names);
            return Strings.listing(names, "_");
        }
        throw new IllegalStateException(
                "No known " + ContentFileType.class.getSimpleName() + ": " + contentFileType.name());
    }

    private ContentFileType obtainContentFileType(Path path) {
        return isInfoFile(path) ? INFO : STEP;
    }

    private boolean isInfoFile(Path path) {
        return path.getName(path.getNameCount() - 2).toString().equals(InfoDir.DIR_NAME);
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
