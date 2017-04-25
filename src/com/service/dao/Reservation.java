package com.service.dao;

public class Reservation {
	Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getReservationObjectId() {
		return reservationObjectId;
	}
	public void setReservationObjectId(Integer reservationObjectId) {
		this.reservationObjectId = reservationObjectId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public String getReservationObjectName() {
		return reservationObjectName;
	}
	public void setReservationObjectName(String reservationObjectName) {
		this.reservationObjectName = reservationObjectName;
	}
	public String getReservationObjectAddress() {
		return reservationObjectAddress;
	}
	public void setReservationObjectAddress(String reservationObjectAddress) {
		this.reservationObjectAddress = reservationObjectAddress;
	}
	public String getReservationObjectInfo() {
		return reservationObjectInfo;
	}
	public void setReservationObjectInfo(String reservationObjectInfo) {
		this.reservationObjectInfo = reservationObjectInfo;
	}
	Integer reservationObjectId;
	Integer userId;
	String dateFrom;
	String dateTo;
    String reservationObjectName;
    String reservationObjectAddress;
    String reservationObjectInfo;
}
