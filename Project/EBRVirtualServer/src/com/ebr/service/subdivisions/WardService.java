package com.ebr.service.subdivisions;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// beans
import com.ebr.bean.subdivisions.Subdivision;
import com.ebr.bean.subdivisions.Ward;

// db interfaces
import com.ebr.db.interfaces.ISubdivisionDatabase;

// db
import com.ebr.db.JsonSubdivisionDatabase;

@Path("/wards")
public class WardService {
	private ISubdivisionDatabase subdivisionDatabase;

	public WardService() {
		subdivisionDatabase = JsonSubdivisionDatabase.singleton();
	}

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Subdivision> getWards(@QueryParam("code") String code, @QueryParam("name") String name,
			@QueryParam("districtCode") String districtCode) {
		Ward ward = new Ward(code, name, districtCode);
		ArrayList<Subdivision> res = subdivisionDatabase.search(ward);
		return res;
	}
}