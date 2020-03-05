package lightsearch.updater.release.info;

import lightsearch.updater.exception.ReleaseInfoException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ReleaseInfoWithLoggerLikeStringTestNG {

    @Test
    public void upload() throws ReleaseInfoException {
        new ReleaseInfoWithLoggerLikeString(new ReleaseInfoTestLikeString()).upload();
    }

    @Test(expectedExceptions = ReleaseInfoException.class)
    public void upload_withException() throws ReleaseInfoException {
        new ReleaseInfoWithLoggerLikeString(new ReleaseInfoTestLikeStringFailed()).upload();
    }

    @Test
    @Parameters("infoContent")
    public void save(String infoContent) throws ReleaseInfoException {
        new ReleaseInfoWithLoggerLikeString(new ReleaseInfoTestLikeString()).save(infoContent);
    }

    @Test(expectedExceptions = ReleaseInfoException.class)
    @Parameters("infoContent")
    public void save_withException(String infoContent) throws ReleaseInfoException {
        new ReleaseInfoWithLoggerLikeString(new ReleaseInfoTestLikeStringFailed()).save(infoContent);
    }
}
