package com.github.tonelloiago.localdrive.watcher.watcher;

import com.github.tonelloiago.localdrive.event.event.queue.EventQueue;
import com.github.tonelloiago.localdrive.configuration.handler.FilesHandler;
import com.github.tonelloiago.localdrive.manager.LocalFolderManager;
import com.github.tonelloiago.localdrive.watcher.watcher.annotation.Watcher;
import com.github.tonelloiago.localdrive.domain.Event;
import com.github.tonelloiago.localdrive.domain.EventKind;
import com.github.tonelloiago.localdrive.domain.UnixAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

@Watcher
@Component
public class LocalFolderWatcher implements WatcherInterface<Path> {

    @Autowired
    private EventQueue eventQueue;

    @Autowired
    private FilesHandler filesHandler;

    public void watcher() {

        try(var watchService = FileSystems.getDefault().newWatchService()) {

            var folderPath = filesHandler.getFolderPath();
            folderPath.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

            while(true) {
                var key = watchService.take();

                for (WatchEvent<?> event : key.pollEvents()) {
                    var kind = event.kind();
                    var filePath = folderPath.resolve((Path) event.context());

                    var eventKind = UnixAction.valueOf(kind.name()).getEventKind();
                    eventQueue.publish(buildEvent(eventKind, filePath));
                }

                key.reset();
            }

        } catch (IOException | InterruptedException ex) {
            System.err.println();
        }
    }

    @Override
    public Event buildEvent(EventKind eventKind, Path filePath) throws IOException {
        if (EventKind.CREATE.equals(eventKind)) {
            return new Event(eventKind, "hash", filesHandler.readFile(filePath), filePath.getFileName().toString(), LocalFolderManager.class);
        }

        return new Event(eventKind, "hash", null, filePath.getFileName().toString(), LocalFolderManager.class);
    }
}
