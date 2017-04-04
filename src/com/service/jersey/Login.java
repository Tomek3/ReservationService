package com.service.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;
//Path: http://localhost/<appln-folder-name>/login
@Path("/login")
public class Login {
    // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
    @Path("/dologin")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
    public String doLogin(@QueryParam("username") String uname, @QueryParam("password") String pwd){
        String response = "";
        if(checkCredentials(uname, pwd)){
        	JSONObject obj = Utility.createJSON("login",true);
        	UserData userData = getUserData(uname);
        	Utility.addToJSON(obj, "login", userData.login);
        	Utility.addToJSON(obj, "password", userData.password);
        	if(Utility.isNotNull(userData.name))
        	{
        		Utility.addToJSON(obj, "name", userData.name);
        	}
        	response = obj.toString();
        }else{
            response = Utility.constructJSON("login", false, "Incorrect Email or Password");
        }
    return response;        
    }
 
    /**
     * Method to check whether the entered credential is valid
     * 
     * @param uname
     * @param pwd
     * @return
     */
    private boolean checkCredentials(String uname, String pwd){
        System.out.println("Inside checkCredentials");
        boolean result = false;
        if(Utility.isNotNull(uname) && Utility.isNotNull(pwd)){
            try {
                result = DBConnection.checkLogin(uname, pwd);
                //System.out.println("Inside checkCredentials try "+result);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                //System.out.println("Inside checkCredentials catch");
                result = false;
            }
        }else{
            //System.out.println("Inside checkCredentials else");
            result = false;
        }
 
        return result;
    }
    
    private UserData getUserData(String login){
    	UserData result = null;
        if(Utility.isNotNull(login)){
            try {
                result = DBConnection.getUserData(login);
            } catch (Exception e) {
            }
        }
        
        return result;
    }
 
}
