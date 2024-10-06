package CarConnect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerService implements ICustomerService {
	private DatabaseContext dbContext = new DatabaseContext();

	@Override
	public Customer getCustomerByID(int customerID) {
		Customer customer = null;
		String sql = "SELECT CustomerID, FirstName, LastName, Email, PhoneNumber, Address, Username, RegistrationDate FROM customer WHERE CustomerID = ?";
		
		try(Connection connection = dbContext.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			ps.setInt(1, customerID);
			ResultSet resultSet = ps.executeQuery();
			
			if (resultSet.next()) {
                customer = new Customer();
                customer.setCustomerId(resultSet.getInt("CustomerID"));
                customer.setFirstName(resultSet.getString("FirstName"));
                customer.setLastName(resultSet.getString("LastName"));
                customer.setEmail(resultSet.getString("Email"));
                customer.setPhoneNumber(resultSet.getString("PhoneNumber"));
                customer.setAddress(resultSet.getString("Address"));
                customer.setUserName(resultSet.getString("Username"));
                customer.setPassword(resultSet.getString("Password"));
                customer.setRegistrationDate(resultSet.getDate("RegistrationDate"));
            }
			
			
		} catch (SQLException | DatabaseConnectionException e) {
			System.out.println("Sql Exception thrown. check sql query or connection with sql server!!");
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public Customer getCustomerByUserName(String username) {
		Customer customer = null;
		String sql = "SELECT CustomerID, FirstName, LastName, Email, PhoneNumber, Address, Username, RegistrationDate FROM customer WHERE Username = ?";
		
		try(Connection connection = dbContext.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			ps.setString(1, username);
			ResultSet resultSet = ps.executeQuery();
			
			if (resultSet.next()) {
                customer = new Customer();
                customer.setCustomerId(resultSet.getInt("CustomerID"));
                customer.setFirstName(resultSet.getString("FirstName"));
                customer.setLastName(resultSet.getString("LastName"));
                customer.setEmail(resultSet.getString("Email"));
                customer.setPhoneNumber(resultSet.getString("PhoneNumber"));
                customer.setAddress(resultSet.getString("Address"));
                customer.setUserName(resultSet.getString("Username"));
                customer.setPassword(resultSet.getString("Password"));
                customer.setRegistrationDate(resultSet.getDate("RegistrationDate"));
            }
			
			
		} catch (SQLException | DatabaseConnectionException e) {
			System.out.println("Sql Exception thrown. check sql query or connection with sql server!!");
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public boolean registerCustomer(Customer customerData) {
		String sql = "INSERT INTO customer (CustomerID, FirstName, LastName, Email, PhoneNumber, Address, Username, Password, RegistrationDate) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection connection = dbContext.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			ps.setInt(1, customerData.getCustomerId());
			ps.setString(2, customerData.getFirstName());
			ps.setString(3, customerData.getLastName());
			ps.setString(4, customerData.getEmail());
			ps.setString(5, customerData.getPhoneNumber());
			ps.setString(6, customerData.getAddress());
			ps.setString(7, customerData.getUserName());
			ps.setString(8, customerData.getPassword());
			ps.setDate(9, (Date) customerData.getRegistrationDate());
			
			
			int x = ps.executeUpdate();
			return x > 0;
		} catch (SQLException | DatabaseConnectionException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public boolean updateCustomer(Customer customerData) {
		String sql = "UPDATE customer SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ?, Address = ?, Username = ?, Password = ?, RegistrationDate = ? WHERE CustomerID = ?";
		
		try(Connection connection = dbContext.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			ps.setString(1, customerData.getFirstName());
			ps.setString(2, customerData.getLastName());
            ps.setString(3, customerData.getEmail());
            ps.setString(4, customerData.getPhoneNumber());
            ps.setString(5, customerData.getAddress());
            ps.setString(6, customerData.getUserName());
            ps.setString(7, customerData.getPassword());
            ps.setDate(8, (Date) (customerData.getRegistrationDate()));
            ps.setInt(9, customerData.getCustomerId());
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
		} catch (SQLException | DatabaseConnectionException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean deleteCustomer(int customerID) {
		String sql = "DELETE FROM customer WHERE CustomerID = ?";
		
		try(Connection connection = dbContext.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			ps.setInt(1, customerID);
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;
			
		} catch (SQLException | DatabaseConnectionException e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
