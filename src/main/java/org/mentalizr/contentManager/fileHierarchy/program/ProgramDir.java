package org.mentalizr.contentManager.fileHierarchy.program;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.contentRoot.HtmlDir;
import org.mentalizr.contentManager.fileHierarchy.media.MediaDir;
import org.mentalizr.contentManager.fileHierarchy.RepoDirectory;
import org.mentalizr.contentManager.fileHierarchy.contentRoot.MdpDir;

import java.io.File;

public class ProgramDir extends RepoDirectory {

    private final MdpDir mdpDir;
    private final HtmlDir htmlDir;
    private final MediaDir mediaDir;

    public ProgramDir(File file) throws ProgramManagerException {
        super(file);
        this.mdpDir = new MdpDir(new File(getFile(), "mdp"));
        this.htmlDir = new HtmlDir(new File(getFile(), "html"));
        this.mediaDir = new MediaDir(new File(getFile(), "media"));
    }

    public MdpDir getMdpDir() {
        return this.mdpDir;
    }

    public HtmlDir getHtmlDir() {
        return htmlDir;
    }

    public MediaDir getMediaDir() {
        return mediaDir;
    }

    public boolean hasHtmlDir() {
        return new File(getFile(), "html").exists();
    }

    @Override
    public boolean requiresExistence() {
        return true;
    }

    @Override
    public boolean requiresReadPermission() {
        return true;
    }

    @Override
    public boolean requiresWritePermission() {
        return false;
    }

}
