package Dao;

import java.util.List;

import Entity.Vehicle;

public interface IVehicleService {
	Vehicle getVehicleByID(int vehicleID);
	List<Vehicle> getAvailableVehicles();
	boolean addVehicle(Vehicle vehicleData);
	boolean updateVehicle(Vehicle vehicleData);
	boolean removeVehicle(int vehicleID);
}
