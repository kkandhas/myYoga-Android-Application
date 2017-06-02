package com.myyoga.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.myyoga.models.YogaAsanas;
/*
 * Fetching the Yoga by making the rest api call
 * 
 */
@Path("/yoga")
public class YogaDetails {
	@GET
	@Path("/fetchYoga")
	@Produces(MediaType.APPLICATION_JSON)
	public String fetchYogaAsanaDetail(@QueryParam("yoganame") String yName){
		String response = "";
		YogaAsanas yogaAsanas = fetchYogaDetails(yName);
		if(yogaAsanas != null){
			response = Utility.constructJSON("Asana", true, yogaAsanas);
		} else {
			response = Utility.constructJSON("Asana", false);
		}
		return response;
	}

	private YogaAsanas fetchYogaDetails(String yName) {
		YogaAsanas yogaAsanas = null;
		if(Utility.isNotNull(yName)){
			try{
				yogaAsanas = DBConnection.fetchAsanaDetails(yName);
			} catch(Exception e){
				e.printStackTrace();			
			}
		}
		return yogaAsanas;
	}
}
