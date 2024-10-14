package Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Vehicle;
import Exception.DatabaseConnectionException;
import Exception.InvalidInputException;
import Exception.VehicleNotFoundException;
import Util.DatabaseContext;

public class VehicleService implements IVehicleService {
	
	private DatabaseContext dbContext = new DatabaseContext();

	@Override
	public Vehicle getVehicleByID(int vehicleID) throws IOException {
		Vehicle vehicle = null;
        String sql = "SELECT * FROM vehicle WHERE VehicleID = ?";

        try (Connection connection = dbContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, vehicleID);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                vehicle = new Vehicle();
                vehicle.setVehicleId(rs.getInt("VehicleID"));
                vehicle.setModel(rs.getString("Model"));
                vehicle.setMake(rs.getString("Make"));
                vehicle.setYear(rs.getInt("Year"));
                vehicle.setColor(rs.getString("Color"));
                vehicle.setRegistrationNumber(rs.getString("RegistrationNumber"));
                vehicle.setAvailability(rs.getBoolean("Availability"));
                vehicle.setDailyRate(rs.getDouble("DailyRate"));
            }
            else {
                throw new VehicleNotFoundException("Vehicle with ID " + vehicleID + " not found.");
            }
        } catch (SQLException | VehicleNotFoundException | DatabaseConnectionException e) {
			e.printStackTrace();
		} 
        return vehicle;
	}

	@Override
	public List<Vehicle> getAvailableVehicles() throws IOException{
		List<Vehicle> vehicles = new ArrayList<>();
		String sql = "SELECT * FROM vehicle WHERE Availability = true";
		
		try(Connection connection = dbContext.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Vehicle vehicle = new Vehicle(
						rs.getInt("vehicleID"), 
						rs.getString("Model"), 
						rs.getString("Make"), 
						rs.getInt("Year"), 
						rs.getString("Color"), 
						rs.getString("RegistrationNumber"), 
						rs.getBoolean("Availability"), 
						rs.getDouble("DailyRate")
					);
                vehicles.add(vehicle);
				
			}
		} catch (SQLException | DatabaseConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vehicles;
	}

	@Override
	public boolean addVehicle(Vehicle vehicleData) throws IOException{
		String sql = "INSERT INTO vehicle (vehicleID, Model, Make, Year, Color, RegistrationNumber, Availability, DailyRate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dbContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
        	
        	// Validate inputs
            if (vehicleData.getModel() == null) {
                throw new InvalidInputException("Model cannot be null or empty.");
            }
            if (vehicleData.getMake() == null) {
                throw new InvalidInputException("Make cannot be null or empty.");
            }
            if (vehicleData.getYear() <= 0) {
                throw new InvalidInputException("Year must be a positive integer.");
            }
            if (vehicleData.getColor() == null) {
                throw new InvalidInputException("Color cannot be null or empty.");
            }
            if (vehicleData.getRegistrationNumber() == null) {
                throw new InvalidInputException("Registration number cannot be null or empty.");
            }
            if (vehicleData.getDailyRate() <= 0) {
                throw new InvalidInputException("Daily rate must be a positive value.");
            }

            statement.setInt(1, vehicleData.getVehicleId());
            statement.setString(2, vehicleData.getModel());
            statement.setString(3, vehicleData.getMake());
            statement.setInt(4, vehicleData.getYear());
            statement.setString(5, vehicleData.getColor());
            statement.setString(6, vehicleData.getRegistrationNumber());
            statement.setBoolean(7, vehicleData.getAvailability());
            statement.setDouble(8, vehicleData.getDailyRate());

            return statement.executeUpdate() > 0;
        } catch (SQLException | InvalidInputException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public boolean updateVehicle(Vehicle vehicleData) throws IOException{
		String sql = "UPDATE vehicle SET Model = ?, Make = ?, Year = ?, Color = ?, RegistrationNumber = ?, "
                + "Availability = ?, DailyRate = ? WHERE VehicleID = ?";

        try (Connection connection = dbContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
        	
        	// Validate inputs
            if (vehicleData.getModel() == null) {
                throw new InvalidInputException("Model cannot be null or empty.");
            }
            if (vehicleData.getMake() == null) {
                throw new InvalidInputException("Make cannot be null or empty.");
            }
            if (vehicleData.getYear() <= 0) {
                throw new InvalidInputException("Year must be a positive integer.");
            }
            if (vehicleData.getColor() == null) {
                throw new InvalidInputException("Color cannot be null or empty.");
            }
            if (vehicleData.getRegistrationNumber() == null) {
                throw new InvalidInputException("Registration number cannot be null or empty.");
            }
            if (vehicleData.getDailyRate() <= 0) {
                throw new InvalidInputException("Daily rate must be a positive value.");
            }

            statement.setString(1, vehicleData.getModel());
            statement.setString(2, vehicleData.getMake());
            statement.setInt(3, vehicleData.getYear());
            statement.setString(4, vehicleData.getColor());
            statement.setString(5, vehicleData.getRegistrationNumber());
            statement.setBoolean(6, vehicleData.getAvailability());
            statement.setDouble(7, vehicleData.getDailyRate());
            statement.setInt(8, vehicleData.getVehicleId());
            
            int rowsUpdated = statement.executeUpdate();
            
            if (rowsUpdated == 0) {
                throw new VehicleNotFoundException("Vehicle with ID " + vehicleData.getVehicleId() + " not found.");
            }
            
            return true;
        } catch (SQLException | VehicleNotFoundException | InvalidInputException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public boolean removeVehicle(int vehicleID) throws IOException{
		String sql = "DELETE FROM vehicle WHERE VehicleID = ?";

        try (Connection connection = dbContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, vehicleID);
            return statement.executeUpdate() > 0;
        } catch (SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        }
        return false;
	}

}
