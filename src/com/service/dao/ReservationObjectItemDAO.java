package com.service.dao;

import java.util.List;
import com.service.jersey.DBConnection;

public class ReservationObjectItemDAO implements IReservationObjectItemDAO {
	
	@Override
	public List<ReservationObjectItem> getReservationObject(String objectId, String date)
	{
		try {
			return DBConnection.getReservationObjectItem(objectId, date);
		} catch (Exception e) {
			return null;
		}
	}
}
