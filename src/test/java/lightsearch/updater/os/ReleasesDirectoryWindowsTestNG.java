package lightsearch.updater.os;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertEquals;

public class ReleasesDirectoryWindowsTestNG {

    private Directory<String> releasesDir;
    private String expectedReleasesDir;

    @BeforeClass
    public void setUpClass() {
        releasesDir = new ReleasesDirectoryWindows(new CurrentDirectoryFromFile());
        expectedReleasesDir = "file:" + File.separator + File.separator + File.separator +
                System.getProperty("user.dir") + File.separator + "update" + File.separator + "releases" + File.separator;
    }

    @Test
    public void name() {
        String actualReleasesDir = releasesDir.name();
        System.out.println("Releases directory: " + actualReleasesDir);
        assertEquals(actualReleasesDir, expectedReleasesDir);
    }
}
