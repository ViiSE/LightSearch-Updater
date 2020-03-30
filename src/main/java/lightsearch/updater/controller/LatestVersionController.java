package lightsearch.updater.controller;

import lightsearch.updater.apk.LatestVersionAPK;
import lightsearch.updater.exception.APKException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LatestVersionController {

    private final LatestVersionAPK<FileSystemResource> latestAPK;

    public LatestVersionController(LatestVersionAPK<FileSystemResource> latestAPK) {
        this.latestAPK = latestAPK;
    }

    @GetMapping("app/versions/latest")
    public ResponseEntity<Resource> getLatestVersion() throws APKException {
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=app-release.apk");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(latestAPK.size())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(latestAPK.as());
    }
}
