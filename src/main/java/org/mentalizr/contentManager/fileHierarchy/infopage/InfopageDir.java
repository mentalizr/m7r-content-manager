package org.mentalizr.contentManager.fileHierarchy.infopage;

import org.mentalizr.contentManager.fileHierarchy.contentFile.ContentFile;

import java.util.List;

public interface InfopageDir {

    String DIR_NAME = "_info";

    List<? extends ContentFile> getInfopageFiles();

}
