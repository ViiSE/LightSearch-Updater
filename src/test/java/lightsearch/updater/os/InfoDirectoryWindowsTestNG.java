package lightsearch.updater.os;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertEquals;

public class InfoDirectoryWindowsTestNG {

    private Directory<String> infoDir;
    private String expectedInfoDir;

    @BeforeClass
    public void setUpClass() {
        infoDir = new InfoDirectoryWindows(new CurrentDirectoryFromFile());
        expectedInfoDir = "file:" + File.separator + File.separator + File.separator +
                System.getProperty("user.dir") + File.separator + "update" + File.separator + "info" + File.separator;
    }

    @Test
    public void name() {
        String actualInfoDir = infoDir.name();
        System.out.println("Info directory: " + actualInfoDir);
        assertEquals(actualInfoDir, expectedInfoDir);
    }
}
