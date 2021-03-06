package org.mentalizr.contentManager.fileHierarchy.submodule;

import org.mentalizr.contentManager.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.RepoDirectory;
import org.mentalizr.contentManager.fileHierarchy.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.contentFile.MdpFileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SubmoduleDirMdp extends RepoDirectory implements SubmoduleDir {

    private final SubmoduleConfFile submoduleConfFile;
    private final List<MdpFile> stepFileList;

    public SubmoduleDirMdp(File file) throws ProgramManagerException {
        super(file);
        this.submoduleConfFile = new SubmoduleConfFile(new File(getFile(), "submodule.conf"));
        this.stepFileList = obtainMdpFiles();
    }

    @Override
    public SubmoduleConfFile getSubmoduleConfFile() {
        return submoduleConfFile;
    }

    @Override
    public List<MdpFile> getStepFiles() {
        return this.stepFileList;
    }

    @Override
    public List<String> getStepFileNames() {
        List<String> stepFileNames = new ArrayList<>();
        for (MdpFile stepFile : this.stepFileList) {
            stepFileNames.add(stepFile.getName());
        }
        return stepFileNames;
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

    private List<MdpFile> obtainMdpFiles() throws ProgramManagerException {
        File[] fileArray = this.file.listFiles(new MdpFileFilter());
        if (fileArray == null || fileArray.length == 0)
            throw new FileNotFoundException("No .mdp files found in submodule: [" + this.file.getAbsolutePath() + "]");

        List<MdpFile> mdpFileList = new ArrayList<>();
        for (File file : fileArray) {
            MdpFile mdpFile = new MdpFile(file);
            mdpFileList.add(mdpFile);
        }

        //noinspection ComparatorCombinators
        mdpFileList.sort((mdpFile1, mdpFile2) -> mdpFile1.getName().compareTo(mdpFile2.getName()));

        return mdpFileList;
    }

}
