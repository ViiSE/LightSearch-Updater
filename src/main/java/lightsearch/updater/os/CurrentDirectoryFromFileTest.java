package lightsearch.updater.os;

import java.io.File;

public class CurrentDirectoryFromFileTest implements Directory<String> {

    @Override
    public String name() {
        return System.getProperty("user.dir") + File.separator + ResourcesFilesPath.getResourcesFilesPath();
    }

    private static class ResourcesFilesPath {

        private static final String oldResourcesFilePath = levelUp() + levelUp() +
                dir("src") + dir("test") + dir("resources");

        private static final String resourcesFilesPath =
                dir("src") + dir("test") + dir("resources");

        public static String getResourcesFilesPath() {
            return resourcesFilesPath;
        }

        public static String getOldResourcesFilePath() { return oldResourcesFilePath; }

        private static String levelUp() {
            return ".." + File.separator;
        }

        private static String dir(String dirName) {
            return dirName + File.separator;
        }
    }
}
