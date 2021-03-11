package org.mentalizr.contentManager.fileHierarchy.info;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoDirectory;
import org.mentalizr.contentManager.fileHierarchy.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.contentFile.MdpFileFilter;
import org.mentalizr.serviceObjects.frontend.program.Infotext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InfoDirHtml extends RepoDirectory implements InfoDir {

    private final List<HtmlFile> infotextFiles;
    private final List<Infotext> infotextList;

    public InfoDirHtml(File file) throws ProgramManagerException {
        super(file);
        this.infotextFiles = obtainInfotextFiles();
        this.infotextList = prepareInfotextList();
    }

    @Override
    public List<HtmlFile> getInfoFiles() {
        return this.infotextFiles;
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

    private List<HtmlFile> obtainInfotextFiles() throws ProgramManagerException {
        File[] fileArray = this.file.listFiles(new MdpFileFilter());
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
        for (HtmlFile htmlFile : this.infotextFiles) {
            Infotext infotext = new Infotext(htmlFile.getId(), htmlFile.getDisplayName());
            infotextList.add(infotext);
        }
        return infotextList;
    }

}
