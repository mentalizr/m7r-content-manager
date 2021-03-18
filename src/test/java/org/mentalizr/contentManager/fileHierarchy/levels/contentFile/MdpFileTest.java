package org.mentalizr.contentManager.fileHierarchy.levels.contentFile;

import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.exceptions.ProgramManagerException;
import org.mentalizr.contentManager.fileHierarchy.exceptions.FileNotFoundException;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class MdpFileTest {

    @Test
    public void constructor() throws ProgramManagerException {
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

    @Test
    public void id() throws ProgramManagerException {
        MdpFile file = new MdpFile(new File("src/test/testPrograms/test1/mdp/m1/sm1/s1.mdp"));
        assertEquals("test1_m1_sm1_s1", file.getId());
    }

    @Test
    public void idOfInfotextMdp() throws ProgramManagerException {
        MdpFile mdpFile = new MdpFile(new File("src/test/testPrograms/test1/mdp/_info/info1.mdp"));
        assertEquals("test1__info_info1", mdpFile.getId());
    }

    @Test
    public void getName() throws ProgramManagerException {
        MdpFile mdpFile = new MdpFile(new File("src/test/testPrograms/test1/mdp/_info/info1.mdp"));
        assertEquals("info1", mdpFile.getName());
    }

    @Test
    public void getDisplayName() throws ProgramManagerException {
        MdpFile mdpFile = new MdpFile(new File("src/test/testPrograms/test1/mdp/_info/info1.mdp"));
        assertEquals("Info1", mdpFile.getDisplayName());
    }

    @Test
    public void getFileType() throws ProgramManagerException {
        MdpFile mdpFile = new MdpFile(new File("src/test/testPrograms/test1/mdp/m1/sm1/s1.mdp"));
        assertEquals(".mdp", mdpFile.getFiletype());
    }

}