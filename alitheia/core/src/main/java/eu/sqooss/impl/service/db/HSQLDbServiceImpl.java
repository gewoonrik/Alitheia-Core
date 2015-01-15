package eu.sqooss.impl.service.db;

import eu.sqooss.service.logging.Logger;

import java.net.URL;
import java.util.Properties;

public class HSQLDbServiceImpl extends BaseDBServiceImpl {

	public HSQLDbServiceImpl(Properties conProp, URL url, Logger l) {
		super(conProp, url, l);
	}

	public HSQLDbServiceImpl() {

	}
	@Override
	protected String getConnectionString() {
		return "jdbc:hsqldb:file:<SCHEMA>";
	}

	@Override
	protected String getDriver() {
		return "org.hsqldb.jdbcDriver";
	}

	@Override
	protected String getHbmDialect() {
		return "org.hibernate.dialect.HSQLDialect";
	}

}
