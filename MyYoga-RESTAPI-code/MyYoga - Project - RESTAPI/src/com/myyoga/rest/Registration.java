package com.myyoga.rest;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.myyoga.models.User;

/*
 * Registering the User and creating the User Object 
 * 
 */

@Path("/Register")
public class Registration {
	@GET
	@Path("/registerUser")
	@Produces(MediaType.APPLICATION_JSON)
	public String registerUser(@Context UriInfo info) {
		String response = "";
		int result = 3;
		String name = info.getQueryParameters().getFirst("name");
		String email = info.getQueryParameters().getFirst("email");
		String password = info.getQueryParameters().getFirst("password");
		String address = info.getQueryParameters().getFirst("address");
		String mobile = info.getQueryParameters().getFirst("mobile");
		String gender = info.getQueryParameters().getFirst("gender");
		String dob = info.getQueryParameters().getFirst("dob");
		String height = info.getQueryParameters().getFirst("height");
		String weight = info.getQueryParameters().getFirst("weight");
		User user = new User();
		user.setName(name);
		user.setAddress(address);
		user.setDob(dob);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhone(mobile);
		user.setHeight(height);
		user.setWeight(weight);
		user.setSex(gender);
		if (Utility.isNotNull(name) && Utility.isNotNull(email)
				&& Utility.isNotNull(address) && Utility.isNotNull(password)
				&& Utility.isNotNull(dob) && Utility.isNotNull(mobile)
				&& Utility.isNotNull(height) && Utility.isNotNull(weight)) {

		
			try {
				if (DBConnection.insertUser(user)) {
					result = 0;
				}
			} catch (SQLException sqle) {
				System.out.println("RegisterUSer catch sqle");
				// When Primary key violation occurs that means user is already
				// registered
				if (sqle.getErrorCode() == 1062) {
					result = 1;
				}
				// When special characters are used in name,username or password
				else if (sqle.getErrorCode() == 1064) {
					System.out.println(sqle.getErrorCode());
					result = 2;
				}
			} catch (Exception e) {
				
				System.out.println("Inside checkCredentials catch e ");
				result = 3;
			}
		} else {
			System.out.println("Inside checkCredentials else");
			result = 3;
		}
		if (result == 0) {
			response = Utility.constructJSON("register", true,user);
		} else if (result == 1) {
			response = Utility.constructJSON("register", false,
					"You are already registered");
		} else if (result == 2) {
			response = Utility
					.constructJSON("register", false,
							"Special Characters are not allowed in Username and Password");
		} else if (result == 3) {
			response = Utility.constructJSON("register", false,
					"Error occured");
		}
		return response;
	}
}
