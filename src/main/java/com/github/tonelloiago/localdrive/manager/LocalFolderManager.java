package com.github.tonelloiago.localdrive.manager;

import com.github.tonelloiago.localdrive.domain.Event;
import com.github.tonelloiago.localdrive.domain.EventKind;
import com.github.tonelloiago.localdrive.manager.annotation.Manager;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@Manager
public class LocalFolderManager implements EventManager {
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
