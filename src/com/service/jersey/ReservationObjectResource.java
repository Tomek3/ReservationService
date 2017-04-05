package com.service.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.service.dao.*;
import com.google.gson.Gson;

@Path("/reservationObject")
public class ReservationObjectResource {
	
	ReservationObjectDAO dao = new ReservationObjectDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8") 
	public String getAllReservationObject() 
	{
	    return new Gson().toJson(dao.getAllReservationObject());
	}

}
