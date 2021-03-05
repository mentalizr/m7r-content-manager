package org.mentalizr.contentManager.fileHierarchy;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ModuleDirMdp extends FhDirectory {

    private final ModuleConfFile moduleConfFile;
    private final List<SubmoduleDirMdp> submoduleDirList;

    public ModuleDirMdp(File file) throws ProgramManagerException {
        super(file);
        this.moduleConfFile = new ModuleConfFile(new File(getFile(), "module.conf"));
        this.submoduleDirList = obtainSubmoduleDirs();
    }

    public ModuleConfFile getModuleConfFile() {
        return moduleConfFile;
    }

    public List<SubmoduleDirMdp> getSubmoduleDirs() {
        return submoduleDirList;
    }

    public List<String> getSubmoduleDirNames() {
        List<String> dirNames = new ArrayList<>();
        for (SubmoduleDirMdp submoduleDirMdp : this.submoduleDirList) {
            dirNames.add(submoduleDirMdp.getDirName());
        }
        return dirNames;
    }

    public boolean hasSubmoduleDir(String dirName) {
        for (SubmoduleDirMdp submoduleDirMdp : this.submoduleDirList) {
            if (submoduleDirMdp.getDirName().equals(dirName)) return true;
        }
        return false;
    }

    public SubmoduleDirMdp getSubmoduleDir(String dirName) {
        for (SubmoduleDirMdp submoduleDirMdp : this.submoduleDirList) {
            if (submoduleDirMdp.getDirName().equals(dirName)) return submoduleDirMdp;
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

    private List<SubmoduleDirMdp> obtainSubmoduleDirs() throws ProgramManagerException {

        File[] fileArray = this.file.listFiles(new ModuleDirFileFilter());
        if (fileArray == null || fileArray.length == 0)
            throw new ProgramManagerException("No submodules found: [" + this.file.getAbsolutePath() + "]");

        List<SubmoduleDirMdp> submoduleDirMdpList = new ArrayList<>();
        for (File file : fileArray) {
            SubmoduleDirMdp submoduleDirMdp = new SubmoduleDirMdp(file);
            submoduleDirMdpList.add(submoduleDirMdp);
        }

        submoduleDirMdpList.sort(new SubmoduleDirMdpComparator());

        return submoduleDirMdpList;
    }

}
