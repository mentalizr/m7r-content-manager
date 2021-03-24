package org.mentalizr.contentManager;

import org.mentalizr.contentManager.build.BuildHandler;
import org.mentalizr.contentManager.exceptions.NoSuchProgramException;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
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

    public static ContentManager getInstanceForContentRoot(Path contentRoot) throws ProgramManagerException {
        try {
            List<Path> programRootDirs = Nio2Helper.getSubdirectories(contentRoot);
            return new ContentManager(programRootDirs);
        } catch (IOException e) {
            throw new ProgramManagerException(e.getMessage(), e);
        }
    }

    public ContentManager(List<Path> programRootPaths) throws ProgramManagerException {
        super(programRootPaths);
        this.readWriteLock = new ReentrantReadWriteLock();
    }

    @Override
    public void reinitialize() throws ProgramManagerException {
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
    public void build(String programName, BuildHandler buildHandler) throws ProgramManagerException {
        this.readWriteLock.writeLock().lock();
        try {
            super.build(programName, buildHandler);
        } finally {
            this.readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void buildAll(BuildHandler buildHandler) throws ProgramManagerException {
        this.readWriteLock.writeLock().lock();
        try {
            super.buildAll(buildHandler);
        } finally {
            this.readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void cleanBuild(String programName, BuildHandler buildHandler) throws ProgramManagerException {
        this.readWriteLock.writeLock().lock();
        try {
            super.cleanBuild(programName, buildHandler);
        } finally {
            this.readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void cleanBuildAll(BuildHandler buildHandler) throws ProgramManagerException {
        this.readWriteLock.writeLock().lock();
        try {
            super.cleanBuildAll(buildHandler);
        } finally {
            this.readWriteLock.writeLock().unlock();
        }
    }

}