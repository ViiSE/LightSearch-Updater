package lightsearch.updater.os;

import lightsearch.updater.apk.APK;
import lightsearch.updater.apk.SimpleAPK;
import lightsearch.updater.exception.StorageException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class DefaultStorageTestNG {

    private final Storage storage = new DefaultStorage();

    @Test(dataProvider = "storageDP", priority = 1)
    public void save(String key, Object value, Class<?> ignored) {
        storage.save(key, value);
    }

    @Test(dataProvider = "storageDP", priority = 2)
    public void load(String key, Object expectedObject, Class<?> clazz) throws StorageException {
        Object actualObject = storage.load(key, clazz);
        System.out.println(actualObject);
        if(!expectedObject.equals(actualObject))
            if(!clazz.isInstance(actualObject))
                throw new RuntimeException("Not expected object! expected - " + expectedObject + ", actual - " + actualObject);
    }

    @Test(dataProvider = "loadStorageDPFailed", priority = 3, expectedExceptions = StorageException.class)
    public void load_object_not_found(String key, Class<?> clazz) throws StorageException {
        storage.load(key, clazz);
    }

    @DataProvider(name = "storageDP")
    public Object[][] createSaveStorageDP() {
        return new Object[][] {
                {"string", "Example", String.class},
                {"inputStream", InputStream.nullInputStream(), InputStream.class},
                {"listOfStrings", new ArrayList<String>() {{ add("Item1"); }}, List.class},
                {"apk", new SimpleAPK("app-release.apk", "1.0"), APK.class}};
    }

    @DataProvider(name = "loadStorageDPFailed")
    public Object[][] createLoadStorageDPFailed() {
        return new Object[][] {
                {"fake", Buffer.class}};
    }
}
