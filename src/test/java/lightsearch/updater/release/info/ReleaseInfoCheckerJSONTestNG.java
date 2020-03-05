package lightsearch.updater.release.info;

import lightsearch.updater.exception.ReleaseInfoException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ReleaseInfoCheckerJSONTestNG {

    private ReleaseInfoChecker checker;

    @BeforeClass
    public void setUpClass() {
        checker = new ReleaseInfoCheckerJSON();
    }

    @Test
    public void check() throws ReleaseInfoException {
        checker.check(getJSON());
        System.out.println("Success!");
    }

    @Test(expectedExceptions = ReleaseInfoException.class)
    public void check_invalidJson() throws ReleaseInfoException {
        checker.check(getInvalidJSON());
    }

    private String getJSON() {
        return "{" +
                    "\"paramStr\":\"string\"," +
                    "\"paramInt\":\"int\"," +
                    "\"array\":[" +
                        "\"value1\"," +
                        "\"value2\"" +
                    "]," +
                    "\"obj\":{" +
                        "\"paramStr\":\"string\"" +
                    "}" +
                "}";
    }

    private String getInvalidJSON() {
        return
                "\"paramStr\":\"string\"," +
                "\"paramInt\":\"int\"," +
                "\"array\":[" +
                    "\"value1\"," +
                    "\"value2\"" +
                "]," +
                "\"obj\":{" +
                    "\"paramStr\":\"string\"" +
                "}" +
            "}";
    }
}
