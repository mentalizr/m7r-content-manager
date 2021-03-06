package org.mentalizr.contentManager.fileHierarchy.contentRoot;

import org.mentalizr.contentManager.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.ProgramConfFile;
import org.mentalizr.contentManager.fileHierarchy.RepoDirectory;
import org.mentalizr.contentManager.fileHierarchy.infotext.InfotextDirMd;
import org.mentalizr.contentManager.fileHierarchy.module.ModuleDirFileFilter;
import org.mentalizr.contentManager.fileHierarchy.module.ModuleDirMdp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MdpDir extends RepoDirectory implements ContentRoot {

    private final ProgramConfFile programConfFile;
    private final InfotextDirMd infotextDirMd;
    private final List<ModuleDirMdp> moduleDirList;

    public MdpDir(File file) throws ProgramManagerException {
        super(file);
        this.programConfFile = new ProgramConfFile(new File(getFile(), "program.conf"));
        this.infotextDirMd = new InfotextDirMd(new File(getFile(), "_info"));
        this.moduleDirList = obtainModuleDirs();
    }

    @Override
    public ProgramConfFile getProgramConfFile() {
        return programConfFile;
    }

    @Override
    public InfotextDirMd getInfotextDir() {
        return this.infotextDirMd;
    }

    @Override
    public List<ModuleDirMdp> getModuleDirs() {
        return moduleDirList;
    }

    @Override
    public List<String> getModuleDirNames() {
        List<String> dirNames = new ArrayList<>();
        for (ModuleDirMdp moduleDirMdp : this.moduleDirList) {
            dirNames.add(moduleDirMdp.getName());
        }
        return dirNames;
    }

    @Override
    public boolean hasModuleDir(String dirName) {
        for (ModuleDirMdp moduleDirMdp : this.moduleDirList) {
            if (moduleDirMdp.getName().equals(dirName)) return true;
        }
        return false;
    }

    @Override
    public ModuleDirMdp getModuleDir(String dirName) {
        for (ModuleDirMdp moduleDirMdp : this.moduleDirList) {
            if (moduleDirMdp.getName().equals(dirName)) return moduleDirMdp;
        }
        throw new RuntimeException("No Module in mdp directory with directory name: " + dirName);
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

    private List<ModuleDirMdp> obtainModuleDirs() throws ProgramManagerException {

        File[] fileArray = this.file.listFiles(new ModuleDirFileFilter());
        if (fileArray == null || fileArray.length == 0)
            throw new FileNotFoundException("No modules found: [" + this.file.getAbsolutePath() + "]");

        List<ModuleDirMdp> moduleDirMdpList = new ArrayList<>();
        for (File file : fileArray) {
            ModuleDirMdp moduleDirMdp = new ModuleDirMdp(file);
            moduleDirMdpList.add(moduleDirMdp);
        }

        //noinspection ComparatorCombinators
        moduleDirMdpList.sort((moduleDir1, moduleDir2) -> moduleDir1.getName().compareTo(moduleDir2.getName()));

        return moduleDirMdpList;
    }

}
