package org.mentalizr.contentManager.fileHierarchy.contentRoot;

import org.mentalizr.contentManager.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoDirectory;
import org.mentalizr.contentManager.fileHierarchy.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.infopage.InfopageDirMdp;
import org.mentalizr.contentManager.fileHierarchy.module.ModuleDirFileFilter;
import org.mentalizr.contentManager.fileHierarchy.module.ModuleDirMdp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.mentalizr.contentManager.helper.PathAssertions.assertFileName;

public class MdpDir extends RepoDirectory implements ContentRootDir {

    public static final String DIR_NAME = "mdp";

    private final ProgramConfFile programConfFile;
    private final InfopageDirMdp infoDirMdp;
    private final List<ModuleDirMdp> moduleDirList;
    private final List<MdpFile> mdpFiles;

    public MdpDir(File file) throws ProgramManagerException {
        super(file);
        assertFileName(file.toPath(), DIR_NAME);
        this.programConfFile = new ProgramConfFile(new File(asFile(), ProgramConfFile.FILE_NAME));
        this.infoDirMdp = new InfopageDirMdp(new File(asFile(), "_info"));
        this.moduleDirList = obtainModuleDirs();
        this.mdpFiles = obtainContentFiles();
    }

    @Override
    public List<MdpFile> getContentFiles() {
        return this.mdpFiles;
    }

    @Override
    public ProgramConfFile getProgramConfFile() {
        return programConfFile;
    }

    @Override
    public InfopageDirMdp getInfotextDir() {
        return this.infoDirMdp;
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

    private List<MdpFile> obtainContentFiles() {
        List<MdpFile> contentFiles = new ArrayList<>();
        for (ModuleDirMdp moduleDir : this.moduleDirList) {
            contentFiles.addAll(moduleDir.getContentFiles());
        }
        return contentFiles;
    }

}
