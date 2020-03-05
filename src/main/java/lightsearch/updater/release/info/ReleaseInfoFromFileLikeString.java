package lightsearch.updater.release.info;

import lightsearch.updater.exception.ReleaseInfoException;
import lightsearch.updater.os.Directory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

@Component("releaseInfoFromFileLikeString")
public class ReleaseInfoFromFileLikeString implements ReleaseInfo<String> {

    private final Directory<Path> releaseInfoPath;
    private final ReleaseInfoChecker releaseInfoChecker;

    public ReleaseInfoFromFileLikeString(Directory<Path> releaseInfoPath, ReleaseInfoChecker releaseInfoChecker) {
        this.releaseInfoPath = releaseInfoPath;
        this.releaseInfoChecker = releaseInfoChecker;
    }

    @Override
    public String upload() throws ReleaseInfoException {
        Path path = releaseInfoPath.name();
        if(Files.exists(path)) {
            try {
                StringBuilder info = new StringBuilder();
                Files.readAllLines(path).forEach(line -> info.append(line).append("\n"));

                return info.toString();
            } catch (IOException ex) {
                throw new ReleaseInfoException(ex.getMessage());
            }
        } else {
            throw new ReleaseInfoException("Cannot upload release info: file is not exist");
        }
    }

    @Override
    synchronized public void save(String newContent) throws ReleaseInfoException {
        try {
            releaseInfoChecker.check(newContent);
            Path infoPath = releaseInfoPath.name();
            Files.write(infoPath, new ArrayList<String>() {{ add(newContent); }}, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new ReleaseInfoException("Error writing to file: " + ex.getMessage());
        }
    }
}
