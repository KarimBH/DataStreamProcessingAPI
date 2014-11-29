package com.api.tfd.pcd;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

@SuppressWarnings("serial")
public class DBProperties extends java.util.Properties{

	/**
	 * Contains the database properties passed in arguments to the constructor or loaded 
	 * from a file in the file system or in the class path 
	 */
	private String host;
	private String port;
	private String dbName;
	private String password;
	private String username;
	private String driverClassName;
	private String jdbcType;
	
/**
 * Constructor 
 * @param host ip address of the database
 * @param port 4 digit number
 * @param dbName Name of the database 
 * @param username DB User id
 * @param password Password of the DB user
 */
	void DBproperties(String hote, String port, String nomDB,
			String utilisateur, String motDePasse, String jdbcType,String driverClassName) {
		hote = this.host;
		port = this.port;
		nomDB = this.dbName;
		utilisateur = this.username;
		motDePasse = this.password;
		jdbcType = this.jdbcType;
		driverClassName = this.driverClassName;
	}
/**
 * Load the .properties file from the file system and sets the DBproperties class attributs.
 * @param propertiesFilePath
 */
	public void loadPropertiesFileFromFileSystem(String propertiesFilePath) {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(propertiesFilePath);

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			host = prop.getProperty("host");
			port = prop.getProperty("port");
			dbName = prop.getProperty("DBName");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			jdbcType = prop.getProperty("jdbcType");
			driverClassName = prop.getProperty("driverClassName");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
/**
 * Load the .properties file contained in the class Path and sets the DBproperties class attributs.
 * @param classPath
 */
	public void loadPropertiesFileFromClassPath(String classPath) {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = DBProperties.class.getClassLoader().getResourceAsStream(
					classPath);
			if (input == null) {
				System.out.println("Sorry, unable to find " + classPath);
				return;
			}

			// load a properties file from class path, inside static method
			prop.load(input);

			// get the property value and print it out
			host = prop.getProperty("host");
			port = prop.getProperty("port");
			dbName = prop.getProperty("DBName");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			jdbcType = prop.getProperty("jdbcType");
			driverClassName = prop.getProperty("driverClassName");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	/**
	 * Save a properties class in a file.
	 * @param filePath Path of the file to put the parameters in.
	 * @prop properties class to save
	 * @comments [optional] comments saved in the begin of the file
	 */
	public void writePropertiesFileF(String filePath, Properties prop, String comments) {
		OutputStream output = null;
	 
		try {
	 
			output = new FileOutputStream(filePath);
			prop.store(output, comments);
			output.close();
	 
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	 
		}
	}
	
	
	public String getDBUrl(){
		return jdbcType + "://" + host + ":" + port + ":" + dbName;
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getJdbcType() {
		return jdbcType;
	}
	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}
	
}

