package com.github.tonelloiago.localdrive.manager.annotation;

import com.github.tonelloiago.localdrive.domain.Event;
import com.github.tonelloiago.localdrive.domain.EventKind;

public interface EventManager<T> {
    Event createFile();
    Event deleteFile();
    Event updateFile();
    Event buildEvent(EventKind eventKind, T filePath);
}
