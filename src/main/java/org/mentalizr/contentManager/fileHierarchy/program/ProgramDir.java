package org.mentalizr.contentManager.fileHierarchy.program;

import org.mentalizr.contentManager.FileSystemHelper;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoDirectory;
import org.mentalizr.contentManager.fileHierarchy.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.contentRoot.HtmlDir;
import org.mentalizr.contentManager.fileHierarchy.contentRoot.MdpDir;
import org.mentalizr.contentManager.fileHierarchy.media.MediaDir;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ProgramDir extends RepoDirectory {

    private final MdpDir mdpDir;
    private HtmlDir htmlDir;
    private final MediaDir mediaDir;

    public ProgramDir(File file) throws ProgramManagerException {
        super(file);
        this.mdpDir = new MdpDir(new File(getFile(), "mdp"));
        this.htmlDir = getOptionalHtmlDirAsNullable();
        this.mediaDir = new MediaDir(new File(getFile(), "media"));
    }

    public List<HtmlFile> getHtmlFiles() {
        if (!hasHtmlDir()) throw new IllegalStateException("No html dir existing. Check before calling.");
        return this.htmlDir.getContentFiles();
    }

    public List<MdpFile> getMdpFiles() {
        return this.mdpDir.getContentFiles();
    }

    private HtmlDir getOptionalHtmlDirAsNullable() throws ProgramManagerException {
        HtmlDir htmlDir = null;
        File htmlDirFile = new File(getFile(), "html");
        if (htmlDirFile.exists()) {
            htmlDir = new HtmlDir(new File(getFile(), "html"));
        }
        return htmlDir;
    }

    public MdpDir getMdpDir() {
        return this.mdpDir;
    }

    public Optional<HtmlDir> getHtmlDir() {
        return Optional.ofNullable(this.htmlDir);
    }

    public MediaDir getMediaDir() {
        return mediaDir;
    }

    private boolean hasHtmlDir() {
        return this.htmlDir != null;
    }

    public void clean() throws ProgramManagerException {
        if (hasHtmlDir()) {
            try {
                if (FileSystemHelper.getDepth(this.htmlDir.getFile().toPath()) > 3)
                    throw new IllegalStateException("Emergency breaking on cleaning m7r content repo." +
                            " Subdirectory html can not be deeper than 3 levels." +
                            " [" + this.htmlDir.getFile().getAbsolutePath() + "]");
                FileSystemHelper.deleteDirectoryRecursively(this.htmlDir.getFile().toPath());
                this.htmlDir = null;
            } catch (IOException e) {
                throw new ProgramManagerException("Exception on cleaning html file." +
                        " [" + this.htmlDir.getFile().getAbsolutePath() + "]", e);
            }
        }
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
