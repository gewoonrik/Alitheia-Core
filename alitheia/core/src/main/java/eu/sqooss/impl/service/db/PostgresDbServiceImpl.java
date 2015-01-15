package eu.sqooss.impl.service.db;


import eu.sqooss.service.logging.Logger;

import java.net.URL;
import java.util.Properties;

public class PostgresDbServiceImpl extends BaseDBServiceImpl {


	public PostgresDbServiceImpl(Properties conProp, URL url, Logger l) {
		super(conProp, url, l);
	}

	public PostgresDbServiceImpl() {

	}

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
