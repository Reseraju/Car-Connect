package CarConnect;

import java.util.List;

public interface IReservationService {
	Reservation getReservationByID(int reservationID);
	List<Reservation> getReservationByCustomerID(int customerID);
	boolean createReservation(Reservation reservationData);
	boolean updateReservation(Reservation reservationData);
	boolean cancelReservation(int reservationID);
}
