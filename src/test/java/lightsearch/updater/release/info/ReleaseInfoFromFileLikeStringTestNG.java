package lightsearch.updater.release.info;

import lightsearch.updater.exception.ReleaseInfoException;
import lightsearch.updater.os.CurrentDirectoryFromFileTest;
import lightsearch.updater.os.Directory;
import lightsearch.updater.os.InfoDirectoryDefault;
import lightsearch.updater.os.ReleaseInfoPathDefault;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

import static org.testng.Assert.*;

public class ReleaseInfoFromFileLikeStringTestNG {

    private ReleaseInfo<String> releaseInfo;
    private String releaseInfoDirs;

    @BeforeClass
    public void setUpClass() throws IOException {
        Directory<Path> releasesDir = new ReleaseInfoPathDefault(
                new InfoDirectoryDefault(
                        new CurrentDirectoryFromFileTest()));

        releaseInfoDirs = releasesDir.name().toString();

        releaseInfo = new ReleaseInfoFromFileLikeString(
                releasesDir,
                new ReleaseInfoCheckerJSON());

        File dirs = new File(
                new InfoDirectoryDefault(
                        new CurrentDirectoryFromFileTest()
                ).name());

        if(!dirs.mkdirs()) {
            if(!dirs.exists())
                throw new RuntimeException("Cannot create dir " + dirs.getAbsolutePath());
        }

        File updateInfo = new File(releaseInfoDirs);
        if(!updateInfo.exists())
            if(!updateInfo.createNewFile()) {
                throw new RuntimeException("Cannot create file " + releaseInfoDirs);
            }
    }

    @Test(priority = 1)
    @Parameters({"infoContent"})
    public void save(String infoContent) throws ReleaseInfoException {
        releaseInfo.save(infoContent);
        System.out.println("Success!");
    }

    @Test(priority = 2)
    @Parameters({"infoContent"})
    public void upload(String infoContent) throws ReleaseInfoException {
        String content = releaseInfo.upload().replaceAll("\\n", "");
        assertNotNull(content, "Content of release info is null!");
        assertEquals(content, infoContent);
        System.out.println("Success!");
    }

    @AfterClass
    public void teardownClass() {
        if(deleteDirectory(new File(releaseInfoDirs)))
            System.out.println("Success!");
        else
            throw new RuntimeException("Cannot delete dir '" + releaseInfoDirs + "'");
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
