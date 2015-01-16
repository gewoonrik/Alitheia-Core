package eu.sqooss.impl.service.db;

import eu.sqooss.service.logging.Logger;

import java.net.URL;
import java.util.Properties;

public class H2DBServiceImpl extends BaseDBServiceImpl {

	public H2DBServiceImpl(Properties conProp, URL url, Logger l) {
		super(conProp, url, l);
	}

	public H2DBServiceImpl() {

	}
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
