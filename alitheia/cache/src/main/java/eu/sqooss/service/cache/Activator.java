package eu.sqooss.service.cache;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import eu.sqooss.core.AlitheiaCore;

public class Activator implements BundleActivator {

    public static final String CACHE_IMPL_DEFAULT = "eu.sqooss.service.cache.OnDiskCache";
    public static final String CACHE_IMPL_KEY = "eu.sqooss.service.cache.impl";

    /**
     * Registers CacheService implementation. If a system property with key
     * CACHE_IMPL_KEY exists, the value is used as class. Otherwise,
     * CACHE_IMPL_DFAULT is used.
     *
     * @param bc
     * @throws Exception
     */
    public void start(BundleContext bc) throws Exception {
        String impl = bc.getProperty(CACHE_IMPL_KEY);

        if (impl == null) {
            impl = CACHE_IMPL_DEFAULT;
        }

        Class clazz = Thread.currentThread().getContextClassLoader().loadClass(impl);

        AlitheiaCore.getInstance().registerService(CacheService.class, clazz);
    }
  
    public void stop(BundleContext bc) throws Exception {
        AlitheiaCore.getInstance().unregisterService(CacheService.class);
    }
}
