package org.mentalizr.contentManager.fileHierarchy.exceptions;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;

import java.util.Set;

public class MediaNotFoundException extends Exception {

    private final MdpFile mdpFile;
    private final Set<String> mediaFiles;

    public MediaNotFoundException(MdpFile mdpFile, Set<String> mediaFiles) {
        super("Referenced media files are not available: "
                + Strings.listing(mediaFiles, ", ", "", "", "[", "]") + ".");
        this.mdpFile = mdpFile;
        this.mediaFiles = mediaFiles;
    }

    public MdpFile getMdpFile() {
        return mdpFile;
    }

    public Set<String> getMediaFiles() {
        return this.mediaFiles;
    }

}
