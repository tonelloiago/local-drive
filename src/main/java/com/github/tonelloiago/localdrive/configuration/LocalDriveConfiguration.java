package com.github.tonelloiago.localdrive.configuration;

import com.github.tonelloiago.localdrive.exception.LocalDriveBaseException;

public class LocalDriveConfiguration {

    private final LocalDrive localDrive;
    private final FilesHandler filesHandler;

    public LocalDriveConfiguration() {
        this.localDrive = new LocalDrive();
        this.filesHandler = new FilesHandler();
        this.configureBaseFolder();
    }

    public LocalDrive getLocalDrive() {
        return localDrive;
    }

    private void configureBaseFolder() {
        try {
            if(filesHandler.exists(this.localDrive.getBasePathAsPath())) {
                return;
            }
            filesHandler.createDirectory(this.localDrive.getBasePathAsPath());
        }catch (Exception e) {
            System.err.println("ERROR: Cannot access directory specified in application.yaml");
            throw new LocalDriveBaseException();
        }
    }

    public void run() {

    }
}
