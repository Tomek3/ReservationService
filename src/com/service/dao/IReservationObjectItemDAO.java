package com.service.dao;

import java.util.List;

public interface IReservationObjectItemDAO {
	List<ReservationObjectItem> getReservationObject(String objectId, String date);
}
