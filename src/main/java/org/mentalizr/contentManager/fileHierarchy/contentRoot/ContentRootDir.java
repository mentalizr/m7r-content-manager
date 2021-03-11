package org.mentalizr.contentManager.fileHierarchy.contentRoot;

import org.mentalizr.contentManager.fileHierarchy.contentFile.ContentFile;
import org.mentalizr.contentManager.fileHierarchy.info.InfoDir;
import org.mentalizr.contentManager.fileHierarchy.module.ModuleDir;

import java.util.List;

public interface ContentRootDir {

    ProgramConfFile getProgramConfFile();

    InfoDir getInfotextDir();

    List<? extends ModuleDir> getModuleDirs();

    List<String> getModuleDirNames();

    boolean hasModuleDir(String dirName);

    ModuleDir getModuleDir(String dirName);

    List<? extends ContentFile> getStepContentFiles();
}
