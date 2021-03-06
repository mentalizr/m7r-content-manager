package org.mentalizr.contentManager.fileHierarchy;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class ContentDir<I,M extends RepoDirectory> extends RepoDirectory {

    private final ProgramConfFile programConfFile;
    private final I infotextDirMd;
    private final List<M> moduleDirList;

    public ContentDir(File file) throws ProgramManagerException {
        super(file);
        this.programConfFile = new ProgramConfFile(new File(getFile(), "program.conf"));
        this.infotextDirMd = obtainInfotextDir();
        this.moduleDirList = obtainModuleDirs();
    }

    protected abstract I obtainInfotextDir();

    protected abstract List<M> obtainModuleDirs() throws ProgramManagerException;

    public ProgramConfFile getProgramConfFile() {
        return programConfFile;
    }

    public I getInfotextDir() {
        return this.infotextDirMd;
    }

    public List<M> getModuleDirs() {
        return moduleDirList;
    }

    public List<String> getModuleDirNames() {
        List<String> dirNames = new ArrayList<>();
        for (M moduleDirMdp : this.moduleDirList) {
            dirNames.add(moduleDirMdp.getName());
        }
        return dirNames;
    }

    public boolean hasModuleDir(String dirName) {
        for (M moduleDirMdp : this.moduleDirList) {
            if (moduleDirMdp.getName().equals(dirName)) return true;
        }
        return false;
    }

    public M getModuleDir(String dirName) {
        for (M moduleDirMdp : this.moduleDirList) {
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

//    private List<M> obtainModuleDirs() throws ProgramManagerException {
//
//        File[] fileArray = this.file.listFiles(new ModuleDirFileFilter());
//        if (fileArray == null || fileArray.length == 0)
//            throw new FileNotFoundException("No modules found: [" + this.file.getAbsolutePath() + "]");
//
//        List<ModuleDirMdp> moduleDirMdpList = new ArrayList<>();
//        for (File file : fileArray) {
//            ModuleDirMdp moduleDirMdp = new ModuleDirMdp(file);
//            moduleDirMdpList.add(moduleDirMdp);
//        }
//
//        moduleDirMdpList.sort(new ModuleDirMdpComparator());
//
//        return moduleDirMdpList;
//    }

}
