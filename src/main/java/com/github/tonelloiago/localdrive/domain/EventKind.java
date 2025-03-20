package com.github.tonelloiago.localdrive.domain;

import com.github.tonelloiago.localdrive.manager.EventManager;

import java.util.function.Consumer;


public enum EventKind {

    LIST(null),
    CREATE(EventManager::createFile),
    DELETE(EventManager::deleteFile),
    UPDATE(EventManager::updateFile);
    
    private final Consumer<EventManager> action;

    EventKind(Consumer<EventManager> action) {
        this.action = action;
    }

    public Consumer<EventManager> getAction() {
        return action;
    }
}
