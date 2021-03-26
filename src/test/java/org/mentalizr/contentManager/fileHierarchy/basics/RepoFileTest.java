package org.mentalizr.contentManager.fileHierarchy.basics;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class RepoFileTest {

    private class RepoFileTestImpl extends RepoFile {

        public RepoFileTestImpl(File file) throws ProgramManagerException {
            super(file);
        }

        @Override
        public boolean requiresExistence() {
            return false;
        }

        @Override
        public boolean requiresReadPermission() {
            return true;
        }

        @Override
        public boolean requiresWritePermission() {
            return true;
        }

        @Override
        protected String getFiletype() {
            return ".test";
        }
    }

    @Test
    void getFiletype() throws ProgramManagerException {
        RepoFileTestImpl repoFileTest = new RepoFileTestImpl(new File("not_existing.test"));
    }
}