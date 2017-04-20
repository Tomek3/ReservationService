package com.service.jersey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.service.dao.*;
 
public class DBConnection {
    /**
     * Method to create DB Connection
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("finally")
    public static Connection createConnection() throws Exception {
        Connection con = null;
        try {
            Class.forName(Constants.dbClass);
            con = DriverManager.getConnection(Constants.dbUrl, Constants.dbUser, Constants.dbPwd);
        } catch (Exception e) {
            throw e;
        } finally {
            return con;
        }
    }
    /**
     * Method to check whether uname and pwd combination are correct
     * 
     * @param uname
     * @param pwd
     * @return
     * @throws Exception
     */
    public static boolean checkLogin(String uname, String pwd) throws Exception {
        boolean isUserAvailable = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM user WHERE username = '" + uname
                    + "' AND password=" + "'" + pwd + "'";
            //System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
                isUserAvailable = true;
            }
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return isUserAvailable;
    }
    /**
     * Method to insert uname and pwd in DB
     * 
     * @param name
     * @param uname
     * @param pwd
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static boolean insertUser(String name, String uname, String pwd) throws SQLException, Exception {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "INSERT into user(name, username, password) values('"+name+ "',"+"'"
                    + uname + "','" + pwd + "')";
            //System.out.println(query);
            int records = stmt.executeUpdate(query);
            //System.out.println(records);
            //When record is successfully inserted
            if (records > 0) {
                insertStatus = true;
            }
        } catch (SQLException sqle) {
            //sqle.printStackTrace();
            throw sqle;
        } catch (Exception e) {
            //e.printStackTrace();
            // TODO Auto-generated catch block
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return insertStatus;
    }
    
    public static UserData getUserData(String login) throws Exception {
    	UserData result = null;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT name, username, password, id FROM user WHERE username = '" + login
                    + "'";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
            	result = new UserData();
                result.name = rs.getString(1);
                result.login = rs.getString(2);
                result.password = rs.getString(3);
                result.id = rs.getInt(4);
            }
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return result;
    }
    
    public static List<ReservationObject> getAllReservationObject() throws Exception {
    	List<ReservationObject> result = new ArrayList<ReservationObject>();
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT id, name, address, info, active FROM reservation_object";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            	ReservationObject ro = new ReservationObject();
            	
            	ro.setId(rs.getInt(1));
            	ro.setName(rs.getString(2));
            	ro.setAddress(rs.getString(3));
            	ro.setInfo(rs.getString(4));
            	ro.setActive(rs.getBoolean(5));
            	
            	result.add(ro);

            }
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return result;
    }
    
    public static List<ReservationObjectItem> getReservationObjectItem(String objectId, String date) throws Exception {
    	List<ReservationObjectItem> result = new ArrayList<ReservationObjectItem>();
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT id, reservation_object_id, date_from, date_to, available FROM reservation_object_item "
            		+ " where reservation_object_id = '" + objectId +"' and DATE(date_from) >= '" + date + "' and DATE(date_to) <= '" + date + "'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            	ReservationObjectItem roi = new ReservationObjectItem();
            	
            	roi.setId(rs.getInt(1));
            	roi.setReservation_object_id(rs.getInt(2));
            	roi.setDateFrom(rs.getString(3));
            	roi.setDateTo(rs.getString(4));
            	roi.setAvailable(rs.getBoolean(5));
            	
            	result.add(roi);

            }
        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            if (dbConn != null) {
                dbConn.close();
            }
            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return result;
    }
}
