package lightsearch.updater.os;

import lightsearch.updater.exception.StorageException;

public interface Storage {
    <V> void save(String key, V v);
    <V> V load(String key, Class<V> clazz) throws StorageException;
}
