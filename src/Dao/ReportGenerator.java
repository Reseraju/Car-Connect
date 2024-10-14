package Dao;

import java.util.List;

import Entity.Reservation;
import Entity.Vehicle;

public class ReportGenerator {
	
	public void generateReservationReport(List<Reservation> reservations) {
		for(Reservation reservation : reservations) {
			System.out.println(
					"Reservation ID: " + reservation.getReservationID() +
					"\nCustomer ID: " + reservation.getCustomerID() +
					"\nVehicle ID: " + reservation.getVehicleID() +
					"\nStart Date: " + reservation.getStartDate() +
					"\nEnd Date: " + reservation.getEndDate() +
					"\nTotal Cost: " + reservation.getTotalCost() +
					"\nStatus: " + reservation.getStatus()
					);
		}
	}
	
	public void generateVehicleReport(List<Vehicle> vehicles) {
		for (Vehicle vehicle : vehicles) {
			
            System.out.println("VehicleID: " + vehicle.getVehicleId() +
                               "\nModel: " + vehicle.getModel() +
                               "\nColor: " + vehicle.getColor() +
                               "\nDaily Rate: " + vehicle.getDailyRate() +
                               "\nAvailability: " + vehicle.getAvailability()
            		);
            
            System.out.println("\n------------------------------------\n");
        }
	}
}
