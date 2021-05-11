package org.mentalizr.contentManager.build;

import org.mentalizr.contentManager.Program;
import org.mentalizr.contentManager.buildHandler.BuildHandler;
import org.mentalizr.contentManager.buildHandler.BuildHandlerFactory;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;

public class TestBuildHandlerFactory extends BuildHandlerFactory {

    @Override
    public BuildHandler createBuildHandler(Program program, MdpFile mdpFile) {
        return new TestBuildHandler(program, mdpFile);
    }

    @Override
    public String getCompilerVersion() {
        return "Test";
    }

}
