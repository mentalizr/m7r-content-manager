package org.mentalizr.contentManager.programManager;

import de.arthurpicht.utils.core.strings.Strings;
import org.mentalizr.contentManager.Configuration;
import org.mentalizr.contentManager.ProgramConsistencyException;
import org.mentalizr.serviceObjects.frontend.program.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProgramManagerOld {

    private static Logger logger = LoggerFactory.getLogger(ProgramManagerOld.class);

    private static Map<String, Program> programMap;

    static {
        try {
            initContentMap();
        } catch (IOException e) {
            logger.error("IOException when initializing ProgramManager: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (ProgramConsistencyException e) {
            logger.error("Program consistency exception: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void initContentMap() throws IOException, ProgramConsistencyException {

        programMap = new HashMap<>();

//        File contentRoot = Configuration.getDirProgramContentRoot();
        File contentRoot = Configuration.CONTENT_ROOT_DIR;
        Path startPath = contentRoot.toPath();
        int maxDepth = 4;

        logger.info("Initializing content/programs with CONTENT_ROOT = " + contentRoot);

        Files.walkFileTree(startPath, new HashSet<>(), maxDepth, new ProgramFileVisitor(programMap, contentRoot));

        checkConsistency();
    }

    private static void checkConsistency() throws ProgramConsistencyException {

        logger.info("Check dependency of programs.");

        Set<String> programKeySet = programMap.keySet();
        for (String programId : programKeySet) {
            Program program = programMap.get(programId);

            if (Strings.isNullOrEmpty(program.getName())) {
                throw new ProgramConsistencyException("Program " + programId + ": name is null or empty.");
            }

            if (program.getModules() == null || program.getModules().isEmpty()) {
                throw new ProgramConsistencyException("Program " + programId + ": no modules found.");
            }

            if (program.getInfotexts() == null ) {
                throw new ProgramConsistencyException("Program " + programId + ": no subdirectory '_info' found.");
            }
        }
    }

    public static void test() {
        logger.info("Aufruf ProgramManager.test()");
    }

    public static Program getProgram(String id) {

        ProgramDebug.out(programMap.get(id));
        return programMap.get(id);
    }

    public static void main(String[] args) throws Exception {
        Program program = ProgramManagerOld.getProgram("dep");
        ProgramDebug.out(program);
    }

}
