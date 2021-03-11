package org.mentalizr.contentManager.fileHierarchy.info;

import org.mentalizr.contentManager.fileHierarchy.contentFile.ContentFile;

import java.util.List;

public interface InfoDir {

    String DIR_NAME = "_info";

    List<? extends ContentFile> getInfoFiles();

}
