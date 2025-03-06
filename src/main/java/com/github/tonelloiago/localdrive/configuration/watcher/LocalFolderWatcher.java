package com.github.tonelloiago.localdrive.configuration.watcher;

import com.github.tonelloiago.localdrive.configuration.folder.LocalFolderConfiguration;
import com.github.tonelloiago.localdrive.configuration.watcher.annotation.Watcher;
import com.github.tonelloiago.localdrive.domain.Event;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

//TODO add logger
@Watcher
public class LocalFolderWatcher implements WatcherInterface {

    public void watcher() {

        try(var watchService = FileSystems.getDefault().newWatchService()) {

            var folderPath = LocalFolderConfiguration.getLocalFolderPath();
            folderPath.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

            while(true) {
                var key = watchService.take();

                for (WatchEvent<?> event : key.pollEvents()) {
                    var kind = event.kind();
                    var filePath = folderPath.resolve((Path) event.context());

                    System.out.println("Event: " + kind + " on file: " + filePath );

                    //TODO montar evento e publicar

                }

                key.reset();
            }

        } catch (IOException | InterruptedException ex) {
            System.err.println();
        }
    }

    @Override
    public Event createFile() {
        return null;
    }

    @Override
    public Event deleteFile() {
        return null;
    }

    @Override
    public Event updateFile() {
        return null;
    }
}
