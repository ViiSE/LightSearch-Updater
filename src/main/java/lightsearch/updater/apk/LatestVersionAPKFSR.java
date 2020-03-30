package lightsearch.updater.apk;

import com.fasterxml.jackson.databind.ObjectMapper;
import lightsearch.updater.data.LatestVersionDTO;
import lightsearch.updater.exception.APKException;
import lightsearch.updater.exception.ReleaseInfoException;
import lightsearch.updater.os.Directory;
import lightsearch.updater.release.info.ReleaseInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component("latestVersionAPKFSR")
public class LatestVersionAPKFSR implements LatestVersionAPK<FileSystemResource> {

    private final ObjectMapper mapper;
    private final ReleaseInfo<String> releaseInfo;
    private final Directory<String> releasesDir;

    private long size;

    public LatestVersionAPKFSR(
            ObjectMapper mapper,
            @Qualifier("releaseInfoFromFileLikeString") ReleaseInfo<String> releaseInfo,
            @Qualifier("releasesDirectoryDefault") Directory<String> releasesDir) {
        this.mapper = mapper;
        this.releaseInfo = releaseInfo;
        this.releasesDir = releasesDir;
    }

    @Override
    public long size() {
        if(size == 0L) {
            try {
                size = getAPKFile().length();
                return size;
            } catch (ReleaseInfoException | IOException e) {
                e.printStackTrace();
            }
        }
        return size;
    }

    @Override
    public FileSystemResource as() throws APKException {
        try {
            return new FileSystemResource(getAPKFile());
        } catch (IOException | ReleaseInfoException e) {
            throw new APKException(e.getMessage());
        }
    }

    private File getAPKFile() throws ReleaseInfoException, IOException {
        LatestVersionDTO lVerDTO = mapper.readValue(releaseInfo.upload(), LatestVersionDTO.class);
        String fullName = releasesDir.name() + "v" + lVerDTO.getLatestVersion() + File.separator + "app-release.apk";
        return new File(fullName);
    }
}
