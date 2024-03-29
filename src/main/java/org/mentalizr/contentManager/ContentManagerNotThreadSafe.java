package org.mentalizr.contentManager;

import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.exceptions.NoSuchProgramException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.ContentNotFoundException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.ProgramNotFoundException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.ContentFile;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;
import org.mentalizr.contentManager.programStructure.ProgramStructure;
import java.nio.file.Path;
import java.util.*;

import static de.arthurpicht.utils.io.assertions.PathAssertions.assertIsExistingDirectory;

public class ContentManagerNotThreadSafe {

    private final List<Path> programRootPaths;
    private Map<String, Program> programMap;
    private Map<String, Path> contentFileMap;
    private Set<String> infoTextSet;

    public ContentManagerNotThreadSafe(List<Path> programRootPaths) throws ContentManagerException {
        this.programRootPaths = programRootPaths;
        reinitialize();
    }

    public void reinitialize() throws ContentManagerException {
        this.programMap = new HashMap<>();
        this.contentFileMap = new HashMap<>();
        this.infoTextSet = new HashSet<>();
        for (Path path : this.programRootPaths) initProgram(path);
    }

    private void initProgram(Path programRootPath) throws ContentManagerException {
        assertIsExistingDirectory(programRootPath);
        Program program = new Program(programRootPath);
        this.programMap.put(program.getName(), program);
        List<HtmlFile> htmlFiles = program.getHtmlFiles();
        for (HtmlFile htmlFile : htmlFiles) {
            this.contentFileMap.put(htmlFile.getId(), htmlFile.asPath());
            if (htmlFile.getContentFileType() == ContentFile.ContentFileType.INFO) {
                infoTextSet.add(htmlFile.getId());
            }
        }
    }

    public ProgramStructure getProgramStructure(String programName) throws ProgramNotFoundException {
        if (programMap.containsKey(programName)) {
            return this.programMap.get(programName).asProgramStructure();
        }
        throw new ProgramNotFoundException(programName);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean hasContent(String id) {
        return this.contentFileMap.containsKey(id);
    }

    public Path getContent(String id) throws ContentNotFoundException {
        if (contentFileMap.containsKey(id)) {
            return this.contentFileMap.get(id);
        }
        throw new ContentNotFoundException(id);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isInfoTextContent(String id) {
        return this.infoTextSet.contains(id);
    }

    public Path getMediaResource(String programName, String fileName) throws ContentManagerException {
        Program program = assertProgram(programName);
        return program.getMediaResource(fileName);
    }

    private Program assertProgram(String programName) throws NoSuchProgramException {
        if (!programMap.containsKey(programName)) throw new NoSuchProgramException(programName);
        return this.programMap.get(programName);
    }

}
