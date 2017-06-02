package com.myyoga.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.myyoga.models.User;


/*
 * Checking the login and constructing the User Object 
 * 
 */
@Path("/login")
public class Login {
	@GET
	@Path("/doLogin")
	@Produces(MediaType.APPLICATION_JSON)
	public String doLogin(@QueryParam("email") String uname,
			@QueryParam("password") String pwd) {
		String response = "";
		User user = checkCredentials(uname,pwd);
		if (user != null) {
			response = Utility.constructJSON("Login", true, user);
		} else {
			response = Utility.constructJSON("Login", false,
					"Incorrect Email/PWD");
		}
		return response;
	}

	public User checkCredentials(String... params){
		
		User user = null;
		if (Utility.isNotNull(params[0]) && Utility.isNotNull(params[1])) {
			try {
				user = DBConnection.checkLogin(params[0], params[1]);
				// System.out.println("Inside checkCredentials try "+result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return user;
	}
}
