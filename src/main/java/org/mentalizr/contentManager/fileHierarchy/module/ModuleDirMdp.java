package org.mentalizr.contentManager.fileHierarchy.module;

import org.mentalizr.contentManager.fileHierarchy.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.ContentTreeDirectory;
import org.mentalizr.contentManager.fileHierarchy.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.submodule.SubmoduleDirMdp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ModuleDirMdp extends ContentTreeDirectory implements ModuleDir {

    private final ModuleConfFile moduleConfFile;
    private final List<SubmoduleDirMdp> submoduleDirList;

    public ModuleDirMdp(File file) throws ProgramManagerException {
        super(file);
        this.moduleConfFile = new ModuleConfFile(new File(asFile(), ModuleConfFile.FILE_NAME));
        this.submoduleDirList = obtainSubmoduleDirs();
    }

    @Override
    public ModuleConfFile getModuleConfFile() {
        return moduleConfFile;
    }

    @Override
    public List<SubmoduleDirMdp> getSubmoduleDirs() {
        return submoduleDirList;
    }

    @Override
    public List<String> getSubmoduleDirNames() {
        List<String> dirNames = new ArrayList<>();
        for (SubmoduleDirMdp submoduleDirMdp : this.submoduleDirList) {
            dirNames.add(submoduleDirMdp.getName());
        }
        return dirNames;
    }

    @Override
    public boolean hasSubmoduleDir(String dirName) {
        for (SubmoduleDirMdp submoduleDirMdp : this.submoduleDirList) {
            if (submoduleDirMdp.getName().equals(dirName)) return true;
        }
        return false;
    }

    @Override
    public SubmoduleDirMdp getSubmoduleDir(String dirName) {
        for (SubmoduleDirMdp submoduleDirMdp : this.submoduleDirList) {
            if (submoduleDirMdp.getName().equals(dirName)) return submoduleDirMdp;
        }
        throw new RuntimeException("No submodule found with directory name: " + dirName);
    }

    @Override
    public List<MdpFile> getContentFiles() {
        List<MdpFile> contentFiles = new ArrayList<>();
        for (SubmoduleDirMdp submoduleDir : this.submoduleDirList) {
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

    private List<SubmoduleDirMdp> obtainSubmoduleDirs() throws ProgramManagerException {

        File[] fileArray = this.file.listFiles(new ModuleDirFileFilter());
        if (fileArray == null || fileArray.length == 0)
            throw new FileNotFoundException("No submodules found: [" + this.file.getAbsolutePath() + "]");

        List<SubmoduleDirMdp> submoduleDirMdpList = new ArrayList<>();
        for (File file : fileArray) {
            SubmoduleDirMdp submoduleDirMdp = new SubmoduleDirMdp(file);
            submoduleDirMdpList.add(submoduleDirMdp);
        }

        //noinspection ComparatorCombinators
        submoduleDirMdpList.sort((submoduleDir1, submoduleDir2) -> submoduleDir1.getName().compareTo(submoduleDir2.getName()));

        return submoduleDirMdpList;
    }

}
