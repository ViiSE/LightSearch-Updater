package lightsearch.updater.apk;

import lightsearch.updater.exception.APKException;
import lightsearch.updater.os.CurrentDirectoryFromFileTest;
import lightsearch.updater.os.ReleasesDirectoryDefault;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.testng.Assert.assertEquals;

public class APKVersionsUploaderDefaultTestNG {

    private APKVersionsUploader vUploader;
    private List<String> expectedVersions;

    @BeforeClass
    public void setUpClass() throws APKException {
        APKVersionCreator apkVersionCreator =
                new APKVersionCreatorDefault(
                        new ReleasesDirectoryDefault(
                                new CurrentDirectoryFromFileTest()));
        apkVersionCreator.createNewVersion("1.0");
        apkVersionCreator.createNewVersion("2.0");
        apkVersionCreator.createNewVersion("3.0");
        apkVersionCreator.createNewVersion("4.0");
        apkVersionCreator.createNewVersion("5.0");

        expectedVersions = new ArrayList<>() {{
            add("1.0");
            add("2.0");
            add("3.0");
            add("4.0");
            add("5.0");
        }};

        vUploader = new APKVersionsUploaderDefault(
                new ReleasesDirectoryDefault(
                        new CurrentDirectoryFromFileTest()));
    }

    @Test
    public void uploadAll() {
        List<String> actualVersions = new ArrayList<>(vUploader.uploadAll());
        for (int i = 0; i < expectedVersions.size(); i++) {
            String expectedV = expectedVersions.get(i);
            String actualV = actualVersions.get(i);
            assertEquals(expectedV, actualV);
        }
    }

    @AfterClass
    public void teardownClass() {
        String dir = new ReleasesDirectoryDefault(
                new CurrentDirectoryFromFileTest())
                .name();

        for (String version : expectedVersions) {
            String vDir = dir + version;
            if(deleteDirectory(new File(vDir)))
                System.out.println("Success!");
            else
                throw new RuntimeException("Cannot delete dir '" + vDir + "'");
        }
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
