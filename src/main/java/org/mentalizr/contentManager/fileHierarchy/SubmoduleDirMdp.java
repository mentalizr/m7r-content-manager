package org.mentalizr.contentManager.fileHierarchy;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SubmoduleDirMdp extends FhDirectory {

    private final SubmoduleConfFile submoduleConfFile;
    private final List<MdpFile> stepFileList;

    public SubmoduleDirMdp(File file) throws ProgramManagerException {
        super(file);
        this.submoduleConfFile = new SubmoduleConfFile(new File(getFile(), "submodule.conf"));
        this.stepFileList = obtainMdpFiles();
    }

    public SubmoduleConfFile getSubmoduleConfFile() {
        return submoduleConfFile;
    }

    public List<MdpFile> getStepFiles() {
        return this.stepFileList;
    }

    public List<String> getStepFileNames() {
        List<String> stepFileNames = new ArrayList<>();
        for (MdpFile stepFile : this.stepFileList) {
            stepFileNames.add(stepFile.getFileName());
        }
        return stepFileNames;
    }

    public String getDirName() {
        return this.file.getName();
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
        File[] fileArray = this.file.listFiles(new MdpFilenameFilter());
        if (fileArray == null || fileArray.length == 0)
            throw new ProgramManagerException("No .mdp files found in submodule: [" + this.file.getAbsolutePath() + "]");

        List<MdpFile> mdpFileList = new ArrayList<>();
        for (File file : fileArray) {
            MdpFile mdpFile = new MdpFile(file);
            mdpFileList.add(mdpFile);
        }

        mdpFileList.sort(new MdpFileComparator());

        return mdpFileList;
    }

}
