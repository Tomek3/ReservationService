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
    
    public static boolean isReservationObjectItemAvailable(String reservationObjectItemId) throws Exception {
    	boolean available = true;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT id FROM reservation WHERE reservation_object_item_id = '" + reservationObjectItemId
                    + "' AND deleted is null";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
            	available = false;
            }
            
            query = "SELECT id FROM reservation_object_item WHERE id = '" + reservationObjectItemId
                    + "' AND available=1 AND date_from < now()";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
            	available = false;
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
        return available;
    }
    
    public static boolean insertReservation(String userId, String resId) throws SQLException, Exception {
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
            String query = "INSERT into reservation(reservation_object_item_id, user_id) values('"+resId+ "',"+"'"
                    + userId + "')";
            int records = stmt.executeUpdate(query);
            if (records > 0) {
                insertStatus = true;
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
        return insertStatus;
    }
    
    public static boolean setReservationObjectItemAvailable(String resId, int status) throws SQLException, Exception {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "UPDATE reservation_object_item set available = '"+status+ "' where id ='"
                    + resId + "'";
            int records = stmt.executeUpdate(query);
            if (records > 0) {
                insertStatus = true;
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
        return insertStatus;
    }
    
    
    //object watched
    public static boolean isWatchObjectItemAvailable(String userId, String reservationObjectItemId) throws Exception {
    	boolean available = true;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT id FROM object_watched WHERE reservation_object_item_id = '" + reservationObjectItemId
                    + "' and user_id = '" + userId + "' AND deleted is null";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
            	available = false;
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
        return available;
    }
    
    public static boolean insertWatched(String userId, String resId) throws SQLException, Exception {
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
            String query = "INSERT into object_watched(reservation_object_item_id, user_id) values('"+resId+ "',"+"'"
                    + userId + "')";
            int records = stmt.executeUpdate(query);
            if (records > 0) {
                insertStatus = true;
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
        return insertStatus;
    }
    
    //reservation
    public static List<Reservation> getAllUserReservation(String userId) throws Exception {
    	List<Reservation> result = new ArrayList<Reservation>();
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT RES.id, ROI.id, RES.user_id, ROI.date_from, ROI.date_to, OBJ.name, OBJ.address, OBJ.info " +
            			   "FROM reservation RES " +
            			   "INNER JOIN reservation_object_item ROI on ROI.id = RES.reservation_object_item_id " +
            			   "INNER JOIN reservation_object OBJ on OBJ.id = ROI.reservation_object_id " +
            		       "WHERE RES.user_id = '" + userId +"' and deleted is null " +
            			   "ORDER BY ROI.date_from DESC";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            	Reservation res = new Reservation();
            	
            	res.setId(rs.getInt(1));
            	res.setReservationObjectId(rs.getInt(2));
            	res.setUserId(rs.getInt(3));
            	res.setDateFrom(rs.getString(4));
            	res.setDateTo(rs.getString(5));
            	res.setReservationObjectName(rs.getString(6));
            	res.setReservationObjectAddress(rs.getString(7));
            	res.setReservationObjectInfo(rs.getString(8));
            	
            	result.add(res);

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
    
    public static boolean canDeleteReservation(String resId) throws Exception {
    	boolean available = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT RES.id FROM reservation RES "
            		+ "INNER JOIN reservation_object_item ROI on ROI.id = RES.reservation_object_item_id "
            		+ "WHERE RES.id = '" + resId
                    + "' AND RES.deleted is null AND DATE_ADD(ROI.date_from, INTERVAL -1 HOUR) > now()";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
            	available = true;
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
        return available;
    }
    
    public static boolean deleteReservation(String resId) throws SQLException, Exception {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "UPDATE reservation set deleted = 1 where id ='"
                    + resId + "'";
            int records = stmt.executeUpdate(query);
            if (records > 0) {
                insertStatus = true;
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
        return insertStatus;
    }
    
    public static String getReservationObjectItemId(String resId) throws Exception {
    	String result = null;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "SELECT reservation_object_item_id from Reservation where id = '" + resId
                    + "'";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                result = rs.getString(1);
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
    
    
  //notification
    public static List<Notification> getAllUserNotification(String userId) throws Exception {
    	List<Notification> result = new ArrayList<Notification>();
        Connection dbConn = null;
        try {
        	if(!makeUserNotification(userId)){
        		return result;
        	}
        	
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            
            String query = "SELECT N.id, N.user_id, N.reservation_id, N.object_watch_id, ROI.date_from, ROI.date_to, OBJ.name, ROI.id " +
            			   "FROM notification N " +
            			   "INNER JOIN reservation RES on RES.id = N.reservation_id " +
            			   "INNER JOIN reservation_object_item ROI on ROI.id = RES.reservation_object_item_id " +
            			   "INNER JOIN reservation_object OBJ on OBJ.id = ROI.reservation_object_id " +
            		       "WHERE N.user_id = '" + userId +"' and N.deleted is null and N.send is null " +
            			   "UNION ALL "+
            			   "SELECT N.id, N.user_id, N.reservation_id, N.object_watch_id, ROI.date_from, ROI.date_to, OBJ.name, ROI.id " +
            			   "FROM notification N " +
            			   "INNER JOIN object_watched OW on OW.id = N.object_watch_id " +
            			   "INNER JOIN reservation_object_item ROI on ROI.id = OW.reservation_object_item_id " +
            			   "INNER JOIN reservation_object OBJ on OBJ.id = ROI.reservation_object_id " +
            		       "WHERE N.user_id = '" + userId +"' and N.deleted is null and N.send is null ";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            	Notification notification = new Notification();
            	
            	notification.setId(rs.getInt(1));
            	notification.setUserId(rs.getInt(2));
            	notification.setReservationId(rs.getInt(3));
            	notification.setObjectWatchId(rs.getInt(4));
            	notification.setDateFrom(rs.getString(5));
            	notification.setDateTo(rs.getString(6));
            	notification.setObjectName(rs.getString(7));
            	notification.setReservationObjectItemId(rs.getInt(8));
            	
            	result.add(notification);

            }
            
            query = "UPDATE notification set send = 1 where user_id ='"
                    + userId + "'";
            stmt.executeUpdate(query);
            
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
    
    public static boolean makeUserNotification(String userId) throws SQLException, Exception {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = 
			"INSERT INTO notification(user_id, reservation_id, object_watch_id, send) "+
			"select S.user_id, S.reservation_id, S.object_watch_id, S.send "+
			"from "+
			"(select OW.user_id as user_id, null as reservation_id , OW.id as object_watch_id , null as send "+
			"from object_watched OW "+
			"inner join reservation_object_item ROI on ROI.id = OW.reservation_object_item_id and ROI.available = 1 "+
			"left join notification N on N.user_id = OW.user_id and N.object_watch_id = OW.id "+
			"where N.id is null and OW.deleted is null and OW.user_id = '" + userId +"' "+
			"union all "+
			"select RES.user_id, RES.id, null, null "+
			"from reservation RES "+
			"inner join reservation_object_item ROI on ROI.id = RES.reservation_object_item_id and ROI.available = 0 "+
			"left join notification N on N.user_id = RES.user_id and N.reservation_id = RES.id "+
			"where N.id is null and RES.deleted is null and DATE_ADD(ROI.date_from, INTERVAL -3 HOUR) < now() and ROI.date_to > now() and RES.user_id = '" + userId +"' ) S";
            int records = stmt.executeUpdate(query);
            if (records > 0) {
                insertStatus = true;
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
        return insertStatus;
    }
    
    public static boolean finishUserNotification(String userId) throws SQLException, Exception {
        boolean insertStatus = false;
        Connection dbConn = null;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Statement stmt = dbConn.createStatement();
            String query = "UPDATE notification set send = 1 where user_id ='"
                    + userId + "'";
            int records = stmt.executeUpdate(query);
            if (records > 0) {
                insertStatus = true;
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
        return insertStatus;
    }
}
