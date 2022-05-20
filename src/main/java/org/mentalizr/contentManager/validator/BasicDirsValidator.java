package org.mentalizr.contentManager.validator;

import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.contentManager.exceptions.InconsistencyException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.MdpDir;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.ProgramConfFile;
import org.mentalizr.contentManager.fileHierarchy.levels.info.InfoDir;
import org.mentalizr.contentManager.fileHierarchy.levels.media.MediaDir;

import java.nio.file.Path;

public class BasicDirsValidator {

    public static void validate(Path programPath) throws InconsistencyException {

        if (!FileUtils.isExistingDirectory(programPath))
            throw new InconsistencyException("Program repo not existing. [" + programPath.toAbsolutePath() + "]");

        Path mdpDir = programPath.resolve(MdpDir.DIR_NAME);
        if (!FileUtils.isExistingDirectory(mdpDir))
            throw new InconsistencyException("No program repo. mdp directory missing. [" + mdpDir.toAbsolutePath() + "]");

        Path mediaDir = programPath.resolve(MediaDir.DIR_NAME);
        if (!FileUtils.isExistingDirectory(mediaDir))
            throw new InconsistencyException("No program repo. media directory missing. [" + mediaDir.toAbsolutePath() + "]");

        Path infoDir = mdpDir.resolve(InfoDir.DIR_NAME);
        if (!FileUtils.isExistingDirectory(infoDir))
            throw new InconsistencyException("No program repo. info directory missing. [" + infoDir.toAbsolutePath() + "]");

        Path programConfig = mdpDir.resolve(ProgramConfFile.FILE_NAME);
        if (!FileUtils.isExistingRegularFile(programConfig))
            throw new InconsistencyException("No program repo. program.config file missing. [" + programConfig.toAbsolutePath() + "]");
    }

}
