package org.mentalizr.contentManager;

import java.io.File;

public class MdpDir {

    private final ProgramDir programDir;
//    private final Program program;
//    private final File mdpDir;

    public MdpDir(ProgramDir programDir) throws ProgramManagerException {
        this.programDir = programDir;

        File mdpDir = new File(programDir.getProgramDir(), "mdp");

        if (!mdpDir.exists() || !mdpDir.isDirectory())
            throw new ProgramManagerException("No directory 'mdp' found. Expected: [" + mdpDir.getAbsolutePath() + "]");

        if (!mdpDir.canRead())
            throw PermissionMissingException.forDirectory(mdpDir);


    }

}
