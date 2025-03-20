package com.github.tonelloiago.localdrive.watcher.watcher;

import com.github.tonelloiago.localdrive.domain.Event;
import com.github.tonelloiago.localdrive.domain.EventKind;
import com.github.tonelloiago.localdrive.watcher.watcher.annotation.Watcher;
import org.springframework.stereotype.Component;

@Watcher
@Component
public class GoogleDriveWatcher implements WatcherInterface {
    @Override
    public void watcher() {

    }

    @Override
    public Event buildEvent(EventKind eventKind, Object filePath) {
        return null;
    }
}
