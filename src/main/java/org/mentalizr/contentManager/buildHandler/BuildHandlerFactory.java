package org.mentalizr.contentManager.buildHandler;

import org.mentalizr.contentManager.Program;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;

public abstract class BuildHandlerFactory {

    public abstract BuildHandler createBuildHandler(Program program, MdpFile mdpFile);

    public abstract String getCompilerVersion();

}
