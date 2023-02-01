package com.ebr.service.bikes;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// beans
import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.TwinBike;

// db interfaces
import com.ebr.db.interfaces.IBikeDatabase;

// dbs
import com.ebr.db.JsonBikeDatabase;

@Path("/twinbikes")
public class TwinBikeService {
	private IBikeDatabase bikeDatabase;

	public TwinBikeService() {
		bikeDatabase = JsonBikeDatabase.singleton();
	}

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Bike> getTwinBikes(@QueryParam("id") String id, @QueryParam("name") String name,
			@QueryParam("licensePlate") String licensePlate, @QueryParam("producer") String producer,
			@QueryParam("description") String description) {
		TwinBike twinbike = new TwinBike(id, name, licensePlate, producer, description);
		ArrayList<Bike> res = bikeDatabase.search(twinbike);
		return res;
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Bike addTwinBike(TwinBike twinbike) {
		return bikeDatabase.add(twinbike);
	}

	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Bike updateTwinBike(TwinBike twinbike) {
		return bikeDatabase.update(twinbike);
	}
}