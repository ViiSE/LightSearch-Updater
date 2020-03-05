package lightsearch.updater.release.info;

import lightsearch.updater.exception.ReleaseInfoException;

public interface ReleaseInfo<T> {
    T upload() throws ReleaseInfoException;
    void save(T newContent) throws ReleaseInfoException;
}
