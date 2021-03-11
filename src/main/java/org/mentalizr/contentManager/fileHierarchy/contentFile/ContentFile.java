package org.mentalizr.contentManager.fileHierarchy.contentFile;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoFile;
import org.mentalizr.contentManager.fileHierarchy.contentRoot.HtmlDir;
import org.mentalizr.contentManager.fileHierarchy.contentRoot.MdpDir;
import org.mentalizr.contentManager.fileHierarchy.info.InfoDir;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ContentFile extends RepoFile {

    public ContentFile(File file) throws ProgramManagerException {
        super(file);
    }

    public String getId() {
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
