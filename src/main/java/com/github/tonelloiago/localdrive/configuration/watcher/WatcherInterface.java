package com.github.tonelloiago.localdrive.configuration.watcher;

import com.github.tonelloiago.localdrive.domain.Event;

public interface WatcherInterface {

    void watcher();
    Event createFile();
    Event deleteFile();
    Event updateFile();

}
