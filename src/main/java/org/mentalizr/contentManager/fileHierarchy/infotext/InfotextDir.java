package org.mentalizr.contentManager.fileHierarchy.infotext;

import org.mentalizr.contentManager.fileHierarchy.contentFile.ContentFile;
import org.mentalizr.contentManager.fileHierarchy.contentFile.MdpFile;

import java.util.List;

public interface InfotextDir {

    public static final String DIR_NAME = "_info";

    public List<? extends ContentFile> getInfotextFiles();
}
