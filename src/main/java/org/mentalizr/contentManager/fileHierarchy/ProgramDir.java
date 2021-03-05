package org.mentalizr.contentManager.fileHierarchy;

import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.serviceObjects.frontend.Program;

import java.io.File;
import java.util.ArrayList;

public class ProgramDir extends FhDirectory {

    private final MdpDir mdpDir;

    public ProgramDir(File file) throws ProgramManagerException {
        super(file);
        this.mdpDir = new MdpDir(new File(getFile(), "mdp"));

//        assertIsDirectory();
//        assertReadPermissions();
//
//        initMdpDirectory();
//        initMediaDirectory();
//        initProgramConfFile();

//        this.program = new Program(getProgramId(), getDisplayName(), new ArrayList<>());
    }

    public MdpDir getMdpDir() {
        return this.mdpDir;
    }

    @Override
    public boolean requiresExistence() {
        return true;
    }

    @Override
    public boolean requiresReadPermission() {
        return true;
    }

    @Override
    public boolean requiresWritePermission() {
        return false;
    }

//    private void assertIsDirectory() throws ProgramManagerException {
//        if (!programDir.isDirectory())
//            throw new ProgramManagerException("Specified program directory is no directory [" + programDir.getAbsolutePath() + "]");
//    }
//
//    private void assertReadPermissions() throws ProgramManagerException {
//        if (!programDir.canRead())
//            throw new ProgramManagerException("No read permissions in program directory [" + programDir.getAbsolutePath() + "]");
//    }
//
//    private void initMdpDirectory() throws ProgramManagerException {
//        this.mdpDir = new File(programDir, MDP_DIR);
//        if (!mdpDir.exists() || !mdpDir.isDirectory())
//            throw new ProgramManagerException("No subdirectory 'mdp' found in [" + programDir.getAbsolutePath() + "]");
//    }
//
//    private void initMediaDirectory() throws ProgramManagerException {
//        this.mediaDir = new File(programDir, "media");
//        if (!this.mediaDir.exists() || !this.mediaDir.isDirectory())
//            throw new ProgramManagerException("No subdirectory 'media' found in [" + programDir.getAbsolutePath() + "]");
//    }
//
//    private void initProgramConfFile() throws ProgramManagerException {
//        File programConfFile = new File(programDir, PROGRAM_CONF);
//        if (!programConfFile.exists())
//            throw new ProgramManagerException("Configuration file program.conf not found in program directory [" + programDir.getAbsolutePath() + "]");
//        this.programConf = new ProgramConf(programConfFile);
//    }

//    public File getProgramDir() {
//        return this.programDir;
//    }
//
//    public String getProgramId() {
//        return this.programDir.getName();
//    }
//
//    public String getDisplayName() {
//        return this.programConf.getName();
//    }
//
//    public Program getProgram() {
//        return this.program;
//    }

}
