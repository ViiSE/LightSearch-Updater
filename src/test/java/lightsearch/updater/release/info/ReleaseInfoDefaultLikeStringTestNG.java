package lightsearch.updater.release.info;

import lightsearch.updater.exception.ReleaseInfoException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class ReleaseInfoDefaultLikeStringTestNG {

    private ReleaseInfo<String> releaseInfo;

    @BeforeClass
    public void setUpClass() {
        releaseInfo = new ReleaseInfoDefaultLikeString();
    }

    @Test
    public void upload() throws ReleaseInfoException {
        String content = releaseInfo.upload();
        assertNotNull(content, "Content of release info is null!");
        System.out.println("Success!");
    }

    @Test(expectedExceptions = ReleaseInfoException.class, expectedExceptionsMessageRegExp = "Cannot save in this implement")
    public void save() throws ReleaseInfoException {
        releaseInfo.save("some content");
    }
}
