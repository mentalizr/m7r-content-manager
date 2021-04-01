package org.mentalizr.contentManager.build;

import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;

import java.util.Objects;

public class BuildFail {

    private final MdpFile mdpFile;
    private final Exception exception;

    public BuildFail(MdpFile mdpFile, Exception exception) {
        this.mdpFile = mdpFile;
        this.exception = exception;
    }

    public MdpFile getMdpFile() {
        return mdpFile;
    }

    public Exception getException() {
        return exception;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuildFail buildFail = (BuildFail) o;
        return mdpFile.equals(buildFail.mdpFile) && exception.equals(buildFail.exception);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mdpFile, exception);
    }
}
