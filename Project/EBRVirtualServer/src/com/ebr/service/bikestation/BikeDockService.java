package com.ebr.service.bikestation;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// beans
import com.ebr.bean.bikestation.BikeDock;

// db interfaces
import com.ebr.db.interfaces.IBikeDatabase;
import com.ebr.db.interfaces.IBikeStationDatabase;
import com.ebr.db.interfaces.IBikeDockDatabase;

//db
import com.ebr.db.JsonBikeDatabase;
import com.ebr.db.JsonBikeStationDatabase;
import com.ebr.db.JsonBikeDockDatabase;

@Path("/bikedocks")
public class BikeDockService {
	private IBikeDatabase bikeDatabase;
	private IBikeStationDatabase bikeStationDatabase;
	private IBikeDockDatabase bikeDockDatabase;

	public BikeDockService() {
		bikeDatabase = JsonBikeDatabase.singleton();
		bikeStationDatabase = JsonBikeStationDatabase.singleton();
		bikeDockDatabase = JsonBikeDockDatabase.singleton();
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<BikeDock> getBikeDocks(@QueryParam("id") String id,
			@QueryParam("bikeStationId") String bikeStationId, @QueryParam("bikeId") String bikeId) {
		BikeDock bikeDock = new BikeDock(id, bikeStationId, bikeId);
		ArrayList<BikeDock> res = bikeDockDatabase.search(bikeDock);

		
		for (BikeDock bd : res) {
			// set bike station info
			bd.setBikeStation(bikeStationDatabase.getById(bd.getBikeStationId()));

			// set bike info
			bd.setBike(bikeDatabase.getById(bd.getBikeId()));
		}

		return res;
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BikeDock add(BikeDock bikeDock) {
		return bikeDockDatabase.add(bikeDock);
	}

	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BikeDock update(BikeDock bikeDock) {
		return bikeDockDatabase.update(bikeDock);
	}
}