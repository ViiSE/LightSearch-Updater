package lightsearch.updater.os;

import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertEquals;

public class CurrentDirectoryFromFileTestNG {

    private final String expectedCurDir = System.getProperty("user.dir") + File.separator;

    @Test
    public void name() {
        Directory<String> curDir = new CurrentDirectoryFromFile();
        String actualCurDir = curDir.name();
        System.out.println("Current directory: " + actualCurDir);
        assertEquals(expectedCurDir, actualCurDir);
    }
}
