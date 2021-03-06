package eu.sqooss.service.cache;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCache extends BaseCacheServiceImpl {

    ConcurrentHashMap<String, byte[]> cache = new ConcurrentHashMap<String, byte[]>(1024);
    
    public InMemoryCache() {}

    @Override
    public boolean startUp() {
        return true;
    }

    @Override
    public void shutDown() {

    }

    @Override
    public byte[] get(String key) {
        return cache.get(key);
    }

    @Override
    public void set(String key, byte[] data) {
        cache.put(key, data);
    }
}
