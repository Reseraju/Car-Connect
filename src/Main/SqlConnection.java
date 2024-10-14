package Main;

import java.sql.Date;
//import java.sql.Connection;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import Dao.ReportGenerator;
import Dao.ReservationService;
import Dao.VehicleService;
import Entity.Reservation;
import Entity.Vehicle;

public class SqlConnection {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		
		// Load the driver class
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		//-----Display Vehicle services class methods--------
		
		VehicleService vehicleService = new VehicleService();
		ReservationService reservationService = new ReservationService();
		
		while(true) {
			System.out.println("Enter your choice:\n1.Add new vehicle\n2.Update existing vehicle\n3.list available vehicles\n4.Remove vehicle\n5.Generate report\n6.Create new reservation\n7.Exit");
			int choice = sc.nextInt();
			
			
//			Vehicle newVehicle = new Vehicle(103, "Model X", "Tesla", 0, "White", "GHI2138", true, 200.00);
			
			Vehicle vehicle = null;
			switch(choice) {
			case 1:{
				// Add new vehicle
				
				vehicle = new Vehicle();
				System.out.println("Enter vehicle ID: ");
				int vehicleID = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Model: ");
				String model = sc.nextLine();
				System.out.println("Enter Make: ");
				String make = sc.next();
				System.out.println("Enter year: ");
				int year = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter color: ");
				String color = sc.next();
				System.out.println("Enter registration number: ");
				String registrationNo = sc.next();
				System.out.println("Is vehicle available for rent: (true/false)");
				boolean availability = sc.nextBoolean();
				sc.nextLine();
				System.out.println("Enter daily rate: ");
				double dailyRate = sc.nextDouble();
				
				vehicle.setVehicleId(vehicleID);
				vehicle.setModel(model);
				vehicle.setMake(make);
				vehicle.setYear(year);
				vehicle.setColor(color);
				vehicle.setRegistrationNumber(registrationNo);
				vehicle.setAvailability(availability);
				vehicle.setDailyRate(dailyRate);
		        boolean isVehicleAdded = vehicleService.addVehicle(vehicle);
		        
		        
		        if (isVehicleAdded) {
		            System.out.println("Vehicle added successfully!\n");
		        } else {
		            System.out.println("Failed to add vehicle.");
		        }
		        break;
			}
				
		        
			case 2:{
				// Update an existing vehicle
				vehicle = new Vehicle();
				System.out.println("Enter vehicle ID: ");
				int vehicleID = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Model: ");
				String model = sc.nextLine();
				System.out.println("Enter Make: ");
				String make = sc.next();
				System.out.println("Enter year: ");
				int year = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter color: ");
				String color = sc.next();
				System.out.println("Enter registration number: ");
				String registrationNo = sc.next();
				System.out.println("Is vehicle available for rent: (true/false)");
				boolean availability = sc.nextBoolean();
				sc.nextLine();
				System.out.println("Enter daily rate: ");
				double dailyRate = sc.nextDouble();
				
				vehicle.setVehicleId(vehicleID);
				vehicle.setModel(model);
				vehicle.setMake(make);
				vehicle.setYear(year);
				vehicle.setColor(color);
				vehicle.setRegistrationNumber(registrationNo);
				vehicle.setAvailability(availability);
				vehicle.setDailyRate(dailyRate);
		        boolean isVehicleUpdated = vehicleService.updateVehicle(vehicle);

		        if (isVehicleUpdated) {
		            System.out.println("Vehicle updated successfully!");
		        } else {
		            System.out.println("Failed to update vehicle.");
		        }
		        break;
			}
			case 3:{
				// Get available vehicles
		        List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles();
		        if (availableVehicles.isEmpty()) {
		            System.out.println("No available vehicles found.");
		        } else {
		        	System.out.println("List of all available vehicles");
		        	System.out.println("-----------------------------------");
		            for (Vehicle vehicles : availableVehicles) {
		                System.out.println(vehicles);
		            }
		        }
				break;
			}
		        
			case 4:{
				//Remove a vehicle
		        boolean isVehicleRemoved = vehicleService.removeVehicle(103);
				if (isVehicleRemoved) {
			          System.out.println("Vehicle removed successfully!");
			      } else {
			          System.out.println("Failed to remove vehicle.");
			      }
				break;
			}
				
			case 5:{
				//generate Report
				List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles();
				if (availableVehicles.isEmpty()) {
		            System.out.println("No vehicles found.");
		        } else {
		        	ReportGenerator reportGenerator = new ReportGenerator();
					reportGenerator.generateVehicleReport(availableVehicles);
		        }
				break;
			}
			
			case 6: {
				//create new reservation
				System.out.println("Enter Customer ID: ");
		        int customerID = sc.nextInt();
		        System.out.println("Enter Vehicle ID: ");
		        int vehicleID = sc.nextInt();
		        sc.nextLine();
		        
		        System.out.println("Enter Start Date (yyyy-mm-dd): ");
		        String startDateInput = sc.nextLine();
		        System.out.println("Enter End Date (yyyy-mm-dd): ");
		        String endDateInput = sc.nextLine();
		        
		        System.out.println("Enter Total Cost: ");
		        double totalCost = sc.nextDouble();
		        sc.nextLine(); 
		        
		        System.out.println("Enter Reservation Status (e.g., confirmed, pending): ");
		        String status = sc.nextLine();

		       
		        Date startDate = Date.valueOf(startDateInput); 
		        Date endDate = Date.valueOf(endDateInput);  

		        // Creating a Reservation object with the collected data
		        Reservation reservation = new Reservation();
		        reservation.setCustomerID(customerID);
		        reservation.setVehicleID(vehicleID);
		        reservation.setStartDate(startDate);
		        reservation.setEndDate(endDate);
		        reservation.setTotalCost(totalCost);
		        reservation.setStatus(status);

		        
		        boolean isReservationCreated = reservationService.createReservation(reservation);

		        
		        if (isReservationCreated) {
		            System.out.println("Reservation created successfully!");
		        } else {
		            System.out.println("Failed to create reservation.");
		        }
		        break;
			}
			case 7:
	            System.out.println("Exiting the program.");
	            sc.close();
	            return;
	            
	        default:
	            System.out.println("Invalid choice! Please select a valid option.");
	        
			}
		} 
	}
}
