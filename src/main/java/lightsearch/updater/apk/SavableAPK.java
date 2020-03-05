package lightsearch.updater.apk;

import lightsearch.updater.exception.APKException;

public interface SavableAPK extends APK {
    void save() throws APKException;
}
