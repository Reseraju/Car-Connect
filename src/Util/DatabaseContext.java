package Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import Exception.DatabaseConnectionException;

public class DatabaseContext {
	
	public Connection getConnection() throws DatabaseConnectionException, IOException {
		try {
			Properties properties = new Properties();
			FileInputStream fis = new FileInputStream("D:\\Eclipse\\CaseStudy\\src\\Util\\db.properties");
			properties.load(fis);
			
			String url = properties.getProperty("jdbc.url");
			String username = properties.getProperty("jdbc.username");
			String password = properties.getProperty("jdbc.password");
			return DriverManager.getConnection(url, username, password);
		}
		catch (SQLException e){
			throw new DatabaseConnectionException("Failed to establish a database connection", e);
		}
	}
}
