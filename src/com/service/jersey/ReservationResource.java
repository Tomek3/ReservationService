package com.service.jersey;


import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.service.dao.ReservationDAO;
import com.service.dao.ReservationObjectItemDAO;


@Path("/reservation")
public class ReservationResource {
	
	ReservationDAO dao = new ReservationDAO();
	
	@GET
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON) 
	public String createReservation(@QueryParam("userId") String userId, @QueryParam("resId") String resId) {

		String response = "";
        int retCode = insertReservation(userId, resId);
        if(retCode == 0){
            response = Utility.constructJSON("create",true);
        }else if(retCode == 1){
            response = Utility.constructJSON("create",false, "Object is not available");
		}else if(retCode == 2){
	        response = Utility.constructJSON("create",false, "Error occured");
	    }
        return response;

	}
	
	@GET
	@Path("/byUser") 
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8") 
	public String getAllUserReservation(@QueryParam("userId") String userId) 
	{
	    return new Gson().toJson(dao.getAllUserReservation(userId));
	}
	
	private int insertReservation(String userId, String resId){
        int result = 2;
        if(Utility.isNotNull(userId) && Utility.isNotNull(resId)){
            try {
            	if(!DBConnection.isReservationObjectItemAvailable(resId))
            	{
            		//reservation already exist
            		return 1;
            	}
            	
                if(DBConnection.insertReservation(userId, resId)){
                    result = 0;
                    DBConnection.setReservationObjectItemAvailable(resId, 0);
                }
            } catch(SQLException sqle){
                if(sqle.getErrorCode() == 1062){
                    result = 1;
                } 
            }
            catch (Exception e) {
                result = 2;
            }
        }
 
        return result;
    }

}
