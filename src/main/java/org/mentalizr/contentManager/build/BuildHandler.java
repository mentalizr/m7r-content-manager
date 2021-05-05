package org.mentalizr.contentManager.build;

import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;

import java.util.List;
import java.util.Set;

public interface BuildHandler {

    List<String> compile(MdpFile mdpFile) throws BuildException;

    Set<String> getReferencedMediaResources(MdpFile mdpFile) throws BuildException;

}
