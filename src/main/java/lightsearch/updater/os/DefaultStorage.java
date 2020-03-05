package lightsearch.updater.os;

import lightsearch.updater.exception.StorageException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("defaultStorage")
public class DefaultStorage implements Storage {

    private final Map<String, Object> mapStorage = new HashMap<>();

    @Override
    public <V> void save(String key, V v) {
        mapStorage.put(key, v);
    }

    @Override
    public <V> V load(String key, Class<V> clazz) throws StorageException {
        try {
            V v = clazz.cast(mapStorage.get(key));
            if(v == null)
                throw new StorageException("Cannot load object: key - " + key + ", class - " + clazz);
            return v;
        } catch (ClassCastException ex) {
            throw new StorageException(ex);
        }
    }
}
