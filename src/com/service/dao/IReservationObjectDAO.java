package com.service.dao;

import java.util.List;

public interface IReservationObjectDAO {

    List<ReservationObject> getAllReservationObject();
    ReservationObject getReservationObject(String id);

}
