package org.mentalizr.contentManager;

import org.mentalizr.serviceObjects.frontend.Program;

import java.io.File;
import java.util.ArrayList;

public class ProgramDir {

    private static final String PROGRAM_CONF = "program.conf";
    private static final String MDP_DIR = "mdp";

    private final File programDir;
    private File mdpDir;
    private File mediaDir;
    private ProgramConf programConf;
    private final Program program;


    public ProgramDir(File programDir) throws ProgramManagerException {
        this.programDir = programDir;

        assertIsDirectory();
        assertReadPermissions();

        initMdpDirectory();
        initMediaDirectory();
        initProgramConfFile();

        this.program = new Program(getProgramId(), getDisplayName(), new ArrayList<>());
    }

    private void assertIsDirectory() throws ProgramManagerException {
        if (!programDir.isDirectory())
            throw new ProgramManagerException("Specified program directory is no directory [" + programDir.getAbsolutePath() + "]");
    }

    private void assertReadPermissions() throws ProgramManagerException {
        if (!programDir.canRead())
            throw new ProgramManagerException("No read permissions in program directory [" + programDir.getAbsolutePath() + "]");
    }

    private void initMdpDirectory() throws ProgramManagerException {
        this.mdpDir = new File(programDir, MDP_DIR);
        if (!mdpDir.exists() || !mdpDir.isDirectory())
            throw new ProgramManagerException("No subdirectory 'mdp' found in [" + programDir.getAbsolutePath() + "]");
    }

    private void initMediaDirectory() throws ProgramManagerException {
        this.mediaDir = new File(programDir, "media");
        if (!this.mediaDir.exists() || !this.mediaDir.isDirectory())
            throw new ProgramManagerException("No subdirectory 'media' found in [" + programDir.getAbsolutePath() + "]");
    }

    private void initProgramConfFile() throws ProgramManagerException {
        File programConfFile = new File(programDir, PROGRAM_CONF);
        if (!programConfFile.exists())
            throw new ProgramManagerException("Configuration file program.conf not found in program directory [" + programDir.getAbsolutePath() + "]");
        this.programConf = new ProgramConf(programConfFile);
    }

    public String getProgramId() {
        return this.programDir.getName();
    }

    public String getDisplayName() {
        return this.programConf.getName();
    }

    public Program getProgram() {
        return this.program;
    }

}
