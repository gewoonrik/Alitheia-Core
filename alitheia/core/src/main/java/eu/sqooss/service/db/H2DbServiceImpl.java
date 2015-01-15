package eu.sqooss.service.db;

import eu.sqooss.impl.service.db.BaseDBServiceImpl;

public class H2DbServiceImpl extends BaseDBServiceImpl {

	@Override
	protected String getConnectionString() {
		return "jdbc:h2:<SCHEMA>";
	}

	@Override
	protected String getDriver() {
		return "org.h2.Driver";
	}

	@Override
	protected String getHbmDialect() {
		return "org.h2.Driver";
	}

}
