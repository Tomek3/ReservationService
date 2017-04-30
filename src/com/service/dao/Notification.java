package com.service.dao;

public class Notification {
	Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getReservationId() {
		return reservationId;
	}
	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}
	public Integer getObjectWatchId() {
		return objectWatchId;
	}
	public void setObjectWatchId(Integer objectWatchId) {
		this.objectWatchId = objectWatchId;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
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
	Integer userId;
	Integer reservationId;
	Integer objectWatchId;
	String objectName;
    String dateFrom;
	String dateTo;
	Integer reservationObjectItemId;
	public Integer getReservationObjectItemId() {
		return reservationObjectItemId;
	}
	public void setReservationObjectItemId(Integer reservationObjectItemId) {
		this.reservationObjectItemId = reservationObjectItemId;
	}
}
