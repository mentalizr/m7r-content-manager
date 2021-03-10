package org.mentalizr.contentManager.fileHierarchy.submodule;

import org.mentalizr.contentManager.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoDirectory;
import org.mentalizr.contentManager.fileHierarchy.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.contentFile.HtmlFileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SubmoduleDirHtml extends RepoDirectory implements SubmoduleDir {

    private final SubmoduleConfFile submoduleConfFile;
    private final List<HtmlFile> contentFileList;

    public SubmoduleDirHtml(File file) throws ProgramManagerException {
        super(file);
        this.submoduleConfFile = new SubmoduleConfFile(new File(getFile(), "submodule.conf"));
        this.contentFileList = obtainHtmlFiles();
    }

    @Override
    public SubmoduleConfFile getSubmoduleConfFile() {
        return submoduleConfFile;
    }

    @Override
    public List<HtmlFile> getContentFiles() {
        return this.contentFileList;
    }

    @Override
    public List<String> getStepFileNames() {
        List<String> stepFileNames = new ArrayList<>();
        for (HtmlFile htmlFile : this.contentFileList) {
            stepFileNames.add(htmlFile.getName());
        }
        return stepFileNames;
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

    private List<HtmlFile> obtainHtmlFiles() throws ProgramManagerException {
        File[] fileArray = this.file.listFiles(new HtmlFileFilter());
        if (fileArray == null || fileArray.length == 0)
            throw new FileNotFoundException("No .html files found in submodule: [" + this.file.getAbsolutePath() + "]");

        List<HtmlFile> htmlFileList = new ArrayList<>();
        for (File file : fileArray) {
            HtmlFile htmlFile = new HtmlFile(file);
            htmlFileList.add(htmlFile);
        }

        //noinspection ComparatorCombinators
        htmlFileList.sort((htmlFile1, htmlFile) -> htmlFile1.getName().compareTo(htmlFile.getName()));

        return htmlFileList;
    }

}
