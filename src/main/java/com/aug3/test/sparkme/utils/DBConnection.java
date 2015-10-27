package com.aug3.test.sparkme.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import scala.runtime.AbstractFunction0;

@SuppressWarnings("serial")
public class DBConnection extends AbstractFunction0<Connection> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(DBConnection.class);

	private String driverClassName;
	private String connectionUrl;
	private String userName;
	private String password;

	public DBConnection(String driverClassName, String connectionUrl, String userName, String password) {
		this.driverClassName = driverClassName;
		this.connectionUrl = connectionUrl;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public Connection apply() {
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			LOGGER.error("Failed to load driver class", e);
		}

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(connectionUrl, userName, password);
		} catch (SQLException e) {
			LOGGER.error("Connection failed", e);
		}

		return connection;
	}
}
