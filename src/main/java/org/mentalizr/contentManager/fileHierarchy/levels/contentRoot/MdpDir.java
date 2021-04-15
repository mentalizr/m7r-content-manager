package org.mentalizr.contentManager.fileHierarchy.levels.contentRoot;

import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.basics.RepoDirectory;
import org.mentalizr.contentManager.fileHierarchy.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.levels.info.MdpInfoDir;
import org.mentalizr.contentManager.fileHierarchy.levels.module.ModuleDirFileFilter;
import org.mentalizr.contentManager.fileHierarchy.levels.module.ModuleDirMdp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.mentalizr.contentManager.helper.PathAssertions.assertFileName;

public class MdpDir extends RepoDirectory implements ContentRootDir {

    public static final String DIR_NAME = "mdp";

    private final ProgramConfFile programConfFile;
    private final MdpInfoDir mdpInfoDir;
    private final List<ModuleDirMdp> moduleDirList;
    private final List<MdpFile> mdpFiles;

    public MdpDir(File file) throws ContentManagerException {
        super(file);
        assertFileName(file.toPath(), DIR_NAME);
        this.programConfFile = new ProgramConfFile(new File(asFile(), ProgramConfFile.FILE_NAME));
        this.mdpInfoDir = new MdpInfoDir(new File(asFile(), "_info"));
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
    public MdpInfoDir getInfoDir() {
        return this.mdpInfoDir;
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

    private List<ModuleDirMdp> obtainModuleDirs() throws ContentManagerException {

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
        contentFiles.addAll(this.mdpInfoDir.getContentFiles());
        return contentFiles;
    }

}
