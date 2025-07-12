package com.jdc.mkt.utils.impls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.jdc.mkt.utils.annotation.JdbcConnectorImpl;

/**
 * @author MKT
 * @param url
 * @param user
 * @param password
 * @throws SQLException
 * @apiNote connection create at once and get connection object from
 *          getConnection(config) method
 * 
 */
public final class ConnectionManager extends JdbcConnectorImpl {

	private static String url;
	private static String user;
	private static String password;
	
	@SuppressWarnings("unused")
	private static String driver;

	private static ConnectionManager connectionManager;

	private ConnectionManager() {}

	
	public static ConnectionManager getConnectionManager() {
		if (null == connectionManager) {
			connectionManager = new ConnectionManager();
		}
		return connectionManager;
	}

	public void setConnection(String url, String user, String password, String driver) {
		ConnectionManager.url = url;
		ConnectionManager.user = user;
		ConnectionManager.password = password;
		ConnectionManager.driver = driver;

	}

	public Connection getConnection() throws SQLException {		
		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * @author MKT
	 * @param configClass
	 * @return connection object
	 * @throws Exception
	 * @apiNote configuration class needed and that must have connection annotation.
	 */
	public Connection getConnection(Class<?> configClass) throws Exception {
		
		var con = super.getConnection(configClass);
		
		url = JdbcConnectorImpl.url;
		user = JdbcConnectorImpl.user;
		password = JdbcConnectorImpl.password;
		driver = JdbcConnectorImpl.driver;
		
		return con;
	}

}
