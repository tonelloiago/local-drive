package com.github.tonelloiago.localdrive.configuration.folder;

import com.github.tonelloiago.localdrive.configuration.handler.FilesHandler;
import com.github.tonelloiago.localdrive.exception.LocalDriveBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

//TODO add logger
@Configuration
public class LocalFolderConfiguration {

    @Autowired
    private FilesHandler filesHandler;

    @Bean
    public LocalFolderConfiguration configureLocalFolder() {
        try {

            var localFolderPath = filesHandler.getFolderPath();

            if(filesHandler.exists(localFolderPath)) {
                return this;
            }

            filesHandler.createDirectory(localFolderPath);
        }catch (Exception e) {
            System.err.println("ERROR: Cannot access directory specified in application.yaml");
            throw new LocalDriveBaseException();
        }

        return this;
    }

    public static void createFile(Path path) {

    }
}
