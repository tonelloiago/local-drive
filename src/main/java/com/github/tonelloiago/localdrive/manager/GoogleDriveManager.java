package com.github.tonelloiago.localdrive.manager;

import com.github.tonelloiago.localdrive.domain.Event;
import com.github.tonelloiago.localdrive.manager.annotation.Manager;
import org.springframework.stereotype.Component;

@Component
@Manager
public class GoogleDriveManager implements EventManager{
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
