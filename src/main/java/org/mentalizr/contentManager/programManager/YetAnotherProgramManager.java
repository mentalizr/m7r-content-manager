package org.mentalizr.contentManager.programManager;

import org.mentalizr.serviceObjects.frontend.program.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class YetAnotherProgramManager {
    private static Logger logger = LoggerFactory.getLogger(YetAnotherProgramManager.class);

    private static Map<String, Program> programMap;

    static {
//        try {
//            initContentMap();
//        } catch (IOException e) {
//            logger.error("IOException: " + e.getMessage());
//            throw new RuntimeException(e);
//        }
    }

//    private static void initContentMap() throws IOException {
//
//        programMap = new HashMap<>();
//
//        File contentRoot = ApplicationContext.getProgramContentRoot();
//        Path startPath = contentRoot.toPath();
//        int maxDepth = 4;
//
//        System.out.println("Initialisiere Progogramme mit CONTENT_ROOT = " + contentRoot);
//
//        Files.walkFileTree(startPath, new HashSet<>(), maxDepth, new ProgramFileVisitor(programMap, contentRoot));
//
//    }


//    public static void test() {
//
//        try {
//            initContentMap();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        logger.info("Aufruf YetAnotherProgramManager.getProgram");
//
//    }
}
