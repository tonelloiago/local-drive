package com.github.tonelloiago.localdrive.configuration.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FilesHandler {

    private static final String USER_HOME = "user.home";

    @Value("${local-drive.base-path}")
    private String basePath;

    public boolean exists(Path path) {
        return Files.exists(path);
    }

    public void createDirectory(Path path) throws IOException {
        Files.createDirectory(path);
    }

    public String getHomeDirectory() {
        return System.getProperty(USER_HOME);
    }

    public Path getFolderPath() {
        return Path.of(getHomeDirectory().concat(basePath));
    }

    public byte[] readFile(Path path) throws IOException {
        return Files.readAllBytes(path);
    }
}
