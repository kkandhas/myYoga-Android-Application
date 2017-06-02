package com.myyoga.rest;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.myyoga.models.User;
import com.myyoga.models.YogaAsanas;


/*
 * 
 * Utility method to Construct JSON and other basic functions
 */
public class Utility {
	/**
	 * Null check Method
	 * 
	 * @param txt
	 * @return
	 */
	public static boolean isNotNull(String txt) {
		// System.out.println("Inside isNotNull");
		return txt != null && txt.trim().length() >= 0 ? true : false;
	}

	/**
	 * Method to construct JSON
	 * 
	 * @param tag
	 * @param status
	 * @return
	 */
	public static String constructJSON(String tag, boolean status) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString();
	}

	/**
	 * Method to construct JSON with Error Msg
	 * 
	 * @param tag
	 * @param status
	 * @param err_msg
	 * @return
	 */
	public static String constructJSON(String tag, boolean status,
			String err_msg) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
			obj.put("error_msg", err_msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString();
	}
	
	public static String constructJSON(String tag, boolean status,
			User user) {
		JSONObject obj = new JSONObject();
		JSONObject obj1 = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
			//obj.put("user", user);
			obj1.put("name", user.getName());
			obj1.put("address", user.getAddress());
			obj1.put("email", user.getEmail());
			obj1.put("gender", user.getSex());
			obj1.put("Height", user.getHeight());
			obj1.put("Weight", user.getWeight());
			obj1.put("dob", user.getDob());
			obj.put("User",obj1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString();
	}

	public static String constructJSON(String tag, ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		
		try{
			
			//obj.put("tag", tag);
			while(rs.next()){
				JSONObject obj = new JSONObject();
				System.out.println(rs.getString(2));
				obj.put("Id", rs.getString(1));
				obj.put("name", rs.getString(2));
				obj.put("email", rs.getString(3));
				obj.put("password", rs.getString(4));
				array.put(obj);
			}
			json.put("Users", array);
		} catch (JSONException e){
			
		}
		return json.toString();
	}

	public static String constructJSON(String tag, boolean status,
			YogaAsanas yogaAsanas) {
		JSONObject json = new JSONObject();
		JSONObject jsonAsana = new JSONObject();
		 try{
			 json.put("tag", tag);
			 json.put("Status", status);
			 jsonAsana.put("asanaName", yogaAsanas.getYogaName());
			 jsonAsana.put("Level", yogaAsanas.getYogaLevel());
			 jsonAsana.put("youtubeId", yogaAsanas.getYoutubeValue());
			 jsonAsana.put("Steps", yogaAsanas.getYogaDescription());
			 json.put("Aasana", jsonAsana);
		 } catch(Exception e){
			 e.printStackTrace();
		 }
		return json.toString();
	}
	
	

}
