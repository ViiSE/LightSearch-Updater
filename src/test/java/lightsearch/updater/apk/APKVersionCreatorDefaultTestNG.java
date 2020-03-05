package lightsearch.updater.apk;

import lightsearch.updater.exception.APKException;
import lightsearch.updater.os.CurrentDirectoryFromFileTest;
import lightsearch.updater.os.ReleasesDirectoryDefault;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Objects;

public class APKVersionCreatorDefaultTestNG {

    private APKVersionCreator apkVersionCreator;

    @BeforeClass
    public void setUpClass() {
        apkVersionCreator = new APKVersionCreatorDefault(
                new ReleasesDirectoryDefault(
                        new CurrentDirectoryFromFileTest()));
    }

    @Test
    @Parameters("apkVersionName")
    public void createNewVersion(String apkVersionName) throws APKException {
        apkVersionCreator.createNewVersion(apkVersionName);
    }

    @AfterClass
    @Parameters("apkVersionName")
    public void teardownClass(String apkVer) {
        String apkVerDir = new ReleasesDirectoryDefault(
                new CurrentDirectoryFromFileTest()
        ).name() + apkVer;
        if(deleteDirectory(new File(apkVerDir)))
            System.out.println("Success!");
        else
            throw new RuntimeException("Cannot delete dir '" + apkVerDir + "'");
    }

    private boolean deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] children = Objects.requireNonNull(dir.listFiles());
            for (File child : children) {
                boolean success = deleteDirectory(child);
                if (!success) {
                    return false;
                }
            }
        }

        System.out.println("Removing file or directory : " + dir.getName());
        return dir.delete();
    }
}
