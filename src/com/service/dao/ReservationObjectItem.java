package com.service.dao;

import java.util.Date;

public class ReservationObjectItem {
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getReservation_object_id() {
		return reservation_object_id;
	}
	public void setReservation_object_id(Integer reservation_object_id) {
		this.reservation_object_id = reservation_object_id;
	}

	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	Integer id;
	Integer reservation_object_id;
	String dateFrom;
	String dateTo;
    Boolean available;
}
