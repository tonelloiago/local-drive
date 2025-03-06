package com.github.tonelloiago.localdrive.configuration;

import com.github.tonelloiago.localdrive.configuration.handler.FilesHandler;
import com.github.tonelloiago.localdrive.configuration.watcher.LocalFolderWatcher;
import com.github.tonelloiago.localdrive.configuration.watcher.annotation.WatcherUtils;
import com.github.tonelloiago.localdrive.exception.LocalDriveBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.nio.file.Path;

//TODO add logger
@Configuration
public class LocalDriveConfiguration {

    @Autowired
    private FilesHandler filesHandler;

    @Value("${local-drive.base-path}")
    private String basePath;

    private static Path localFolderPath;

    @Bean
    public LocalDriveConfiguration configureLocalFolder() {
        try {
            var homeDirectory = filesHandler.getHomeDirectory();
            localFolderPath = Path.of(homeDirectory.concat(basePath));

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

    public static Path getLocalFolderPath() {
        return localFolderPath;
    }

    public static void createFile(Path path) {

    }

    //TODO run tem que sair daqui
    @Bean
    @DependsOn({"configureLocalFolder", "configureGoogleDrive"})
    public LocalDriveConfiguration run() {
        var localFolderWatcher = new LocalFolderWatcher();
        WatcherUtils.runWatcher(localFolderWatcher);

        return this;
    }
}
