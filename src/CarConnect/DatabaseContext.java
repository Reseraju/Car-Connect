package CarConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseContext {
	
	public Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/hexaware";
		String username = "root";
		String password = "Apple@99l3123";
		return DriverManager.getConnection(url, username, password);
	}
}
