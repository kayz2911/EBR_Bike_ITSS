package com.ebr.service.bikes;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// beans
import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.EBike;
import com.ebr.bean.bikes.RegularBike;
import com.ebr.bean.bikes.TwinBike;
import com.ebr.bean.bikestation.BikeDock;

// db interfaces
import com.ebr.db.interfaces.IBikeDatabase;
import com.ebr.db.interfaces.IBikeDockDatabase;

// dbs
import com.ebr.db.JsonBikeDatabase;
import com.ebr.db.JsonBikeDockDatabase;

@Path("/bikes")
public class BikeService {
	private IBikeDatabase bikeDatabase;
	private IBikeDockDatabase bikeDockDatabase;

	public BikeService() {
		bikeDatabase = JsonBikeDatabase.singleton();
		bikeDockDatabase = JsonBikeDockDatabase.singleton();
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Bike> getAllBikes() {
		ArrayList<Bike> res = bikeDatabase.search(null);
		return res;
	}

	@GET
	@Path("/get-bikes-by-bike-station-id")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Bike> getBikesByBikeStationId(@QueryParam("bikeStationId") String bikeStationId,
			@QueryParam("bikeName") String bikeName, @QueryParam("bikeLicensePlate") String bikeLicensePlate,
			@QueryParam("bikeProducer") String bikeProducer, @QueryParam("bikeDescription") String bikeDescription) {
		ArrayList<Bike> res = new ArrayList<Bike>();

		ArrayList<BikeDock> bikeDocks = bikeDockDatabase.getBikeDocksByBikeStationId(bikeStationId);

		for (BikeDock bd : bikeDocks) {
			Bike bike = bikeDatabase.getById(bd.getBikeId());
			Bike testRegularBike = new RegularBike(bikeName, bikeLicensePlate, bikeProducer, bikeDescription);
			Bike testEBike = new EBike(bikeName, bikeLicensePlate, bikeProducer, bikeDescription);
			Bike testTwinBike = new TwinBike(bikeName, bikeLicensePlate, bikeProducer, bikeDescription);
			if (bike.match(testRegularBike) || bike.match(testEBike) || bike.match(testTwinBike))
				res.add(bike);
		}

		return res;
	}

	@GET
	@Path("/get-bike-by-id")
	@Produces(MediaType.APPLICATION_JSON)
	public Bike getBikeById(@QueryParam("bikeId") String bikeId) {
		Bike res = bikeDatabase.getById(bikeId);

		return res;
	}
}