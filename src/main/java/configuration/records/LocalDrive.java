package configuration.records;

import java.io.Serializable;
import java.nio.file.Path;

public class LocalDrive implements Serializable {

    private String basePath;
    private Path basePathAsPath;

    public LocalDrive() {
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getBasePath() {
        return this.basePath;
    }

    public void setBasePathAsPath(Path path) {
        this.basePathAsPath = path;
    }

    public Path getBasePathAsPath() {
        return this.basePathAsPath;
    }
}
