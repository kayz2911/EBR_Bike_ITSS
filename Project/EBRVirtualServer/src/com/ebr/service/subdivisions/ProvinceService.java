package com.ebr.service.subdivisions;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// beans
import com.ebr.bean.subdivisions.Province;
import com.ebr.bean.subdivisions.Subdivision;

// db interfaces
import com.ebr.db.interfaces.ISubdivisionDatabase;

// db
import com.ebr.db.JsonSubdivisionDatabase;

@Path("/provinces")
public class ProvinceService {
	private ISubdivisionDatabase subdivisionDatabase;

	public ProvinceService() {
		subdivisionDatabase = JsonSubdivisionDatabase.singleton();
	}

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Subdivision> getProvinces(@QueryParam("code") String code, @QueryParam("name") String name) {
		Province province = new Province(code, name);
		ArrayList<Subdivision> res = subdivisionDatabase.search(province);
		return res;
	}
}