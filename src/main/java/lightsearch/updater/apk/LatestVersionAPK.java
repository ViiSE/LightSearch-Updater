package lightsearch.updater.apk;

import lightsearch.updater.exception.APKException;

public interface LatestVersionAPK<T> {
    long size();
    T as() throws APKException;
}
