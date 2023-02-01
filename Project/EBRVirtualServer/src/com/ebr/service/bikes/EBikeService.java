package com.ebr.service.bikes;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// beans
import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.EBike;

// db interfaces
import com.ebr.db.interfaces.IBikeDatabase;

// dbs
import com.ebr.db.JsonBikeDatabase;

@Path("/ebikes")
public class EBikeService {
	private IBikeDatabase bikeDatabase;

	public EBikeService() {
		bikeDatabase = JsonBikeDatabase.singleton();
	}

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Bike> getEBikes(@QueryParam("id") String id, @QueryParam("name") String name,
			@QueryParam("licensePlate") String licensePlate, @QueryParam("producer") String producer,
			@QueryParam("description") String description) {
		EBike ebike = new EBike(id, name, licensePlate, producer, description);
		ArrayList<Bike> res = bikeDatabase.search(ebike);
		return res;
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Bike addEBike(EBike ebike) {
		return bikeDatabase.add(ebike);
	}

	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Bike updateEBike(EBike ebike) {
		return bikeDatabase.update(ebike);
	}
}