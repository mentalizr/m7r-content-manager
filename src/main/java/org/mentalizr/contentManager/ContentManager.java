package org.mentalizr.contentManager;

import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.ContentNotFoundException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.ProgramNotFoundException;
import org.mentalizr.contentManager.fileHierarchy.levels.contentRoot.ProgramConf;
import org.mentalizr.contentManager.programStructure.ProgramStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@SuppressWarnings("StringConcatenationArgumentToLogCall")
public class ContentManager extends ContentManagerNotThreadSafe {

    private static final Logger logger = LoggerFactory.getLogger(ContentManager.class);
    private final ReadWriteLock readWriteLock;

    public ContentManager(List<Path> programRootPaths) throws ContentManagerException {
        super(programRootPaths);
        this.readWriteLock = new ReentrantReadWriteLock();
    }

    public void reload() throws ContentManagerException {
        this.readWriteLock.writeLock().lock();
        try {
            super.reinitialize();
        } finally {
            this.readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public ProgramStructure getProgramStructure(String programName) throws ProgramNotFoundException {
        logger.debug("Requested programStructure for name: [" + programName + "]");
        this.readWriteLock.readLock().lock();
        try {
            return super.getProgramStructure(programName);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    public ProgramConf getProgramConfig(String programName) throws ProgramNotFoundException {
        logger.debug("Requested programConfig for name: [" + programName + "]");
        this.readWriteLock.readLock().lock();
        try {
            return super.getProgramConfig(programName);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    @Override
    public boolean hasContent(String id) {
        logger.debug("Check for content: [" + id + "]");
        this.readWriteLock.readLock().lock();
        try {
            return super.hasContent(id);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    @Override
    public Path getContent(String id) throws ContentNotFoundException {
        logger.debug("Requested content: [" + id + "]");
        this.readWriteLock.readLock().lock();
        try {
            return super.getContent(id);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    @Override
    public boolean isInfoTextContent(String id) {
        logger.debug("Check if content is infoText: [" + id + "]");
        this.readWriteLock.readLock().lock();
        try {
            return super.isInfoTextContent(id);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    @Override
    public Path getMediaResource(String programName, String fileName) throws ContentManagerException {
        logger.debug("Requested media resource. Program: [" + programName + "] File: [" + fileName + "].");
        this.readWriteLock.readLock().lock();
        try {
            return super.getMediaResource(programName, fileName);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

}