package org.mentalizr.contentManager.helper;

import de.arthurpicht.utils.io.tempDir.TempDir;
import de.arthurpicht.utils.io.tempDir.TempDirs;
import org.junit.jupiter.api.Test;
import org.mentalizr.contentManager.TestConfig;
import org.mentalizr.contentManager.exceptions.ContentManagerException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DirectiveInFileCheckerTest {

    @Test
    public void feedbackDirective() throws IOException, ContentManagerException {
        // prepare
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        Path filePath = tempDir.asPath().resolve("myFile");
        Files.writeString(filePath, "firstLine\n@@feedback\nnextLine");
        //execute/test
        assertTrue(DirectiveInFileChecker.hasFeedbackDirective(filePath));
    }

    @Test
    public void feedbackDirective_neg() throws IOException, ContentManagerException {
        // prepare
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        Path filePath = tempDir.asPath().resolve("myFile");
        Files.writeString(filePath, "firstLine\nsomething else\nnextLine");
        //execute/test
        assertFalse(DirectiveInFileChecker.hasFeedbackDirective(filePath));
    }

    @Test
    public void exerciseDirective() throws IOException, ContentManagerException {
        // prepare
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        Path filePath = tempDir.asPath().resolve("myFile");
        Files.writeString(filePath, "firstLine\n@@exercise\nnextLine");
        //execute/test
        assertTrue(DirectiveInFileChecker.hasExerciseDirective(filePath));
    }

    @Test
    public void exerciseDirective_neg() throws IOException, ContentManagerException {
        // prepare
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        Path filePath = tempDir.asPath().resolve("myFile");
        Files.writeString(filePath, "firstLine\nsomething else\nnextLine");
        //execute/test
        assertFalse(DirectiveInFileChecker.hasExerciseDirective(filePath));
    }

    @Test
    public void obtainDisplayName() throws IOException, ContentManagerException {
        // prepare
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        Path filePath = tempDir.asPath().resolve("myFile");
        Files.writeString(filePath, "firstLine\n@@name=myName\nnextLine");
        //execute/test
        assertEquals("myName", DirectiveInFileChecker.obtainDisplayName(filePath));
    }

    @Test
    public void obtainDisplayName_neg() throws IOException {
        // prepare
        TempDir tempDir = TempDirs.createUniqueTempDirAutoRemove(TestConfig.PROJECT_TEMP_DIR);
        Path filePath = tempDir.asPath().resolve("myFile");
        Files.writeString(filePath, "firstLine\nsomething else\nnextLine");
        //execute/test
        ContentManagerException e =
                assertThrows(ContentManagerException.class, () -> DirectiveInFileChecker.obtainDisplayName(filePath));
        assertTrue(e.getMessage().contains("Syntax error in"));
        assertTrue(e.getMessage().contains("Tag @@name not found"));
    }

}