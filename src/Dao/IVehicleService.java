package Dao;

import java.io.IOException;
import java.util.List;

import Entity.Vehicle;

public interface IVehicleService {
	Vehicle getVehicleByID(int vehicleID) throws IOException;
	List<Vehicle> getAvailableVehicles() throws IOException;
	boolean addVehicle(Vehicle vehicleData) throws IOException;
	boolean updateVehicle(Vehicle vehicleData) throws IOException;
	boolean removeVehicle(int vehicleID) throws IOException;
}
