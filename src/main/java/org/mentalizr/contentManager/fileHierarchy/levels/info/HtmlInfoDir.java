package org.mentalizr.contentManager.fileHierarchy.levels.info;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.basics.RepoDirectory;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFileFilter;
import org.mentalizr.serviceObjects.frontend.program.Infotext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.mentalizr.contentManager.helper.PathAssertions.assertFileName;

public class HtmlInfoDir extends RepoDirectory implements InfoDir {

    private final List<HtmlFile> infopageFiles;
    private final List<Infotext> infotextList;

    public HtmlInfoDir(File file) throws ProgramManagerException {
        super(file);
        assertFileName(file.toPath(), InfoDir.DIR_NAME);
        this.infopageFiles = obtainInfopageFiles();
        this.infotextList = prepareInfotextList();
    }

    @Override
    public List<HtmlFile> getContentFiles() {
        return this.infopageFiles;
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

    public List<Infotext> asInfotextList() {
        return this.infotextList;
    }

    private List<HtmlFile> obtainInfopageFiles() throws ProgramManagerException {
        File[] fileArray = this.file.listFiles(new HtmlFileFilter());
        if (fileArray == null || fileArray.length == 0)
            return new ArrayList<>();

        List<HtmlFile> htmlFileList = new ArrayList<>();
        for (File file : fileArray) {
            HtmlFile htmlFile = new HtmlFile(file);
            htmlFileList.add(htmlFile);
        }

        //noinspection ComparatorCombinators
        htmlFileList.sort((mdpFile1, mdpFile2) -> mdpFile1.getName().compareTo(mdpFile2.getName()));

        return htmlFileList;
    }

    private List<Infotext> prepareInfotextList() {
        List<Infotext> infotextList = new ArrayList<>();
        for (HtmlFile htmlFile : this.infopageFiles) {
            Infotext infotext = new Infotext(htmlFile.getId(), htmlFile.getDisplayName());
            infotextList.add(infotext);
        }
        return infotextList;
    }

}
