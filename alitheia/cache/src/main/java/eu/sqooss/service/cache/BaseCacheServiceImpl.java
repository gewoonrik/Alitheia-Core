package eu.sqooss.service.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import eu.sqooss.service.logging.Logger;
import org.osgi.framework.BundleContext;

public abstract class BaseCacheServiceImpl implements CacheService {

    private BundleContext bundleContext;
    private Logger logger;

    @Override
    public void setInitParams(BundleContext bundleContext, Logger logger) {
        this.bundleContext = bundleContext;
        this.logger = logger;
    }

    @Override
    public InputStream getStream(String key) {
        byte[] buff = get(key);
        
        if (buff == null) {
            return null;
        }
        
        ByteArrayInputStream bais = new ByteArrayInputStream(buff);
        return bais;
    }

    @Override
    public void setStream(String key, InputStream in) {
        try {
            int nRead;
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[4096];

            while ((nRead = in.read(data, 0, data.length)) != -1) {
              buffer.write(data, 0, nRead);
            }

            buffer.flush();            
            set(key, buffer.toByteArray());
            
        } catch (IOException e) {
            logger.error("Error");
        }
    }

    protected Logger getLogger() {
        return logger;
    }
}
