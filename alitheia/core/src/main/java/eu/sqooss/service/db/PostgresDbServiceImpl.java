package eu.sqooss.service.db;

import eu.sqooss.impl.service.db.BaseDBServiceImpl;

public class PostgresDbServiceImpl extends BaseDBServiceImpl {

	@Override
	protected String getConnectionString() {
		return "jdbc:postgresql://<HOST>/<SCHEMA>";
	}

	@Override
	protected String getDriver() {
		return "org.postgresql.Driver";
	}

	@Override
	protected String getHbmDialect() {
		return "org.hibernate.dialect.PostgreSQLDialect";
	}

}
