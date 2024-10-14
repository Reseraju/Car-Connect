package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.Admin;
import Exception.AdminNotFoundException;
import Exception.DatabaseConnectionException;
import Util.DatabaseContext;

public class AdminService implements IAdminService {
	private DatabaseContext dbContext = new DatabaseContext();
	
	@Override
	public Admin getAdminByID(int adminID) {
		Admin admin = null;
        String sql = "SELECT * FROM admin WHERE AdminID = ?";

        try (Connection connection = dbContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, adminID);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setAdminID(rs.getInt("AdminID"));
                admin.setFirstName(rs.getString("FirstName"));
                admin.setLastName(rs.getString("LastName"));
                admin.setEmail(rs.getString("Email"));
                admin.setPhoneNumber(rs.getString("PhoneNumber"));
                admin.setUsername(rs.getString("Username"));
                admin.setPassword(rs.getString("Password"));
                admin.setRole(rs.getString("Role"));
                admin.setJoinDate(rs.getDate("JoinDate"));
            }
            else {
            	throw new AdminNotFoundException("Admin with ID"  + adminID  +"not found!!");
            }
        } catch (SQLException | AdminNotFoundException | DatabaseConnectionException e) {
			e.printStackTrace();
		} 
        return admin;
	}

	@Override
	public Admin getAdminByUsername(String username) {
		Admin admin = null;
        String sql = "SELECT * FROM admin WHERE Username = ?";

        try (Connection connection = dbContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setAdminID(rs.getInt("AdminID"));
                admin.setFirstName(rs.getString("FirstName"));
                admin.setLastName(rs.getString("LastName"));
                admin.setEmail(rs.getString("Email"));
                admin.setPhoneNumber(rs.getString("PhoneNumber"));
                admin.setUsername(rs.getString("Username"));
                admin.setPassword(rs.getString("Password"));
                admin.setRole(rs.getString("Role"));
                admin.setJoinDate(rs.getDate("JoinDate"));
            }
            else {
            	throw new AdminNotFoundException("Admin with username " + username + " not found!!");
            }
        } catch (SQLException | AdminNotFoundException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        return admin;
	}

	@Override
	public boolean registerAdmin(Admin adminData) {
		String sql = "INSERT INTO admin (AdminID, FirstName, LastName, Email, PhoneNumber, Username, Password, Role, JoinDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dbContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, adminData.getAdminID());
            statement.setString(2, adminData.getFirstName());
            statement.setString(3, adminData.getLastName());
            statement.setString(4, adminData.getEmail());
            statement.setString(5, adminData.getPhoneNumber());
            statement.setString(6, adminData.getUsername());
            statement.setString(7, adminData.getPassword());
            statement.setString(8, adminData.getRole());
            statement.setDate(9, (Date) adminData.getJoinDate());

            return statement.executeUpdate() > 0;
        } catch (SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public boolean updateAdmin(Admin adminData) {
		String sql = "UPDATE admin SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ?, Username = ?, "
                + "Password = ?, Role = ?, JoinDate = ? WHERE AdminID = ?";

        try (Connection connection = dbContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, adminData.getFirstName());
            statement.setString(2, adminData.getLastName());
            statement.setString(3, adminData.getEmail());
            statement.setString(4, adminData.getPhoneNumber());
            statement.setString(5, adminData.getUsername());
            statement.setString(6, adminData.getPassword());
            statement.setString(7, adminData.getRole());
            statement.setDate(8, (Date) adminData.getJoinDate());
            statement.setInt(9, adminData.getAdminID());

            return statement.executeUpdate() > 0;
        } catch (SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public boolean deleteAdmin(int adminID) {
		String sql = "DELETE FROM admin WHERE AdminID = ?";

        try (Connection connection = dbContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, adminID);
            return statement.executeUpdate() > 0;
        } catch (SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        return false;
	}

}
