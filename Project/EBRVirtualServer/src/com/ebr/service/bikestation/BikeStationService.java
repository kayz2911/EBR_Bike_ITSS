package com.ebr.service.bikestation;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// beans
import com.ebr.bean.Address;
import com.ebr.bean.bikestation.BikeDock;
import com.ebr.bean.bikestation.BikeStation;
import com.ebr.bean.subdivisions.District;
import com.ebr.bean.subdivisions.Province;
import com.ebr.bean.subdivisions.Ward;

// db interfaces
import com.ebr.db.interfaces.IBikeStationDatabase;
import com.ebr.db.interfaces.IBikeDockDatabase;
import com.ebr.db.interfaces.IAddressDatabase;
import com.ebr.db.interfaces.ISubdivisionDatabase;

// db
import com.ebr.db.JsonBikeStationDatabase;
import com.ebr.db.JsonBikeDockDatabase;
import com.ebr.db.JsonAddressDatabase;
import com.ebr.db.JsonSubdivisionDatabase;

@Path("/bikestations")
public class BikeStationService {
	private IBikeStationDatabase bikeStationDatabase;
	private IBikeDockDatabase bikeDockDatabase;
	private IAddressDatabase addressDatabase;
	private ISubdivisionDatabase subdivisionDatabase;

	public BikeStationService() {
		bikeStationDatabase = JsonBikeStationDatabase.singleton();
		bikeDockDatabase = JsonBikeDockDatabase.singleton();
		addressDatabase = JsonAddressDatabase.singleton();
		subdivisionDatabase = JsonSubdivisionDatabase.singleton();
	}

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<BikeStation> getBikeStations(@QueryParam("id") String id, @QueryParam("name") String name,
			@QueryParam("addressId") String addressId, @QueryParam("location") String location) {
		BikeStation bikeStation = new BikeStation(id, name, addressId);
		ArrayList<BikeStation> bikeStations = bikeStationDatabase.search(bikeStation);

		ArrayList<BikeStation> res = new ArrayList<BikeStation>();

		for (BikeStation bs : bikeStations) {
			// get address
			Address address = addressDatabase.getById(bs.getAddressId());

			// filter by address
			if (location != null && !location.equals("") && !address.toString().contains(location))
				continue;

			// set additional info: address
			bs = setBikeStationAdditionalInfo(bs);

			res.add(bs);
		}

		return res;
	}

	@GET
	@Path("/get-bike-station-by-id")
	@Produces(MediaType.APPLICATION_JSON)
	public BikeStation getById(@QueryParam("id") String id) {
		BikeStation bikeStation = bikeStationDatabase.getById(id);

		// set additional info: address
		bikeStation = setBikeStationAdditionalInfo(bikeStation);

		return bikeStation;
	}

	@GET
	@Path("/check-bike-station-have-enough-empty-bike-dock")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkBikeStationHaveEnoughEmptyBikeDock(@QueryParam("bikeStationId") String bikeStationId,
			@QueryParam("numOfRequiredBikeDocks") int numOfRequiredBikeDocks) throws IOException {
		BikeStation bikeStation = bikeStationDatabase.getById(bikeStationId);

		ArrayList<BikeDock> bikeDocks = bikeDockDatabase.getBikeDocksByBikeStationId(bikeStationId);

		if (bikeStation.getNumberOfDock() - bikeDocks.size() >= numOfRequiredBikeDocks)
			return true;

		return false;
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BikeStation add(BikeStation bikeStation) throws Exception {
		if (bikeStation.getAddress() == null)
			throw new Exception("Error: Address must be provided");

		Address address = addressDatabase.add(bikeStation.getAddress());

		// set address id
		bikeStation.setAddressId(address.getId());

		return bikeStationDatabase.add(bikeStation);
	}

	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BikeStation update(BikeStation bikeStation) throws Exception {
		if (bikeStation.getAddress() == null)
			throw new Exception("Error: Address must be provided");

		// update address
		Address address = addressDatabase.update(bikeStation.getAddress());

		return bikeStationDatabase.update(bikeStation);
	}

	// pragma region PRIVATE METHODS
	/**
	 * set bike station additional info: address
	 * 
	 * @param {BikeStation} bikeStation
	 * 
	 */
	private BikeStation setBikeStationAdditionalInfo(BikeStation bikeStation) {
		if (bikeStation == null)
			return null;

		// get address
		Address address = addressDatabase.getById(bikeStation.getAddressId());

		if (address != null) {
			// get address subdivisions
			Province province = subdivisionDatabase.getSubdivisionByCode(address.getProvinceCode(), Province.class);
			District district = subdivisionDatabase.getSubdivisionByCode(address.getDistrictCode(), District.class);
			Ward ward = subdivisionDatabase.getSubdivisionByCode(address.getWardCode(), Ward.class);

			// get address subdivisions' name
			address.setProvinceName(province.getName());
			address.setDistrictName(district.getName());
			address.setWardName(ward.getName());
		}

		// set address info
		bikeStation.setAddress(address);

		return bikeStation;
	}
	// pragma endregion
}