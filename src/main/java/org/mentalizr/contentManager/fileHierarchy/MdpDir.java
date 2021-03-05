package org.mentalizr.contentManager.fileHierarchy;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MdpDir extends FhDirectory {

    private final ProgramConfFile programConfFile;
    private final InfotextDirMd infotextDirMd;
    private final List<ModuleDirMdp> moduleDirList;

    public MdpDir(File file) throws ProgramManagerException {
        super(file);
        this.programConfFile = new ProgramConfFile(new File(getFile(), "program.conf"));
        this.infotextDirMd = new InfotextDirMd(new File(getFile(), "_info"));
        this.moduleDirList = obtainModuleDirs();
    }

    public ProgramConfFile getProgramConfFile() {
        return programConfFile;
    }

    public InfotextDirMd getInfotextDir() {
        return this.infotextDirMd;
    }

    public List<ModuleDirMdp> getModuleDirs() {
        return moduleDirList;
    }

    public List<String> getModuleDirNames() {
        List<String> dirNames = new ArrayList<>();
        for (ModuleDirMdp moduleDirMdp : this.moduleDirList) {
            dirNames.add(moduleDirMdp.getDirName());
        }
        return dirNames;
    }

    public boolean hasModuleDir(String dirName) {
        for (ModuleDirMdp moduleDirMdp : this.moduleDirList) {
            if (moduleDirMdp.getDirName().equals(dirName)) return true;
        }
        return false;
    }

    public ModuleDirMdp getModuleDir(String dirName) {
        for (ModuleDirMdp moduleDirMdp : this.moduleDirList) {
            if (moduleDirMdp.getDirName().equals(dirName)) return moduleDirMdp;
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
            throw new ProgramManagerException("No modules found: [" + this.file.getAbsolutePath() + "]");

        List<ModuleDirMdp> moduleDirMdpList = new ArrayList<>();
        for (File file : fileArray) {
            ModuleDirMdp moduleDirMdp = new ModuleDirMdp(file);
            moduleDirMdpList.add(moduleDirMdp);
        }

        moduleDirMdpList.sort(new ModuleDirMdpComparator());

        return moduleDirMdpList;
    }

}
