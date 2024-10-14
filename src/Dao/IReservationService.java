package Dao;

import java.io.IOException;
import java.util.List;

import Entity.Reservation;

public interface IReservationService {
	Reservation getReservationByID(int reservationID) throws IOException;
	List<Reservation> getReservationByCustomerID(int customerID) throws IOException;
	boolean createReservation(Reservation reservationData) throws IOException;
	boolean updateReservation(Reservation reservationData) throws IOException;
	boolean cancelReservation(int reservationID) throws IOException;
}
