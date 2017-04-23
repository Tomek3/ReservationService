package com.service.jersey;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/watch")
public class ObjectWatched {
	
	@GET
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON) 
	public String createWatch(@QueryParam("userId") String userId, @QueryParam("resId") String resId) {

		String response = "";
        int retCode = insertWatch(userId, resId);
        if(retCode == 0){
            response = Utility.constructJSON("create",true);
        }else if(retCode == 1){
            response = Utility.constructJSON("create",false, "Object is not available");
		}else if(retCode == 2){
	        response = Utility.constructJSON("create",false, "Error occured");
	    }
        return response;

	}
	
	private int insertWatch(String userId, String resId){
        int result = 2;
        if(Utility.isNotNull(userId) && Utility.isNotNull(resId)){
            try {
            	if(!DBConnection.isWatchObjectItemAvailable(resId))
            	{
            		//watch already exist
            		return 1;
            	}
            	
                if(DBConnection.insertWatched(userId, resId)){
                    result = 0;
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
