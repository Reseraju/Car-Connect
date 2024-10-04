package CarConnect;

import java.util.List;

public interface IVehicleService {
	Vehicle getVehicleByID(int vehicleID);
	List<Vehicle> getAvailableVehicles();
	boolean addVehicle(Vehicle vehicleData);
	boolean updateVehicle(Vehicle vehicleData);
	boolean removeVehicle(int vehicleID);
}
