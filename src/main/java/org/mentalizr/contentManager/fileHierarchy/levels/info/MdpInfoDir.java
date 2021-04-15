package org.mentalizr.contentManager.fileHierarchy.levels.info;

import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.basics.RepoDirectory;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.mentalizr.contentManager.helper.PathAssertions.assertFileName;

public class MdpInfoDir extends RepoDirectory implements InfoDir {

    private final List<MdpFile> infotextFiles;

    public MdpInfoDir(File file) throws ContentManagerException {
        super(file);
        assertFileName(file.toPath(), InfoDir.DIR_NAME);
        this.infotextFiles = obtainInfotextFiles();
    }

    @Override
    public List<MdpFile> getContentFiles() {
        return this.infotextFiles;
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

    private List<MdpFile> obtainInfotextFiles() throws ContentManagerException {
        File[] fileArray = this.file.listFiles(new MdpFileFilter());
        if (fileArray == null || fileArray.length == 0)
            return new ArrayList<>();

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
