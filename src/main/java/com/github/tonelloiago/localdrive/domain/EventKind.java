package com.github.tonelloiago.localdrive.domain;

import com.github.tonelloiago.localdrive.configuration.watcher.WatcherInterface;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.Arrays;
import java.util.function.Consumer;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public enum EventKind {

    LIST(null, null, null),
    CREATE(ENTRY_CREATE, "CREATE", WatcherInterface::createFile),
    DELETE(ENTRY_DELETE, "DELETE", WatcherInterface::deleteFile),
    UPDATE(ENTRY_MODIFY, "UPDATE", WatcherInterface::updateFile);

    private final WatchEvent.Kind<Path> unixEventKind;
    private final String googleDriveEventKind;
    private final Consumer<WatcherInterface> action;

    EventKind(WatchEvent.Kind<Path> unixEventKind, String googleDriveEventKind, Consumer<WatcherInterface> action) {
        this.unixEventKind = unixEventKind;
        this.googleDriveEventKind = googleDriveEventKind;
        this.action = action;
    }

    public static EventKind fromUnixKind(WatchEvent.Kind<Path> unixEventKind) {
        return Arrays.stream(values())
                .filter(eventKind -> eventKind.unixEventKind.equals(unixEventKind))
                .findFirst()
                .orElse(null);
    }

    public static EventKind fromDriveEventKind(String driveEventKind) {
        return Arrays.stream(values())
                .filter(eventKind -> eventKind.googleDriveEventKind.equals(driveEventKind))
                .findFirst()
                .orElse(null);
    }

    public void takeAction(WatcherInterface watcherInterface) {
        action.accept(watcherInterface);
    }
}
