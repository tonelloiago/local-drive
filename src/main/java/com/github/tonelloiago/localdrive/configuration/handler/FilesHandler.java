package com.github.tonelloiago.localdrive.configuration.handler;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FilesHandler {

    private static final String USER_HOME = "user.home";

    public boolean exists(Path path) {
        return Files.exists(path);
    }

    public void createDirectory(Path path) throws IOException {
        Files.createDirectory(path);
    }

    public String getHomeDirectory() {
        return System.getProperty(USER_HOME);
    }
}
