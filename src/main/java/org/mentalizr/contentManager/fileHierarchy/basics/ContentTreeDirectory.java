package org.mentalizr.contentManager.fileHierarchy.basics;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.HtmlDir;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.MdpDir;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ContentTreeDirectory extends RepoDirectory {

    protected final String id;

    public ContentTreeDirectory(File file) throws ContentManagerException {
        super(file);
        this.id = prepareId();
    }

    public String getId() {
        return this.id;
    }

    private String prepareId() {
        List<String> names = new ArrayList<>();
        Path path = this.file.toPath();
        int nameCount = path.getNameCount();

        boolean contentRootFound = false;
        for (int i = nameCount - 1; i >= 0; i--) {
            String name = path.getName(i).toString();
            if (contentRootFound) {
                names.add(name);
                break;
            }
            if (!name.equals(MdpDir.DIR_NAME) && !name.equals(HtmlDir.DIR_NAME)) {
                names.add(name);
            } else {
                contentRootFound = true;
            }
        }
        if (!contentRootFound) throw new RuntimeException("No content tree directory.");

        Collections.reverse(names);

        return Strings.listing(names, "_");
    }

}
