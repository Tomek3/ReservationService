package com.service.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.service.dao.*;

@Path("/reservationObjectItem")
public class ReservationObjectItemResource {
	
	ReservationObjectItemDAO dao = new ReservationObjectItemDAO();
	
	@GET
	@Path("/byObjectAndDate") 
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8") 
	public String getAllReservationObjectItem(@QueryParam("objectId") String objectId, @QueryParam("date") String date) 
	{
	    return new Gson().toJson(dao.getReservationObject(objectId, date));
	}

}
