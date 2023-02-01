package com.ebr.service.bikes;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// beans
import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.RegularBike;

// db interfaces
import com.ebr.db.interfaces.IBikeDatabase;

// dbs
import com.ebr.db.JsonBikeDatabase;

@Path("/regularbikes")
public class RegularBikeService {
	private IBikeDatabase bikeDatabase;

	public RegularBikeService() {
		bikeDatabase = JsonBikeDatabase.singleton();
	}

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Bike> getRegularBikes(@QueryParam("id") String id, @QueryParam("name") String name,
			@QueryParam("licensePlate") String licensePlate, @QueryParam("producer") String producer,
			@QueryParam("description") String description) {
		RegularBike regularBike = new RegularBike(id, name, licensePlate, producer, description);
		ArrayList<Bike> res = bikeDatabase.search(regularBike);
		return res;
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Bike addRegularBike(RegularBike regularBike) {
		return bikeDatabase.add(regularBike);
	}

	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Bike updateRegularBike(RegularBike regularBike) {
		return bikeDatabase.update(regularBike);
	}
}