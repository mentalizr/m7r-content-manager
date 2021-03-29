package org.mentalizr.contentManager.fileHierarchy.levels.program;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.basics.RepoDirectory;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.HtmlDir;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.MdpDir;
import org.mentalizr.contentManager.fileHierarchy.levels.media.MediaDir;
import org.mentalizr.serviceObjects.frontend.program.Program;

import java.io.File;
import java.util.List;

public class ProgramDir extends RepoDirectory {

    private final MdpDir mdpDir;
    private final HtmlDir htmlDir;
    private final MediaDir mediaDir;

    public ProgramDir(File file) throws ProgramManagerException {
        super(file);
        this.mdpDir = new MdpDir(new File(asFile(), MdpDir.DIR_NAME));
        this.htmlDir = obtainHtmlDirNullable();
        this.mediaDir = new MediaDir(new File(asFile(), MediaDir.DIR_NAME));
    }

    private ProgramDir(ProgramDir programDir) throws ProgramManagerException {
        super(programDir.file);
        this.mdpDir = programDir.mdpDir;
        this.htmlDir = obtainHtmlDirNullable();
        this.mediaDir = programDir.mediaDir;
    }

    public static ProgramDir reinitializeHtmlDir(ProgramDir programDir) throws ProgramManagerException {
        return new ProgramDir(programDir);
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

    private HtmlDir obtainHtmlDirNullable() throws ProgramManagerException {
        HtmlDir htmlDir = null;
        File htmlDirFile = new File(asFile(), HtmlDir.DIR_NAME);
        if (htmlDirFile.exists()) {
            htmlDir = new HtmlDir(new File(asFile(), HtmlDir.DIR_NAME));
        }
        return htmlDir;
    }

}
