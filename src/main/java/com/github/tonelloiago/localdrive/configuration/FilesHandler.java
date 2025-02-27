package com.github.tonelloiago.localdrive.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesHandler {

    public boolean exists(Path path) {
        return Files.exists(path);
    }

    public void createDirectory(Path path) throws IOException {
        Files.createDirectory(path);
    }
}
