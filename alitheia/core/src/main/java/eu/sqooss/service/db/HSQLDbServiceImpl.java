package eu.sqooss.service.db;

import eu.sqooss.impl.service.db.BaseDBServiceImpl;

public class HSQLDbServiceImpl extends BaseDBServiceImpl {

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
