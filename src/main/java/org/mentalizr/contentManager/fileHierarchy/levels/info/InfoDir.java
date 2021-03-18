package org.mentalizr.contentManager.fileHierarchy.levels.info;

import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.ContentFile;

import java.util.List;

public interface InfoDir {

    String DIR_NAME = "_info";

    List<? extends ContentFile> getContentFiles();

}
