package org.mentalizr.contentManager.fileHierarchy.program;

import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoDirectory;
import org.mentalizr.contentManager.fileHierarchy.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.contentRoot.HtmlDir;
import org.mentalizr.contentManager.fileHierarchy.contentRoot.MdpDir;
import org.mentalizr.contentManager.fileHierarchy.media.MediaDir;
import org.mentalizr.serviceObjects.frontend.program.Program;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class ProgramDir extends RepoDirectory {

    private final MdpDir mdpDir;
    private HtmlDir htmlDir;
    private final MediaDir mediaDir;

    public ProgramDir(File file) throws ProgramManagerException {
        super(file);
        this.mdpDir = new MdpDir(new File(asFile(), MdpDir.DIR_NAME));
        this.htmlDir = getOptionalHtmlDirAsNullable();
        this.mediaDir = new MediaDir(new File(asFile(), MediaDir.DIR_NAME));
    }

    public List<HtmlFile> getHtmlFiles() {
        assertHtmlDir();
        return this.htmlDir.getContentFiles();
    }

    public List<MdpFile> getMdpFiles() {
        return this.mdpDir.getContentFiles();
    }

    public Program asProgram() {
        assertHtmlDir();
        return this.htmlDir.asProgram();
    }

    private HtmlDir getOptionalHtmlDirAsNullable() throws ProgramManagerException {
        HtmlDir htmlDir = null;
        File htmlDirFile = new File(asFile(), HtmlDir.DIR_NAME);
        if (htmlDirFile.exists()) {
            htmlDir = new HtmlDir(new File(asFile(), HtmlDir.DIR_NAME));
        }
        return htmlDir;
    }

    public MdpDir getMdpDir() {
        return this.mdpDir;
    }

    public boolean hasHtmlDir() {
        return this.htmlDir != null;
    }

    public HtmlDir getHtmlDir() {
        if (this.htmlDir == null) throw new IllegalStateException("No html existing. Check before calling getHtmlDir().");
        return this.htmlDir;
    }

    public MediaDir getMediaDir() {
        return mediaDir;
    }

    public void clean() throws ProgramManagerException {
        if (hasHtmlDir()) {
            try {
                FileUtils.rmDir(this.htmlDir.asPath());
                this.htmlDir = null;
            } catch (IOException e) {
                throw new ProgramManagerException("Exception on cleaning html file." +
                        " [" + this.htmlDir.asFile().getAbsolutePath() + "]", e);
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

    private void assertHtmlDir() {
        if (!hasHtmlDir()) throw new IllegalStateException("No html dir existing. Check before calling.");
    }

}
