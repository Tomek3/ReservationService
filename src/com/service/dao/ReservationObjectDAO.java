package com.service.dao;

import java.util.List;

import com.service.jersey.DBConnection;

public class ReservationObjectDAO implements IReservationObjectDAO {
	
	@Override
	public List<ReservationObject> getAllReservationObject()
	{
		try {
			return DBConnection.getAllReservationObject();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public ReservationObject getReservationObject(String id)
	{
		return new ReservationObject();
	}

}
