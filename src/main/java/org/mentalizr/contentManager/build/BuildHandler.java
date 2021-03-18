package org.mentalizr.contentManager.build;

import org.mentalizr.contentManager.fileHierarchy.contentFile.MdpFile;

import java.util.List;

public interface BuildHandler {

    List<String> compile(MdpFile mdpFile) throws BuildException;

}
