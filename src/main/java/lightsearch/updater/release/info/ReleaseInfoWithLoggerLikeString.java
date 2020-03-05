package lightsearch.updater.release.info;

import lightsearch.updater.exception.ReleaseInfoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("releaseInfoWithLoggerLikeString")
public class ReleaseInfoWithLoggerLikeString implements ReleaseInfo<String> {

    private final Logger logger = LoggerFactory.getLogger(ReleaseInfoWithLoggerLikeString.class);
    private final ReleaseInfo<String> releaseInfo;

    public ReleaseInfoWithLoggerLikeString(@Qualifier("releaseInfoFromFileLikeString") ReleaseInfo<String> releaseInfo) {
        this.releaseInfo = releaseInfo;
    }

    @Override
    public String upload() throws ReleaseInfoException {
        try {
            String info = releaseInfo.upload();
            logger.info("Release info was uploaded");
            return info;
        } catch (ReleaseInfoException ex) {
            logger.error("upload: " + ex.getMessage());
            throw new ReleaseInfoException(ex.getMessage());
        }
    }

    @Override
    public void save(String newContent) throws ReleaseInfoException {
        try {
            releaseInfo.save(newContent);
            logger.info("Release info was saved");
        } catch (ReleaseInfoException ex) {
            logger.error("save: " + ex.getMessage());
            throw new ReleaseInfoException(ex.getMessage());
        }
    }
}
