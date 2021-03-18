package org.mentalizr.contentManager.fileHierarchy.levels.module;

import java.io.File;
import java.io.FileFilter;

@SuppressWarnings("RedundantIfStatement")
public class ModuleDirFileFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        if (!pathname.isDirectory()) return false;
        if (pathname.getName().startsWith(".")) return false;
        if (pathname.getName().startsWith("_")) return false;
        return true;
    }
}
