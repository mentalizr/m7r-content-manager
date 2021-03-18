package org.mentalizr.contentManager.outdated.contentManager;

import org.mentalizr.contentManager.outdated.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Deprecated
public class ContentManager {

    private static final Logger logger = LoggerFactory.getLogger(ContentManager.class);

    private static Map<String, File> stepContentMap;
    private static Map<String, File> infoContentMap;

    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    static {
        try {
            initContentMap();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initContentMap() throws IOException {

        stepContentMap = new HashMap<>();
        infoContentMap = new HashMap<>();

        File contentRoot = Configuration.CONTENT_ROOT_DIR;
        Path startPath = contentRoot.toPath();
        int maxDepth = 4;

        Files.walkFileTree(startPath, new HashSet<>(), maxDepth, new ContentFileVisitor<Path>(stepContentMap, infoContentMap, contentRoot));

        logger.debug("Gefundene Content-Files:");
        for (String id : stepContentMap.keySet()) {
            File contentFile = stepContentMap.get(id);
            logger.debug("ID: " + id + " File: " + contentFile.getAbsolutePath());
        }

        logger.debug("Gefundene Info-Files:");
        for (String id : infoContentMap.keySet()) {
            File contentFile = infoContentMap.get(id);
            logger.debug("ID: " + id + " File: " + contentFile.getAbsolutePath());
        }

    }

    private static File getErrorFile() {
        return new File(Configuration.CONTENT_ERROR_DIR, "error.html");
    }

    public static File getStepContentFile(String id) {
        readWriteLock.readLock().lock();
        try {
            File file = stepContentMap.get(id);
            return file == null ? getErrorFile() : file;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static File getInfoContentFile(String id) {
        readWriteLock.readLock().lock();
        try {
            File file = infoContentMap.get(id);
            return file == null ? getErrorFile() : file;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static void reloadContent() throws IOException {
        readWriteLock.writeLock().lock();
        try {
            initContentMap();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(ContentManager.getStepContentFile("depression_m1_sm1_s1").toString());
        System.out.println(ContentManager.getStepContentFile("depression_m1_sm1_s1_ddud").toString());
    }

}
