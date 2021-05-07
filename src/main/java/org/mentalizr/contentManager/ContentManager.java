package org.mentalizr.contentManager;

import org.mentalizr.contentManager.buildHandler.BuildHandlerFactory;
import org.mentalizr.contentManager.exceptions.ContentManagerException;
import org.mentalizr.contentManager.exceptions.NoSuchProgramException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.ContentNotFoundException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.MalformedMediaResourceNameException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.NoSuchMediaResourceException;
import org.mentalizr.contentManager.helper.Nio2Helper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ContentManager extends ContentManagerNotThreadsafe {

    private final ReadWriteLock readWriteLock;

    public static ContentManager getInstanceForContentRoot(Path contentRoot) throws ContentManagerException {
        try {
            List<Path> programRootDirs = Nio2Helper.getSubdirectories(contentRoot);
            return new ContentManager(programRootDirs);
        } catch (IOException e) {
            throw new ContentManagerException(e.getMessage(), e);
        }
    }

    public ContentManager(List<Path> programRootPaths) throws ContentManagerException {
        super(programRootPaths);
        this.readWriteLock = new ReentrantReadWriteLock();
    }

    @Override
    public void reinitialize() throws ContentManagerException {
        this.readWriteLock.writeLock().lock();
        try {
            super.reinitialize();
        } finally {
            this.readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public Path getContent(String id) throws ContentNotFoundException {
        this.readWriteLock.readLock().lock();
        try {
            return super.getContent(id);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    @Override
    public Path getMediaResource(String programName, String fileName) throws NoSuchProgramException, NoSuchMediaResourceException, MalformedMediaResourceNameException {
        this.readWriteLock.readLock().lock();
        try {
            return super.getMediaResource(programName, fileName);
        } finally {
            this.readWriteLock.readLock().unlock();
        }
    }

    @Override
    public void build(String programName, BuildHandlerFactory buildHandlerFactory) throws ContentManagerException {
        this.readWriteLock.writeLock().lock();
        try {
            super.build(programName, buildHandlerFactory);
        } finally {
            this.readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void buildAll(BuildHandlerFactory buildHandlerFactory) throws ContentManagerException {
        this.readWriteLock.writeLock().lock();
        try {
            super.buildAll(buildHandlerFactory);
        } finally {
            this.readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void cleanBuild(String programName, BuildHandlerFactory buildHandlerFactory) throws ContentManagerException {
        this.readWriteLock.writeLock().lock();
        try {
            super.cleanBuild(programName, buildHandlerFactory);
        } finally {
            this.readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void cleanBuildAll(BuildHandlerFactory buildHandlerFactory) throws ContentManagerException {
        this.readWriteLock.writeLock().lock();
        try {
            super.cleanBuildAll(buildHandlerFactory);
        } finally {
            this.readWriteLock.writeLock().unlock();
        }
    }

}