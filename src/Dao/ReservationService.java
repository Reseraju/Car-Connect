package Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Reservation;
import Exception.DatabaseConnectionException;
import Util.DatabaseContext;

public class ReservationService implements IReservationService {
	
	private DatabaseContext dbContext = new DatabaseContext();
	
	@Override
	public Reservation getReservationByID(int reservationID) throws IOException {
		
		
		Reservation reservation = null;
		Connection connection = null;
		
		try{
			connection = dbContext.getConnection();
			String sql = "SELECT * FROM reservation WHERE ReservationID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, reservationID);
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next()) {
				reservation = new Reservation(
						resultSet.getInt("ReservationID"),
	                    resultSet.getInt("CustomerID"),
	                    resultSet.getInt("VehicleID"),
	                    resultSet.getDate("StartDate"),
	                    resultSet.getDate("EndDate"),
	                    resultSet.getDouble("TotalCost"),
	                    resultSet.getString("Status")
						);
			}
			
		} catch (SQLException | DatabaseConnectionException e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("error connecting to db in getReservationByID() method in ReservationService class");
					e.printStackTrace();
				}
			}
		}
		return reservation;
	}

	@Override
	public List<Reservation> getReservationByCustomerID(int customerID) throws IOException {
		List<Reservation> reservations = new ArrayList<>();
		Connection connection = null;
		
		try {
			connection = dbContext.getConnection();
			String sql = "SELECT * FROM reservation WHERE CustomerID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, customerID);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Reservation reservation = new Reservation(
						rs.getInt("ReservationID"),
						rs.getInt("CustomerID"),
	                    rs.getInt("VehicleID"),
	                    rs.getDate("StartDate"),
	                    rs.getDate("EndDate"),
	                    rs.getDouble("TotalCost"),
	                    rs.getString("Status")
						);
				reservations.add(reservation);
			}
			
		} catch (SQLException | DatabaseConnectionException e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("Error in connection to db!! On getReservationByCustomerID() method on ReservationService class");
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public boolean createReservation(Reservation reservationData) throws IOException{
		Connection connection = null;
        boolean isCreated = false;

        try {
            connection = dbContext.getConnection();
            String sql = "INSERT INTO reservation (CustomerID, VehicleID, StartDate, EndDate, TotalCost, Status) "
                       + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, reservationData.getCustomerID());
            statement.setInt(2, reservationData.getVehicleID());
            statement.setDate(3, (Date) reservationData.getStartDate());
            statement.setDate(4, (Date) reservationData.getEndDate());
            statement.setDouble(5, reservationData.getTotalCost());
            statement.setString(6, reservationData.getStatus());

            int rowsInserted = statement.executeUpdate();
            isCreated = rowsInserted > 0;
        } catch (SQLException | DatabaseConnectionException e) {
        	System.out.println("throws sql exception in createReservation() method ");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return isCreated;
	}

	@Override
	public boolean updateReservation(Reservation reservationData) throws IOException{
		Connection connection = null;
        boolean isUpdated = false;

        try {
            connection = dbContext.getConnection();
            String sql = "UPDATE reservation SET CustomerID = ?, VehicleID = ?, StartDate = ?, EndDate = ?, TotalCost = ?, Status = ? "
                       + "WHERE ReservationID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, reservationData.getCustomerID());
            statement.setInt(2, reservationData.getVehicleID());
            statement.setDate(3, new java.sql.Date(reservationData.getStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(reservationData.getEndDate().getTime()));
            statement.setDouble(5, reservationData.getTotalCost());
            statement.setString(6, reservationData.getStatus());
            statement.setInt(7, reservationData.getReservationID());

            int rowsUpdated = statement.executeUpdate();
            isUpdated = rowsUpdated > 0;
        } catch (SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return isUpdated;
	}

	@Override
	public boolean cancelReservation(int reservationID) throws IOException{
		Connection connection = null;
        boolean isCanceled = false;

        try {
            connection = dbContext.getConnection();
            String sql = "DELETE FROM reservation WHERE ReservationID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, reservationID);

            int rowsDeleted = statement.executeUpdate();
            isCanceled = rowsDeleted > 0;
        } catch (SQLException | DatabaseConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return isCanceled;
	}

}
