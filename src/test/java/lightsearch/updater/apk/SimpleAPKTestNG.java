package lightsearch.updater.apk;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class SimpleAPKTestNG {

    private APK apk;

    @BeforeClass
    @Parameters({"apkName", "version"})
    public void setUpClass(String apkName, String version) {
        apk = new SimpleAPK(apkName, version);
    }

    @Test
    @Parameters({"version"})
    public void version(String version) {
        String apkVer = apk.version();
        assertNotNull(apkVer, "APK version is null!");
        assertEquals(apkVer, version);
    }

    @Test
    @Parameters({"apkName"})
    public void name(String name) {
        String apkName = apk.name();
        assertNotNull(apkName, "APK name is null!");
        assertEquals(name, apkName);
    }
}
