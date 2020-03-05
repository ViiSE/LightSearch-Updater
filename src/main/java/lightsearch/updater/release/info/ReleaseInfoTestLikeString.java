package lightsearch.updater.release.info;

public class ReleaseInfoTestLikeString implements ReleaseInfo<String> {

    @Override
    public String upload() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ignored) {}

        return "{\"param\":\"value\"}";
    }

    @Override
    public void save(String newContent) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ignored) {}
    }
}
