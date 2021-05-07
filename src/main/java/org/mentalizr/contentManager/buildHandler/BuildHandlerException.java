package org.mentalizr.contentManager.buildHandler;

import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;

public class BuildHandlerException extends Exception{

    private final MdpFile mdpFile;

    public BuildHandlerException(MdpFile mdpFile, String message) {
        super(message);
        this.mdpFile = mdpFile;
    }

    public BuildHandlerException(MdpFile mdpFile, Throwable cause) {
        super(cause.getMessage(), cause);
        this.mdpFile = mdpFile;
    }

    public MdpFile getMdpFile() {
        return this.mdpFile;
    }

}
