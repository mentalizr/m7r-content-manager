package org.mentalizr.contentManager.fileHierarchy;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.contentManager.exceptions.*;
import org.mentalizr.contentManager.fileHierarchy.contentRoot.HtmlDir;
import org.mentalizr.contentManager.fileHierarchy.contentRoot.MdpDir;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ContentTreeDirectory extends RepoDirectory {

    public ContentTreeDirectory(File file) throws ProgramManagerException {
        super(file);
    }

    public String getId() {
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
