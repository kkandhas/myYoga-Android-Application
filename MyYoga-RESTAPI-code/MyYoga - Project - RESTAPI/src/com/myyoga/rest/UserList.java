package com.myyoga.rest;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/userlist")
public class UserList {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String userList() throws SQLException {
		String response = "";
		ResultSet rs = null;
		try {
			rs = DBConnection.allUsers();
			// System.out.println("Inside checkCredentials try "+result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// System.out.println("Inside checkCredentials catch");
			//result = false;
		}
		if (rs !=null) {
			response = Utility.constructJSON("List", rs);
		} else {
			response = Utility.constructJSON("Login", false,
					"Incorrect Email/PWD");
		}
		return response;
	}

}
