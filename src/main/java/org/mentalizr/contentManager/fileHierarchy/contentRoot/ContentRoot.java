package org.mentalizr.contentManager.fileHierarchy.contentRoot;

import org.mentalizr.contentManager.fileHierarchy.infotext.InfotextDir;
import org.mentalizr.contentManager.fileHierarchy.module.ModuleDir;

import java.util.List;

public interface ContentRoot {

    ProgramConfFile getProgramConfFile();

    InfotextDir getInfotextDir();

    List<? extends ModuleDir> getModuleDirs();

    List<String> getModuleDirNames();

    boolean hasModuleDir(String dirName);

    ModuleDir getModuleDir(String dirName);
}