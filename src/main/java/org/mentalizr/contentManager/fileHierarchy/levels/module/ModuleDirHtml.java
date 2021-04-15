package org.mentalizr.contentManager.fileHierarchy.levels.module;

import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.basics.ContentTreeDirectory;
import org.mentalizr.contentManager.fileHierarchy.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.levels.submodule.SubmoduleDirHtml;
import org.mentalizr.serviceObjects.frontend.program.Module;
import org.mentalizr.serviceObjects.frontend.program.Submodule;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ModuleDirHtml extends ContentTreeDirectory implements ModuleDir {

    private final ModuleConfFile moduleConfFile;
    private final List<SubmoduleDirHtml> submoduleDirList;
    private final Module module;

    public ModuleDirHtml(File file) throws ContentManagerException {
        super(file);
        this.moduleConfFile = new ModuleConfFile(new File(asFile(), ModuleConfFile.FILE_NAME));
        this.submoduleDirList = obtainSubmoduleDirs();
        this.module = prepareModule();
    }

    @Override
    public ModuleConfFile getModuleConfFile() {
        return moduleConfFile;
    }

    @Override
    public List<SubmoduleDirHtml> getSubmoduleDirs() {
        return submoduleDirList;
    }

    @Override
    public List<String> getSubmoduleDirNames() {
        List<String> dirNames = new ArrayList<>();
        for (SubmoduleDirHtml submoduleDir : this.submoduleDirList) {
            dirNames.add(submoduleDir.getName());
        }
        return dirNames;
    }

    @Override
    public boolean hasSubmoduleDir(String dirName) {
        for (SubmoduleDirHtml submoduleDir : this.submoduleDirList) {
            if (submoduleDir.getName().equals(dirName)) return true;
        }
        return false;
    }

    @Override
    public SubmoduleDirHtml getSubmoduleDir(String dirName) {
        for (SubmoduleDirHtml submoduleDir : this.submoduleDirList) {
            if (submoduleDir.getName().equals(dirName)) return submoduleDir;
        }
        throw new RuntimeException("No submodule found with directory name: " + dirName);
    }

    @Override
    public List<HtmlFile> getContentFiles() {
        List<HtmlFile> contentFiles = new ArrayList<>();
        for (SubmoduleDirHtml submoduleDir : this.submoduleDirList) {
            contentFiles.addAll(submoduleDir.getContentFiles());
        }
        return contentFiles;
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
        return this.moduleConfFile.getModuleConf().getName();
    }

    public Module asModule() {
        return this.module;
    }

    private List<SubmoduleDirHtml> obtainSubmoduleDirs() throws ContentManagerException {

        File[] fileArray = this.file.listFiles(new ModuleDirFileFilter());
        if (fileArray == null || fileArray.length == 0)
            throw new FileNotFoundException("No submodules found: [" + this.file.getAbsolutePath() + "]");

        List<SubmoduleDirHtml> submoduleDirList = new ArrayList<>();
        for (File file : fileArray) {
            SubmoduleDirHtml submoduleDirMdp = new SubmoduleDirHtml(file);
            submoduleDirList.add(submoduleDirMdp);
        }

        //noinspection ComparatorCombinators
        submoduleDirList.sort((submoduleDir1, submoduleDir2) -> submoduleDir1.getName().compareTo(submoduleDir2.getName()));

        return submoduleDirList;
    }

    private Module prepareModule() {
        List<Submodule> submodules = new ArrayList<>();
        for (SubmoduleDirHtml submoduleDirHtml : this.submoduleDirList) {
            submodules.add(submoduleDirHtml.asSubmodule());
        }

        return new Module(
                this.getId(),
                getDisplayName(),
                submodules
        );
    }

}
