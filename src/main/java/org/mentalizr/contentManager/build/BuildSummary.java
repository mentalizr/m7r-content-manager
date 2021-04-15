package org.mentalizr.contentManager.build;

import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;

import java.util.ArrayList;
import java.util.List;

public class BuildSummary {

    private final List<MdpFile> successfulMpdFiles;
    private final List<BuildFail> failedMdpFiles;

    public BuildSummary() {
        this.successfulMpdFiles = new ArrayList<>();
        this.failedMdpFiles = new ArrayList<>();
    }

    public void addSuccessfulMdpFile(MdpFile mdpFile) {
        this.successfulMpdFiles.add(mdpFile);
    }

    public void addFailedMdpFiles(MdpFile mdpFile, Exception e) {
        this.failedMdpFiles.add(new BuildFail(mdpFile, e));
    }

    public int getNrOfSuccessfulMdpFiles() {
        return this.successfulMpdFiles.size();
    }

    public int getNrOfFailedMdpFiles() {
        return this.failedMdpFiles.size();
    }

    public int getTotalNrOfMdpFiles() {
        return getNrOfSuccessfulMdpFiles() + getNrOfFailedMdpFiles();
    }

    public List<MdpFile> getSuccessfulMpdFiles() {
        return this.successfulMpdFiles;
    }

    public List<BuildFail> getFailedMdpFiles() {
        return this.failedMdpFiles;
    }

    public boolean isSuccess() {
        return this.failedMdpFiles.isEmpty();
    }

}
