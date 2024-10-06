package CarConnect;

//import java.sql.Connection;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.List;

public class SqlConnection {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		// Load the driver class
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		//-----Display Vehicle services class methods--------
		
		VehicleService vehicleService = new VehicleService();
		
		
		// Add new vehicle
//		Vehicle newVehicle = new Vehicle(103, "Model X", "Tesla", 0, "White", "GHI2138", true, 200.00);
//        boolean isVehicleAdded = vehicleService.addVehicle(newVehicle);
//        
//        
//        if (isVehicleAdded) {
//            System.out.println("Vehicle added successfully!");
//        } else {
//            System.out.println("Failed to add vehicle.");
//        }
        
        
        // Update an existing vehicle
//        newVehicle.setColor("Red"); // Changing color
//        boolean isVehicleUpdated = vehicleService.updateVehicle(newVehicle);
//
//        if (isVehicleUpdated) {
//            System.out.println("Vehicle updated successfully!");
//        } else {
//            System.out.println("Failed to update vehicle.");
//        }
		
        
		// Get available vehicles
        List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles();
        if (availableVehicles.isEmpty()) {
            System.out.println("No available vehicles found.");
        } else {
        	System.out.println("List of all available vehicles");
        	System.out.println("-----------------------------------");
            for (Vehicle vehicle : availableVehicles) {
                System.out.println(vehicle);
            }
        }
        
        //Remove a vehicle
//        boolean isVehicleRemoved = vehicleService.removeVehicle(103);
//		if (isVehicleRemoved) {
//	          System.out.println("Vehicle removed successfully!");
//	      } else {
//	          System.out.println("Failed to remove vehicle.");
//	      }
        
       
	}
}
