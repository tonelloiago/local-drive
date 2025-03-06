package com.github.tonelloiago.localdrive.configuration;

import com.github.tonelloiago.localdrive.exception.LocalDriveBaseException;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//TODO add logger
@Configuration
public class GoogleDriveConfiguration {

    private static final String APPLICATION_NAME = "Local Drive App";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    @Bean
    public Drive configureGoogleDrive() {

        try(var inputStream = GoogleDriveConfiguration.class.getResourceAsStream(CREDENTIALS_FILE_PATH)) {
            var googleCredentials = GoogleCredentials.fromStream(inputStream);
            var httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            var requestInitializer = new HttpCredentialsAdapter(googleCredentials);

            return new Drive.Builder(httpTransport, JSON_FACTORY, requestInitializer)
                    .setApplicationName(APPLICATION_NAME)
                    .build();

        } catch (Exception e) {
            System.out.println("Add logger");
            throw new LocalDriveBaseException();
        }
    }

}