package com.github.tonelloiago.localdrive.configuration;

import com.github.tonelloiago.localdrive.configuration.handler.FilesHandler;
import com.github.tonelloiago.localdrive.exception.LocalDriveBaseException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LocalDriveConfigurationTest {

    @Mock
    FilesHandler filesHandler;

    @InjectMocks
    LocalDriveConfiguration localDriveConfiguration;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(localDriveConfiguration, "basePath", "/test-drive");
    }

    @AfterAll
    static void tearDown() throws IOException {
        Files.delete(getPath());
    }

    @Test
    @Order(1)
    void shouldCreateDirectoryIfDontExists() throws IOException {;
        Mockito.when(filesHandler.getHomeDirectory()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(filesHandler).createDirectory(getPath());
        Mockito.when(filesHandler.exists(any())).thenReturn(false);

        localDriveConfiguration.configureLocalFolder();

        Assertions.assertTrue(Files.exists(getPath()));
    }

    @Test
    @Order(2)
    void shouldntCreateDirectoryIfExists() {
        Mockito.when(filesHandler.getHomeDirectory()).thenCallRealMethod();
        Mockito.when(filesHandler.exists(getPath())).thenCallRealMethod();
        Assertions.assertDoesNotThrow(() -> localDriveConfiguration.configureLocalFolder());
        Assertions.assertTrue(Files.exists(getPath()));
    }

    @Test
    void shouldThrowLocalDriveBaseExceptionIfAnErrorOccurs() {
        Mockito.when(filesHandler.getHomeDirectory()).thenThrow(new SecurityException());
        Assertions.assertThrows(LocalDriveBaseException.class, () -> localDriveConfiguration.configureLocalFolder());
    }

    private static Path getPath() {
        return Path.of(System.getProperty("user.home").concat("/test-drive"));
    }
}
