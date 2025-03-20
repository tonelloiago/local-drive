package com.github.tonelloiago.localdrive.watcher.watcher;

import com.github.tonelloiago.localdrive.domain.Event;
import com.github.tonelloiago.localdrive.domain.EventKind;

import java.io.IOException;

public interface WatcherInterface<T> {
    void watcher();
    Event buildEvent(EventKind eventKind, T filePath) throws IOException;
}
