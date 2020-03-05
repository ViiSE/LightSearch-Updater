package lightsearch.updater.apk;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("simpleAPK")
@Scope("prototype")
public class SimpleAPK implements APK {

    private final String name;
    private final String version;

    public SimpleAPK(String name, String version) {
        this.name = name;
        this.version = version;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String version() {
        return version;
    }
}
