package com.service.dao;

import java.util.List;

public interface IReservationDAO {
	
	List<Reservation> getAllUserReservation(String userId);
	
	List<Reservation> getAllUserObjectWatched(String userId);

}
