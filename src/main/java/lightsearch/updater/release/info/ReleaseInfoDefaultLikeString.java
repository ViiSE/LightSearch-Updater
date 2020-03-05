package lightsearch.updater.release.info;

import lightsearch.updater.exception.ReleaseInfoException;
import org.springframework.stereotype.Component;

@Component("releaseInfoDefaultLikeString")
public class ReleaseInfoDefaultLikeString implements ReleaseInfo<String> {

    @Override
    public String upload() {
        return "{\n" +
                "  \"latestVersion\": \"input_latest_version_here\",\n" +
                "  \"latestVersionCode\": \"input_latest_version_code_here\",\n" +
                "  \"url\": \"input_url_to_apk_here\",\n" +
                "  \"releaseNotes\": [\n" +
                "    \"- input\",\n" +
                "    \"- release\",\n" +
                "    \"- notes\",\n" +
                "    \"- here\"\n" +
                "  ]\n" +
                "}";
    }

    @Override
    public void save(String newContent) throws ReleaseInfoException {
        throw new ReleaseInfoException("Cannot save in this implement");
    }
}
