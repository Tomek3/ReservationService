package com.service.dao;

import java.util.List;

import com.service.jersey.DBConnection;

public class ReservationDAO implements IReservationDAO {
	
	@Override
	public List<Reservation> getAllUserReservation(String userId)
	{
		try {
			return DBConnection.getAllUserReservation(userId);
		} catch (Exception e) {
			return null;
		}
	}

}
