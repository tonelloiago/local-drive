package com.github.tonelloiago.localdrive.configuration;

import com.github.tonelloiago.localdrive.configuration.folder.GoogleDriveConfiguration;
import com.github.tonelloiago.localdrive.exception.LocalDriveBaseException;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.auth.oauth2.GoogleCredentials;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class GoogleDriveConfigurationTest {

    @InjectMocks
    GoogleDriveConfiguration googleDriveConfiguration;

    @Test
    void shouldConfigureGoogleDriveConnectionCorrectly() {
        try (var mockedCredentials = mockStatic(GoogleCredentials.class);
             var mockedTransport = mockStatic(GoogleNetHttpTransport.class)) {

            var mockGoogleCredentials = mock(GoogleCredentials.class);
            mockedCredentials.when(() -> GoogleCredentials.fromStream(any())).thenReturn(mockGoogleCredentials);

            var netHttpTransport = mock(NetHttpTransport.class);
            mockedTransport.when(GoogleNetHttpTransport::newTrustedTransport).thenReturn(netHttpTransport);

            var result = googleDriveConfiguration.configureGoogleDrive();
            assertNotNull(result);
        }
    }

    @Test
    void shouldThrowExceptionIfFailsToConfigure() {
        try (var mockedCredentials = mockStatic(GoogleCredentials.class)) {
            mockedCredentials.when(() -> GoogleCredentials.fromStream(any())).thenThrow(new IOException());
            assertThrows(LocalDriveBaseException.class, () -> googleDriveConfiguration.configureGoogleDrive());
        }
    }
}
