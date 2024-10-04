package CarConnect;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlConnection {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DatabaseContext dbContext = new DatabaseContext();
		// Load the driver class
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// Establish the connection
		Connection connection = dbContext.getConnection();
		
		// Create statement object
		Statement statement = connection.createStatement();
		
		// Write sql queries
		String sql = "INSERT INTO customer (CustomerID, FirstName, LastName, Email, PhoneNumber, Username, Password) "
				+ "VALUES(1, 'Rese', 'Raju', 'reeseraju@gmail.com', '1234567890', 'Reeseraju', 'rese123')";
		
		// Execute Query
		int x = statement.executeUpdate(sql);
		System.out.println(x + " row(s) Inserted");
	}
}
