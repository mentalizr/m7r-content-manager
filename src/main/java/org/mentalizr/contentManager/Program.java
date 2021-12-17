package org.mentalizr.contentManager;

import de.arthurpicht.utils.io.nio2.FileUtils;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.exceptions.InconsistencyException;
import org.mentalizr.contentManager.exceptions.ValidationException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.MalformedMediaResourceNameException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.NoSuchMediaResourceException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.MdpFile;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.HtmlDir;
import org.mentalizr.contentManager.fileHierarchy.levels.program.ProgramDir;
import org.mentalizr.contentManager.helper.Nio2Helper;
import org.mentalizr.contentManager.programStructure.ProgramStructure;
import org.mentalizr.contentManager.validator.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Program {

    private ProgramDir programDir;

    public Program(Path programPath) throws ContentManagerException {
        validatePre(programPath);
        this.programDir = new ProgramDir(programPath.toFile());
        validate();
    }

    public void reinitializeProgramDir() throws ContentManagerException {
        Path programPath = this.programDir.asPath();
        this.programDir = new ProgramDir(programPath.toFile());
        validate();
    }

    private void validatePre(Path programPath) throws InconsistencyException {
        BasicDirsValidator.validate(programPath);
    }

    public void validate() throws ValidationException {

        Validator nonEmptyStructuresValidator = new NonEmptyStructuresValidator(this);
        List<ValidationError> validationErrors = new ArrayList<>(nonEmptyStructuresValidator.getValidationResult().getValidationErrors());

        Validator exerciseAndFeedbackMarkValidator = new ExerciseAndFeedbackMarkValidator(this);
        validationErrors.addAll(exerciseAndFeedbackMarkValidator.getValidationResult().getValidationErrors());

        if (!validationErrors.isEmpty()) throw new ValidationException(validationErrors);
    }

    public Path getPath() {
        return this.programDir.asPath();
    }

    public String getName() {
        return this.programDir.getName();
    }

    public ProgramDir getProgramDir() {
        return this.programDir;
    }

    public List<HtmlFile> getHtmlFiles() {
        return this.programDir.getHtmlFiles();
    }

    public List<MdpFile> getMdpFiles() {
        return this.programDir.getMdpFiles();
    }

    public ProgramStructure asProgramStructure() {
        return this.programDir.asProgramStructure();
    }

    public Path getMediaResource(String fileName) throws MalformedMediaResourceNameException, NoSuchMediaResourceException {
        return this.programDir.getMediaDir().getMediaResource(fileName);
    }

    public boolean hasHtmlDir() {
        return this.programDir.hasHtmlDir();
    }

    public void cleanHtmlDir() throws ContentManagerException {
        if (this.hasHtmlDir()) {
            rmHtmlDir();
            reinitializeProgramDir();
        }
    }

    public Set<String> getAllMediaResourceNames() throws ContentManagerException {
        return this.programDir.getMediaDir().getAllMediaResourceNames();
    }

    public static void assertHasHtmlDir(Path programPath) throws ContentManagerException {
        if (!Nio2Helper.isExistingDir(programPath))
            throw new ContentManagerException("Program repo not existing. [" + programPath.toAbsolutePath() + "]");

        Path htmlDir = programPath.resolve(HtmlDir.DIR_NAME);
        if (!Nio2Helper.isExistingDir(htmlDir))
            throw new ContentManagerException("No html directory in program repo. [" + htmlDir.toAbsolutePath() + "]");
    }

    public static Path getHtmlDestinationForMdpFile(Path mdpDir, Path htmlDir, MdpFile mdpFile) {
        Path relativePath = mdpDir.relativize(mdpFile.asPath());
        Path creationFile = htmlDir.resolve(relativePath);

        Path creationDir = creationFile.getParent();
        String htmlFileName = mdpFile.getName() + HtmlFile.FILETYPE;

        return creationDir.resolve(htmlFileName);
    }

    public Path getHtmlDestinationForMdpFile(MdpFile mdpFile) {
        Path relativePath = this.programDir.getMdpDir().asPath().relativize(mdpFile.asPath());
        Path creationFile = this.programDir.getHtmlDir().asPath().resolve(relativePath);

        Path creationDir = creationFile.getParent();
        String htmlFileName = mdpFile.getName() + HtmlFile.FILETYPE;

        return creationDir.resolve(htmlFileName);
    }

    private void rmHtmlDir() throws ContentManagerException {
        Path htmlDir = this.programDir.getHtmlDir().asPath();
        try {
            FileUtils.rmDir(htmlDir);
        } catch (IOException e) {
            throw new ContentManagerException("Exception on cleaning html file." +
                    " [" + htmlDir.toAbsolutePath() + "]", e);
        }
    }

    public void createHtmlDirSkeleton() throws ContentManagerException {
        if (this.programDir.hasHtmlDir())
            throw new IllegalStateException("html directory is already existing. Check before calling");

        try {
            Programs.createHtmlDirSkeleton(this.programDir);
            this.programDir = ProgramDir.reinitializeHtmlDir(this.programDir);
        } catch (IOException e) {
            throw new ContentManagerException(e);
        }
    }

}
