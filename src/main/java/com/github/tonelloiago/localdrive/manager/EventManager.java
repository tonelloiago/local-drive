package com.github.tonelloiago.localdrive.manager;

import com.github.tonelloiago.localdrive.domain.Event;
import com.github.tonelloiago.localdrive.domain.EventKind;

public interface EventManager {
    Event createFile();
    Event deleteFile();
    Event updateFile();
}
