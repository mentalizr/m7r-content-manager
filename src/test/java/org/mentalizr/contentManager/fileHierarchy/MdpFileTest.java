package org.mentalizr.contentManager.fileHierarchy;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.FileNotFoundException;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.contentFile.MdpFile;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class MdpFileTest {

    @Test
    public void test() throws ProgramManagerException {
        new MdpFile(new File("src/test/testPrograms/test1/mdp/_info/info1.mdp"));
    }

    @Test
    public void fileNotFound() throws ProgramManagerException {
        try {
            new MdpFile(new File("notExisting.mdp"));
            fail(FileNotFoundException.class.getSimpleName() + " expected.");
        } catch (FileNotFoundException e) {
            // din
        }
    }


}