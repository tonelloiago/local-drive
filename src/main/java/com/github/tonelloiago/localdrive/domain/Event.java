package com.github.tonelloiago.localdrive.domain;

public record Event(EventKind eventKind, String hash, byte[] content, String name) {

    public EventKind eventKind() {
        return eventKind;
    }

    public String hash() {
        return hash;
    }

    public byte[] content() {
        return content;
    }

    public String name() {
        return name;
    }
}
