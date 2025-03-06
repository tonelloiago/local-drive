package com.github.tonelloiago.localdrive.configuration;

import com.github.tonelloiago.localdrive.configuration.watcher.LocalFolderWatcher;
import com.github.tonelloiago.localdrive.configuration.watcher.annotation.WatcherUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class LocalDriveConfiguration {

    @Bean
    @DependsOn({"configureLocalFolder", "configureGoogleDrive"})
    public LocalDriveConfiguration run() {
        var localFolderWatcher = new LocalFolderWatcher();
        WatcherUtils.runWatcher(localFolderWatcher);
        return this;
    }

}
