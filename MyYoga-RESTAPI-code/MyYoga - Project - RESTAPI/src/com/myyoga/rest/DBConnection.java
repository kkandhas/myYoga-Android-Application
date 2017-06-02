package com.myyoga.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.myyoga.models.User;
import com.myyoga.models.YogaAsanas;

/*
 * Creating the DB Connection and performing fetching, inserting and updating values
 * 
 */
public class DBConnection {

	@SuppressWarnings("finally")
	public static Connection createConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName(Constants.dbClass);
			con = DriverManager.getConnection(Constants.dbUrl,
					Constants.dbUser, Constants.dbPwd);
		} catch (Exception e) {
			throw e;
		} finally {
			return con;
		}
	}

	public static User checkLogin(String uname, String pwd) throws Exception {
		//boolean isUserAvailable = false;
		Connection dbConn = null;
		ResultSet rs = null;
		User user = new User();
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM users WHERE email = '" + uname
					+ "' AND password=" + "'" + pwd + "'";
			// System.out.println(query);

			rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println(rs);
				System.out.println(rs.getString(1) + rs.getString(2)
						+ rs.getString(3));
				//isUserAvailable = true;
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setDob(rs.getString(5));
				user.setPhone(rs.getString(6));
				user.setAddress(rs.getString(7));
				user.setHeight(rs.getString(8));
				user.setWeight(rs.getString(9));
				user.setSex(rs.getString(10));
				
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
		return user;
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
	
	public static boolean insertUser(User user) throws SQLException, Exception {
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
            String query = "INSERT into users(name, email, password,address,dob,phone,gender,height,weight) values('"+user.getName()+ "',"+"'"
                    + user.getEmail() + "','" + user.getPassword() 
                    + "','" + user.getAddress()+ "','" +user.getDob() + "','" + user.getPhone()
                    + "','" + user.getSex() + "','" + user.getHeight() + "','" + user.getWeight()
                    + "')";
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

	 
	public static ResultSet allUsers() throws SQLException {
		// TODO Auto-generated method stub
		Connection dbConn = null;
		ResultSet rs = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM users";
			// System.out.println(query);

			rs = stmt.executeQuery(query);

		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} 
		return rs;
	}

	public static YogaAsanas fetchAsanaDetails(String yName) throws SQLException {
		Connection dbConn = null;
		ResultSet rs = null;
		YogaAsanas yogaAsanas = new YogaAsanas();
		try{
			try{
				dbConn = DBConnection.createConnection();
			} catch(Exception e){
				e.printStackTrace();
			}
			Statement st = dbConn.createStatement();
			String query = "SELECT * FROM POSTURE_DETAILS WHERE YOGA_NAME =  '" + yName	+ "'";
			rs = st.executeQuery(query);
			while (rs.next()) {
				yogaAsanas.setYogaName(yName);
				yogaAsanas.setYogaLevel(rs.getString(3));
				yogaAsanas.setYoutubeValue(rs.getString(4));
				yogaAsanas.setYogaDescription(rs.getNString(5));
			}
			
		} catch (SQLException sqle){
			throw sqle;
		} catch (Exception e){
			if(dbConn!=null){
				dbConn.close();
			} throw e;
		}
		return yogaAsanas;
	}
	

	
}
