package eu.sqooss.impl.service.db;

import org.junit.Test;
import org.osgi.framework.BundleContext;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DBServiceFactoryTest {

    BundleContext bc = mock(BundleContext.class);

    @Test
    public void testGetMysqlDBServiceImpl() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        when(bc.getProperty("eu.sqooss.db")).thenReturn("eu.sqooss.impl.service.db.MySQLDBServiceImpl");
        DBServiceFactory dbServiceFactory = new DBServiceFactory(bc);
        assertThat(dbServiceFactory.getDBService(), instanceOf(MySQLDBServiceImpl.class));
    }

    @Test
      public void testGetPostgresDBServiceImpl() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        when(bc.getProperty("eu.sqooss.db")).thenReturn("eu.sqooss.impl.service.db.PostgresDBServiceImpl");
        DBServiceFactory dbServiceFactory = new DBServiceFactory(bc);
        assertThat(dbServiceFactory.getDBService(), instanceOf(PostgresDBServiceImpl.class));
    }

    @Test
    public void testGetHSQLDBServiceImpl() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        when(bc.getProperty("eu.sqooss.db")).thenReturn("eu.sqooss.impl.service.db.HSQLDBServiceImpl");
        DBServiceFactory dbServiceFactory = new DBServiceFactory(bc);
        assertThat(dbServiceFactory.getDBService(), instanceOf(HSQLDBServiceImpl.class));
    }

    @Test
    public void testGetH2DBServiceImpl() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        when(bc.getProperty("eu.sqooss.db")).thenReturn("eu.sqooss.impl.service.db.H2DBServiceImpl");
        DBServiceFactory dbServiceFactory = new DBServiceFactory(bc);
        assertThat(dbServiceFactory.getDBService(), instanceOf(H2DBServiceImpl.class));
    }
}
