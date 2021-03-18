package org.mentalizr.contentManager.fileHierarchy.levels.module;

import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.ContentFile;
import org.mentalizr.contentManager.fileHierarchy.levels.submodule.SubmoduleDir;

import java.util.List;

public interface ModuleDir {

    ModuleConfFile getModuleConfFile();

    List<? extends SubmoduleDir> getSubmoduleDirs();

    List<String> getSubmoduleDirNames();

    boolean hasSubmoduleDir(String dirName);

    SubmoduleDir getSubmoduleDir(String dirName);

    List<? extends ContentFile> getContentFiles();

}
