package org.mentalizr.contentManager.fileHierarchy.module;

import org.mentalizr.contentManager.fileHierarchy.submodule.SubmoduleDir;
import org.mentalizr.contentManager.fileHierarchy.submodule.SubmoduleDirMdp;

import java.util.List;

public interface ModuleDir {

    ModuleConfFile getModuleConfFile();

    List<? extends SubmoduleDir> getSubmoduleDirs();

    List<String> getSubmoduleDirNames();

    boolean hasSubmoduleDir(String dirName);

    SubmoduleDir getSubmoduleDir(String dirName);
}
