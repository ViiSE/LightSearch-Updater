package lightsearch.updater.producer.apk;

import lightsearch.updater.LightSearchUpdater;
import lightsearch.updater.apk.APK;
import lightsearch.updater.apk.SavableAPK;
import lightsearch.updater.apk.SavableAPKFile;
import lightsearch.updater.apk.SimpleAPK;
import lightsearch.updater.os.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.InputStream;

import static org.testng.Assert.assertTrue;

@SpringBootTest(classes = LightSearchUpdater.class)
public class APKProducerDefaultIntegrationTestNG extends AbstractTestNGSpringContextTests {

    @Autowired
    private APKProducer apkProducer;

    @Autowired
    @Qualifier("releasesDirectoryDefault")
    private Directory<String> directory;

    @Test
    @Parameters({"name", "version"})
    public void getSimpleAPKInstance(String name, String version) {
        APK apk = apkProducer.getSimpleAPKInstance(name, version);
        assertTrue(apk instanceof SimpleAPK);
    }

    @Test
    @Parameters({"name", "version"})
    public void getSavableAPKFileInstance(String name, String version) {
        SavableAPK apk = apkProducer.getSavableAPKFileInstance(
                apkProducer.getSimpleAPKInstance(name, version),
                InputStream.nullInputStream(),
                directory);
        assertTrue(apk instanceof SavableAPKFile);
    }

    @Test
    @Parameters({"name", "version"})
    public void getSavableAPKWithLoggerInstance(String name, String version) {
        apkProducer.getSavableAPKWithLoggerInstance(
                apkProducer.getSavableAPKFileInstance(
                        apkProducer.getSimpleAPKInstance(name, version),
                        InputStream.nullInputStream(),
                        directory));
    }
}
