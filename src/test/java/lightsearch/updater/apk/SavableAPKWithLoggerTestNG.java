package lightsearch.updater.apk;

import org.testng.annotations.Test;

public class SavableAPKWithLoggerTestNG {

    @Test
    public void save_withoutException() {
        new SavableAPKWithLogger(new SavableTestAPK()).save();
    }

    @Test
    public void save_withException() {
        new SavableAPKWithLogger(new SavableTestAPKFailed()).save();
    }
}
