package com.github.tonelloiago.localdrive.domain;

public enum UnixAction {

    ENTRY_CREATE(EventKind.CREATE),
    ENTRY_DELETE(EventKind.DELETE),
    ENTRY_MODIFY(EventKind.UPDATE);

    private final EventKind eventKind;

    UnixAction(EventKind eventKind) {
        this.eventKind = eventKind;
    }

    public EventKind getEventKind() {
        return this.eventKind;
    }

}
