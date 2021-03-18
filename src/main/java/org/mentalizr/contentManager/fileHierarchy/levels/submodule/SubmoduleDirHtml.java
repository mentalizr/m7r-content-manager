package org.mentalizr.contentManager.fileHierarchy.levels.submodule;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.basics.ContentTreeDirectory;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFileFilter;
import org.mentalizr.serviceObjects.frontend.program.Step;
import org.mentalizr.serviceObjects.frontend.program.Submodule;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SubmoduleDirHtml extends ContentTreeDirectory implements SubmoduleDir {

    private final SubmoduleConfFile submoduleConfFile;
    private final List<HtmlFile> contentFileList;
    private final Submodule submodule;

    public SubmoduleDirHtml(File file) throws ProgramManagerException {
        super(file);
        this.submoduleConfFile = new SubmoduleConfFile(new File(asFile(), SubmoduleConfFile.FILE_NAME));
        this.contentFileList = obtainHtmlFiles();
        this.submodule = prepareSubmodule();
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

    public String getDisplayName() {
        return this.submoduleConfFile.getSubmoduleConf().getName();
    }

    public Submodule asSubmodule() {
        return this.submodule;
    }

    private List<HtmlFile> obtainHtmlFiles() throws ProgramManagerException {
        List<HtmlFile> htmlFileList = new ArrayList<>();
        File[] fileArray = this.file.listFiles(new HtmlFileFilter());
        if (fileArray == null || fileArray.length == 0)
            return htmlFileList;
//            throw new FileNotFoundException("No .html files found in submodule: [" + this.file.getAbsolutePath() + "]");

        for (File file : fileArray) {
            HtmlFile htmlFile = new HtmlFile(file);
            htmlFileList.add(htmlFile);
        }

        //noinspection ComparatorCombinators
        htmlFileList.sort((htmlFile1, htmlFile) -> htmlFile1.getName().compareTo(htmlFile.getName()));

        return htmlFileList;
    }

    private Submodule prepareSubmodule() {
        List<Step> steps = new ArrayList<>();
        for (HtmlFile htmlFile : this.contentFileList) {
            steps.add(htmlFile.asStep());
        }

        return new Submodule(
                getId(),
                getDisplayName(),
                steps
        );
    }

}
