package com.github.tonelloiago.localdrive.configuration.handler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class FilesHandlerTest {

    @InjectMocks
    FilesHandler filesHandler;

    @Test
    void shouldReturnTrueIfFolderExists() throws IOException {
        var tempFile = Files.createTempFile("test", ".txt");
        assertTrue(filesHandler.exists(tempFile));
        Files.deleteIfExists(tempFile);
    }

    @Test
    void shouldReturnFalseIfFolderDoesntExists() throws IOException {
        var fakePath = Paths.get("fake/path/that/does/not/exist.txt");
        assertFalse(filesHandler.exists(fakePath));
    }

    @Test
    void testCreateDirectory() throws IOException {
        var tempDir = Files.createTempDirectory("test");
        var newDir = tempDir.resolve("newDir");

        filesHandler.createDirectory(newDir);

        assertTrue(Files.exists(newDir));
        Files.deleteIfExists(newDir);
        Files.deleteIfExists(tempDir);
    }

    @Test
    void testGetHomeDirectory() {
        var expected = System.getProperty("user.home");
        assertEquals(expected, filesHandler.getHomeDirectory());
    }

}
