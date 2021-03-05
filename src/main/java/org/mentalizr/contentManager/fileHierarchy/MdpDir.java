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

    public List<ModuleDirMdp> getModuleDirList() {
        return moduleDirList;
    }

    public List<String> getModuleIdFractionList() {
        List<String> idFractionList = new ArrayList<>();
        for (ModuleDirMdp moduleDirMdp : this.moduleDirList) {
            idFractionList.add(moduleDirMdp.getIdFraction());
        }
        return idFractionList;
    }

    public boolean hasModuleDirMdp(String idFraction) {
        for (ModuleDirMdp moduleDirMdp : this.moduleDirList) {
            return true;
        }
        return false;
    }

    public ModuleDirMdp getModuleDirMdp(String idFraction) {
        for (ModuleDirMdp moduleDirMdp : this.moduleDirList) {
            if (moduleDirMdp.getIdFraction().equals(idFraction)) return moduleDirMdp;
        }
        throw new RuntimeException("No Module in mdp directory with id fraction: " + idFraction);
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
        return moduleDirMdpList;
    }

}
