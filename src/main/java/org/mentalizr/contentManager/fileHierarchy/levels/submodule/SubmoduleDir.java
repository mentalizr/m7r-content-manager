package org.mentalizr.contentManager.fileHierarchy.levels.submodule;

import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.ContentFile;

import java.util.List;

public interface SubmoduleDir {
    SubmoduleConfFile getSubmoduleConfFile();

    List<? extends ContentFile> getContentFiles();

    List<String> getStepFileNames();
}
