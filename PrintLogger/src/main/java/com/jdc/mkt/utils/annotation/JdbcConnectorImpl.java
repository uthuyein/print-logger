package com.jdc.mkt.utils.annotation;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnectorImpl {

	protected static String url, user, password, driver;

	protected Connection getConnection(Class<?> configClass) throws Exception {
		if (configClass.isAnnotationPresent(Connector.class)) {
			Connector connector = configClass.getAnnotation(Connector.class);

			url = connector.url();
			user = connector.user();
			password = connector.password();
			driver = connector.driver();
			
			if (null != driver) {
				Class.forName(driver);
			}

			return DriverManager.getConnection(url, user, password);
		}
		throw new RuntimeException("Connector annotation not present on class");
	}

}
