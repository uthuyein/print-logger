package com.jdc.mkt.utils.impls;

import java.sql.Connection;
import java.sql.DriverManager;

import com.jdc.mkt.utils.DatabaseType;
import com.jdc.mkt.utils.anno.Connector;

class JdbcConnectorImpl {

	/**
	 * Get a connection from the given config class.
	 * 
	 * @param configClass the class annotated with @Connector
	 * @return a Connection object
	 * @throws Exception if the connection cannot be established
	 */
	private static ConnectionData connectionData;
	private  Class<?> configClass;

	private static record ConnectionData(DatabaseType type,String className ,String url ,String user, String password) {}
	
	
	JdbcConnectorImpl(Class<?> config) {
		this.configClass = config;
		
	}

	
	protected  Connector getConnector() throws RuntimeException {
		if (configClass.isAnnotationPresent(Connector.class)) {
			Connector connector = configClass.getAnnotation(Connector.class);
			return connector;
		}
		throw new RuntimeException("Connector annotation not present on class");
		
	}

	public  Connection getConnection() throws RuntimeException {

		try {
			if (null == connectionData) {

				var connector = getConnector();
				if (connector != null) {
					var dbType = DatabaseType.valueOf(connector.value());
					var url = dbType.getUrl()  + connector.name();
					var className = dbType.getDriverClassName();
					connectionData = new ConnectionData(dbType,className,url, connector.user(), connector.password());
				}
				var driver = DriverManager.getConnection(connectionData.url(), connectionData.user(),
						connectionData.password());

				return driver;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Connector annotation not present on class");
		}
		return null;

	}

//	private static Connector getConnector() throws Exception {
//
//		Reflections reflections = new Reflections(""); // Empty string means root
//
//		Set<Class<?>> annotatedClasses = reflections.get(Scanners.TypesAnnotated.with(Connector.class).asClass());
//
//		for (Class<?> clazz : annotatedClasses) {
//			System.out.println("Found: " + clazz.getName());
//
//			if (clazz.isAnnotationPresent(Connector.class)) {
//				Connector connector = clazz.getAnnotation(Connector.class);
//
//				return connector;
//			} else {
//				System.out.println("No Connector annotation found on class: " + clazz.getName());
//			}
//		}
//		throw new RuntimeException("Connector annotation not present on class");
//	}	

}
