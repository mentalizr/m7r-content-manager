package org.mentalizr.contentManager;

import org.mentalizr.contentManager.build.BuildHandler;
import org.mentalizr.contentManager.exceptions.NoSuchProgramException;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.ContentNotFoundException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.MalformedMediaResourceNameException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.NoSuchMediaResourceException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentFile.HtmlFile;

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
    }

    public Path getContent(String id) throws ContentNotFoundException {
        if (contentFileMap.containsKey(id)) {
            return this.contentFileMap.get(id);
        }
        throw new ContentNotFoundException(id);
    }

    public Path getMediaResource(String programName, String fileName) throws NoSuchProgramException, NoSuchMediaResourceException, MalformedMediaResourceNameException {
        Program program = assertProgram(programName);
        return program.getMediaResource(fileName);
    }

    public void build(String programName, BuildHandler buildHandler) throws ContentManagerException {
        Program program = assertProgram(programName);
        program.build(buildHandler);
    }

    public void buildAll(BuildHandler buildHandler) throws ContentManagerException {
        for (Program program : this.programMap.values()) program.build(buildHandler);
    }

    public void cleanBuild(String programName, BuildHandler buildHandler) throws ContentManagerException {
        Program program = assertProgram(programName);
        program.clean();
        program.build(buildHandler);
    }

    public void cleanBuildAll(BuildHandler buildHandler) throws ContentManagerException {
        for (Program program : this.programMap.values()) {
            program.clean();
            program.build(buildHandler);
        }
    }

    private Program assertProgram(String programName) throws NoSuchProgramException {
        if (!programMap.containsKey(programName)) throw new NoSuchProgramException(programName);
        return this.programMap.get(programName);
    }

}
