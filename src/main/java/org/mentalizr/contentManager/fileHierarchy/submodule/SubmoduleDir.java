package org.mentalizr.contentManager.fileHierarchy.submodule;

import org.mentalizr.contentManager.fileHierarchy.contentFile.ContentFile;
import org.mentalizr.contentManager.fileHierarchy.contentFile.MdpFile;

import java.util.List;

public interface SubmoduleDir {
    SubmoduleConfFile getSubmoduleConfFile();

    List<? extends ContentFile> getContentFiles();

    List<String> getStepFileNames();
}
