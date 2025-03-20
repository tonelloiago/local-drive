package com.github.tonelloiago.localdrive.manager;

import com.github.tonelloiago.localdrive.domain.Event;
import com.github.tonelloiago.localdrive.domain.EventKind;
import com.github.tonelloiago.localdrive.manager.annotation.EventManager;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@Manager
public class LocalFolderManager implements EventManager<Path> {
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

    @Override
    public Event buildEvent(EventKind eventKind, Path filePath) {
        return null;
    }
}
