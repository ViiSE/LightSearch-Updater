package lightsearch.updater.os;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;

public class ReleasesInfoPathDefaultTestNG {

    private Path expectedPath;
    private Directory<Path> pathDirectory;

    @BeforeClass
    public void setUpClass() {
        Directory<String> dir = new InfoDirectoryDefault(new CurrentDirectoryFromFile());
        pathDirectory = new ReleaseInfoPathDefault(dir);
        expectedPath = Paths.get(dir.name() + File.separator + "update.json");
    }

    @Test
    public void name() {
        Path actualPath = pathDirectory.name();
        System.out.println("Releases info path: " + actualPath.toString());
        assertEquals(expectedPath, actualPath);
    }
}
