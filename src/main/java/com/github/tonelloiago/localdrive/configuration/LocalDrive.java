package com.github.tonelloiago.localdrive.configuration;

import com.github.tonelloiago.localdrive.exception.LocalDriveBaseException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class LocalDrive implements Serializable {

    private static final String BASE_PATH = "/drive";
    private static final String USER_HOME = "user.home";

    public LocalDrive() {
        setUserConfigurations();
    }

    private void setUserConfigurations() {
        var classLoader = LocalDriveConfiguration.class.getClassLoader();
        var inputStream = classLoader.getResourceAsStream("application.yaml");

        if(Objects.isNull(inputStream)) {
            System.err.println("ERROR: Cannot find application.yaml");
            throw new LocalDriveBaseException();
        }
    }

    public Path getBasePathAsPath() {
        var homeDirectory = getHomeDirectory();
        return Paths.get(homeDirectory + BASE_PATH);
    }

    private String getHomeDirectory() {
        try {
            return System.getProperty(USER_HOME);
        } catch (Exception e) {
            System.err.println("ERROR: Cannot access user's home directory");
            throw new LocalDriveBaseException();
        }
    }

}
