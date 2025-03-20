package com.github.tonelloiago.localdrive.configuration;

import com.github.tonelloiago.localdrive.watcher.watcher.GoogleDriveWatcher;
import com.github.tonelloiago.localdrive.watcher.watcher.LocalFolderWatcher;
import com.github.tonelloiago.localdrive.watcher.watcher.annotation.WatcherUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalDriveConfiguration {

    @Autowired
    private LocalFolderWatcher localFolderWatcher;

    @Autowired
    private GoogleDriveWatcher googleDriveWatcher;

    @Bean
    public ApplicationRunner runAfterStartup() {
        return args -> {
            new Thread(() -> {
                WatcherUtils.runWatchers(localFolderWatcher, googleDriveWatcher);
            }).start();
        };
    }

}
