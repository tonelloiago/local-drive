package configuration;

import configuration.records.LocalDrive;
import org.yaml.snakeyaml.Yaml;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class LocalDriveConfiguration {

    private LocalDrive localDrive;

    public LocalDriveConfiguration configure() {

        setUserConfigurations();
        configureBaseFolder();

        return this;
    }

    private void configureBaseFolder() {
        try {

            if(Files.exists(this.localDrive.getBasePathAsPath())) {
                return;
            }

            Files.createDirectory(this.localDrive.getBasePathAsPath());
        }catch (Exception e) {
            System.err.println("ERROR: Cannot access directory specified in application.yaml");
            System.exit(1);
        }
    }

    private String getHomeDirectory() {
        try {
            return System.getProperty("user.home");
        } catch (Exception e) {
            System.err.println("ERROR: Cannot access user's home directory");
            System.exit(1);
        }
        return "";
    }

    private void setUserConfigurations() {
        var classLoader = LocalDriveConfiguration.class.getClassLoader();
        var inputStream = classLoader.getResourceAsStream("application.yaml");

        if(Objects.isNull(inputStream)) {
            System.err.println("ERROR: Cannot find application.yaml");
            System.exit(1);
        }

        var yaml = new Yaml();
        this.localDrive = yaml.loadAs(inputStream, LocalDrive.class);
        var homeDirectory = getHomeDirectory();
        this.localDrive.setBasePathAsPath(Paths.get(homeDirectory + this.localDrive.getBasePath()));
    }

    public void run() {

    }
}
