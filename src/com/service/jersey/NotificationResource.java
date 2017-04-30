package com.service.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.service.dao.INotificationDAO;
import com.service.dao.NotificationDAO;

@Path("/notification")
public class NotificationResource {
	
	INotificationDAO dao = new NotificationDAO();
	
	@GET
	@Path("/byUser") 
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8") 
	public String getAllUserNotification(@QueryParam("userId") String userId) 
	{
	    return new Gson().toJson(dao.getAllUserNotification(userId));
	}

}
