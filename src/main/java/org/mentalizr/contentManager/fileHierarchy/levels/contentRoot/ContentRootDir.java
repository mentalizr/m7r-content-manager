package org.mentalizr.contentManager.fileHierarchy.levels.contentRoot;

import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.ContentFile;
import org.mentalizr.contentManager.fileHierarchy.levels.info.InfoDir;
import org.mentalizr.contentManager.fileHierarchy.levels.module.ModuleDir;

import java.util.List;

public interface ContentRootDir {

    ProgramConfFile getProgramConfFile();

    InfoDir getInfoDir();

    List<? extends ModuleDir> getModuleDirs();

    List<String> getModuleDirNames();

    boolean hasModuleDir(String dirName);

    ModuleDir getModuleDir(String dirName);

    List<? extends ContentFile> getContentFiles();
}
