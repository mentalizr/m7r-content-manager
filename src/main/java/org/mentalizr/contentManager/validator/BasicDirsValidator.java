package org.mentalizr.contentManager.validator;

import org.mentalizr.contentManager.Program;
import org.mentalizr.contentManager.exceptions.InconsistencyException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.MdpDir;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.ProgramConfFile;
import org.mentalizr.contentManager.fileHierarchy.levels.info.InfoDir;
import org.mentalizr.contentManager.fileHierarchy.levels.media.MediaDir;
import org.mentalizr.contentManager.helper.Nio2Helper;

import java.nio.file.Path;

public class BasicDirsValidator {

    public static void validate(Path programPath) throws InconsistencyException {

        if (!Nio2Helper.isExistingDir(programPath))
            throw new InconsistencyException("Program repo not existing. [" + programPath.toAbsolutePath() + "]");

        Path mdpDir = programPath.resolve(MdpDir.DIR_NAME);
        if (!Nio2Helper.isExistingDir(mdpDir))
            throw new InconsistencyException("No program repo. mdp directory missing. [" + mdpDir.toAbsolutePath() + "]");

        Path mediaDir = programPath.resolve(MediaDir.DIR_NAME);
        if (!Nio2Helper.isExistingDir(mediaDir))
            throw new InconsistencyException("No program repo. media directory missing. [" + mediaDir.toAbsolutePath() + "]");

        Path infoDir = mdpDir.resolve(InfoDir.DIR_NAME);
        if (!Nio2Helper.isExistingDir(infoDir))
            throw new InconsistencyException("No program repo. info directory missing. [" + infoDir.toAbsolutePath() + "]");

        Path programConfig = mdpDir.resolve(ProgramConfFile.FILE_NAME);
        if (!Nio2Helper.isExistingRegularFile(programConfig))
            throw new InconsistencyException("No program repo. program.config file missing. [" + programConfig.toAbsolutePath() + "]");
    }

}
