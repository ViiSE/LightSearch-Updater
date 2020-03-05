package lightsearch.updater.apk;

import lightsearch.updater.exception.APKException;
import lightsearch.updater.os.CurrentDirectoryFromFileTest;
import lightsearch.updater.os.ReleasesDirectoryDefault;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Objects;

import static org.testng.Assert.assertTrue;

public class SavableAPKFileTestNG {

    private SavableAPK apk;
    private String apkDirs;

    @BeforeClass
    @Parameters({"apkName", "version", "content"})
    public void setUpClass(String name, String version, String content) {
        byte[] bContent = content.getBytes();

        apkDirs = new ReleasesDirectoryDefault(new CurrentDirectoryFromFileTest())
                .name() +
                File.separator +
                version +
                File.separator;

        apk = new SavableAPKFile(
                new SimpleAPK(name, version),
                new ByteArrayInputStream(bContent),
                new ReleasesDirectoryDefault(
                        new CurrentDirectoryFromFileTest()));

        File dirs = new File(apkDirs);
        if(!dirs.mkdirs()) {
            if(!dirs.exists())
                throw new RuntimeException("Cannot create dir " + apkDirs);
        }
    }

    @Test
    public void save() throws APKException {
        apk.save();
        File apkFile = new File(apkDirs + apk.name());
        assertTrue(apkFile.exists(), "File is not created!");
    }

    @AfterClass
    public void teardownClass() {
        if(deleteDirectory(new File(apkDirs)))
            System.out.println("Success!");
        else
            throw new RuntimeException("Cannot delete dir '" + apkDirs + "'");
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
