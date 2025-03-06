package com.github.tonelloiago.localdrive.configuration.watcher;

import com.github.tonelloiago.localdrive.configuration.watcher.annotation.Watcher;
import com.github.tonelloiago.localdrive.domain.Event;

@Watcher
public class GoogleDriveWatcher implements WatcherInterface {
    @Override
    public void watcher() {

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
