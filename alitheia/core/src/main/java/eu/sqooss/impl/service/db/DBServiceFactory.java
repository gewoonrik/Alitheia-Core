package eu.sqooss.impl.service.db;

import eu.sqooss.service.db.DBService;
import org.osgi.framework.BundleContext;

public class DBServiceFactory {

    private static final String DB = "eu.sqooss.db";
    private final BundleContext bc;

    public DBServiceFactory(BundleContext bc)   {
        this.bc = bc;
    }

    public DBService getDBService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String impl = bc.getProperty(DB);
        Class clazz = Thread.currentThread().getContextClassLoader().loadClass(impl);

        DBService dbService = (DBService) clazz.newInstance();
        return dbService;
    }
}
