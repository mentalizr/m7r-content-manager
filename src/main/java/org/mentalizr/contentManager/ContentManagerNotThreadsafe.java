package org.mentalizr.contentManager;

import org.mentalizr.contentManager.buildHandler.BuildHandlerFactory;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.exceptions.NoSuchProgramException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.ContentNotFoundException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.ProgramNotFoundException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;
import org.mentalizr.contentManager.programStructure.ProgramStructure;
import org.mentalizr.contentManager.programStructure.ProgramStructures;
import org.mentalizr.contentManager.programStructure.Step;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mentalizr.contentManager.helper.PathAssertions.assertIsExistingDirectory;

public class ContentManagerNotThreadsafe {

    private final List<Path> programRootPaths;
    private Map<String, Program> programMap;
    private Map<String, Path> contentFileMap;

    public ContentManagerNotThreadsafe(List<Path> programRootPaths) throws ContentManagerException {
        this.programRootPaths = programRootPaths;
        reinitialize();
    }

    public void reinitialize() throws ContentManagerException {
        this.programMap = new HashMap<>();
        this.contentFileMap = new HashMap<>();
        for (Path path : this.programRootPaths) initProgram(path);
    }

    private void initProgram(Path programRootPath) throws ContentManagerException {
        assertIsExistingDirectory(programRootPath);
        Program program = new Program(programRootPath);
        this.programMap.put(program.getName(), program);
        List<HtmlFile> htmlFiles = program.getHtmlFiles();
        for (HtmlFile htmlFile : htmlFiles) {
            this.contentFileMap.put(htmlFile.getId(), htmlFile.asPath());
        }
        validityCheck(program);
    }

    private void validityCheck(Program program) throws ContentManagerException {
        ProgramStructure programStructure = program.asProgramStructure();
        List<Step> stepList = ProgramStructures.stepList(programStructure);
        for (int i = 0; i < stepList.size(); i++) {
            Step currentStep = stepList.get(i);
            if (currentStep.isFeedback()) {
                if (i == 0)
                    throw new ContentManagerException("Validity check failed for program [" + program.getName() + "] " +
                            "and step [" + currentStep.getName() + "]: First step can not be marked as feedback.");
                Step previousStep = stepList.get(i - 1);
                if (!previousStep.isExercise())
                    throw new ContentManagerException("Validity check failed for program [" + program.getName() + "] " +
                            "and step [" + currentStep.getName() + "]: Feedback step has no preceding exercise step.");
            }
        }
    }

    public ProgramStructure getProgramStructure(String programName) throws ProgramNotFoundException {
        if (programMap.containsKey(programName)) {
            return this.programMap.get(programName).asProgramStructure();
        }
        throw new ProgramNotFoundException(programName);
    }

    public Path getContent(String id) throws ContentNotFoundException {
        if (contentFileMap.containsKey(id)) {
            return this.contentFileMap.get(id);
        }
        throw new ContentNotFoundException(id);
    }

    public Path getMediaResource(String programName, String fileName) throws ContentManagerException {
        Program program = assertProgram(programName);
        return program.getMediaResource(fileName);
    }

    public void build(String programName, BuildHandlerFactory buildHandlerFactory) throws ContentManagerException {
        Program program = assertProgram(programName);
        program.build(buildHandlerFactory);
    }

    public void buildAll(BuildHandlerFactory buildHandlerFactory) throws ContentManagerException {
        for (Program program : this.programMap.values()) program.build(buildHandlerFactory);
    }

    public void cleanBuild(String programName, BuildHandlerFactory buildHandlerFactory) throws ContentManagerException {
        Program program = assertProgram(programName);
        program.clean();
        program.build(buildHandlerFactory);
    }

    public void cleanBuildAll(BuildHandlerFactory buildHandlerFactory) throws ContentManagerException {
        for (Program program : this.programMap.values()) {
            program.clean();
            program.build(buildHandlerFactory);
        }
    }

    private Program assertProgram(String programName) throws NoSuchProgramException {
        if (!programMap.containsKey(programName)) throw new NoSuchProgramException(programName);
        return this.programMap.get(programName);
    }

}
