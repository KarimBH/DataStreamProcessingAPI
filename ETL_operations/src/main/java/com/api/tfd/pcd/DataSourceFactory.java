package com.api.tfd.pcd;

import org.apache.commons.dbcp.BasicDataSource;

public class DataSourceFactory extends BasicDataSource {

	/**
	 * Constructor. Sets the properties of the BasicDataSource object obtained
	 * 
	 * @param DriverClassName
	 *            ex: "oracle.jdbc.OracleDriver"
	 * @param dbUrl
	 *            ex : "jdbc:oracle:thin://host:port:DBName"
	 * @param username
	 * @param password
	 */

	public DataSourceFactory(String DriverClassName, String dbUrl,
			String username, String password) {

		this.setDriverClassName(DriverClassName);
		this.setUrl(dbUrl);
		this.setUsername(username);
		this.setPassword(password);
	}

	/**
	 * Constructor. Takes a DBproperties object and sets the BasicDataSource
	 * object properties
	 * 
	 * @param dbprop
	 */
	public DataSourceFactory(DBProperties dbprop) {

		this.setDriverClassName(dbprop.getDriverClassName());
		this.setUrl(dbprop.getDBUrl());
		this.setUsername(dbprop.getUsername());
		this.setPassword(dbprop.getPassword());
	}
}
