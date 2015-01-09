package eu.sqooss.impl.service.db;

import java.net.URL;
import java.util.Properties;

import eu.sqooss.service.logging.Logger;

public class MySQLDBServiceImpl extends BaseDBServiceImpl {

	public MySQLDBServiceImpl(Properties conProp, URL url, Logger l) {
		super(conProp, url, l);
	}

	public MySQLDBServiceImpl() {

	}

	@Override
	protected String getConnectionString() {
		return "jdbc:mysql://<HOST>/<SCHEMA>?useUnicode=true&amp;connectionCollation=utf8_general_ci&amp;characterSetResults=utf8";
	}

	@Override
	protected String getDriver() {
		return "com.mysql.jdbc.Driver";
	}

	@Override
	protected String getHbmDialect() {
		return "org.hibernate.dialect.MySQLInnoDBDialect";
	}

}
