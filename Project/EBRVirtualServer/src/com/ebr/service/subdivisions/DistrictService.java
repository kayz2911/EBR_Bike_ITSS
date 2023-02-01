package com.ebr.service.subdivisions;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// beans
import com.ebr.bean.subdivisions.District;
import com.ebr.bean.subdivisions.Subdivision;

// db interfaces
import com.ebr.db.interfaces.ISubdivisionDatabase;

// db
import com.ebr.db.JsonSubdivisionDatabase;

@Path("/districts")
public class DistrictService {
	private ISubdivisionDatabase subdivisionDatabase;

	public DistrictService() {
		subdivisionDatabase = JsonSubdivisionDatabase.singleton();
	}

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Subdivision> getDistricts(@QueryParam("code") String code, @QueryParam("name") String name,
			@QueryParam("provinceCode") String provinceCode) {
		District district = new District(code, name, provinceCode);
		ArrayList<Subdivision> res = subdivisionDatabase.search(district);
		return res;
	}
}