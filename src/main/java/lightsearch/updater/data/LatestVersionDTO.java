package lightsearch.updater.data;

import java.util.List;

public class LatestVersionDTO {

    private String latestVersion;
    private int latestVersionCode;
    private String url;
    private List<String> releaseNotes;

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }

    public int getLatestVersionCode() {
        return latestVersionCode;
    }

    public void setLatestVersionCode(int latestVersionCode) {
        this.latestVersionCode = latestVersionCode;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setReleaseNotes(List<String> releaseNotes) {
        this.releaseNotes = releaseNotes;
    }

    public List<String> getReleaseNotes() {
        return releaseNotes;
    }
}