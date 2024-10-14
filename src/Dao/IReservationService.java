package Dao;

import java.util.List;

import Entity.Reservation;

public interface IReservationService {
	Reservation getReservationByID(int reservationID);
	List<Reservation> getReservationByCustomerID(int customerID);
	boolean createReservation(Reservation reservationData);
	boolean updateReservation(Reservation reservationData);
	boolean cancelReservation(int reservationID);
}
