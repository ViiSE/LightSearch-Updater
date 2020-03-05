package lightsearch.updater.release.info;

import lightsearch.updater.exception.ReleaseInfoException;

public class ReleaseInfoTestLikeStringFailed implements ReleaseInfo<String> {

    @Override
    public String upload() throws ReleaseInfoException {
        throw new ReleaseInfoException("Cannot upload Release info");
    }

    @Override
    public void save(String newContent) throws ReleaseInfoException {
        throw new ReleaseInfoException("Cannot save Release info with content '" + newContent + "'");
    }
}
