package org.mentalizr.contentManager.fileHierarchy.module;

import org.mentalizr.contentManager.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoDirectory;
import org.mentalizr.contentManager.fileHierarchy.submodule.SubmoduleDirHtml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ModuleDirHtml extends RepoDirectory implements ModuleDir {

    private final ModuleConfFile moduleConfFile;
    private final List<SubmoduleDirHtml> submoduleDirList;

    public ModuleDirHtml(File file) throws ProgramManagerException {
        super(file);
        this.moduleConfFile = new ModuleConfFile(new File(getFile(), "module.conf"));
        this.submoduleDirList = obtainSubmoduleDirs();
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

    private List<SubmoduleDirHtml> obtainSubmoduleDirs() throws ProgramManagerException {

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

}
