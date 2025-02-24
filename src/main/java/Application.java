import configuration.LocalDriveConfiguration;
import configuration.watcher.WatcherUtils;

public class Application {

    private static final LocalDriveConfiguration LOCAL_DRIVE_CONFIGURATION = new LocalDriveConfiguration();

    public static void main(String[] args) {
        LOCAL_DRIVE_CONFIGURATION.configure().run();
    }

}
