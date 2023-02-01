package com.ebr.db;

import java.util.ArrayList;

// beans
import com.ebr.bean.bikestation.BikeStation;

// db interfaces
import com.ebr.db.interfaces.IBikeStationDatabase;

// db seed
import com.ebr.db.seed.Seed;

// utils
import com.ebr.utils.Utils;

public class JsonBikeStationDatabase implements IBikeStationDatabase {
	private static IBikeStationDatabase singleton = new JsonBikeStationDatabase();

	private ArrayList<BikeStation> bikeStations = Seed.singleton().getBikeStations();

	private JsonBikeStationDatabase() {
	}

	public static IBikeStationDatabase singleton() {
		return singleton;
	}

	@Override
	public ArrayList<BikeStation> search(BikeStation bikeStation) {
		ArrayList<BikeStation> res = new ArrayList<BikeStation>();
		
		for (BikeStation bs : bikeStations) {
			if (bs.match(bikeStation)) {
				res.add(bs);
			}
		}
		
		return res;
	}

	@Override
	public boolean checkExistById(String bikeStationId) {
		if (bikeStationId != null) {
			for (BikeStation bs : bikeStations) {
				if (bs.getId().equals(bikeStationId)) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public BikeStation getById(String bikeStationId) {
		if (bikeStationId != null) {
			for (BikeStation bs : bikeStations) {
				if (bs.getId().equals(bikeStationId)) {
					return bs;
				}
			}
		}
		
		return null;
	}

	@Override
	public BikeStation add(BikeStation bikeStation) {
		for (BikeStation bs : bikeStations) {
			if (bs.equals(bikeStation)) {
				return null;
			}
		}

		if (bikeStation.getId() == null || bikeStation.getId().equals(""))
			bikeStation.setId(Utils.generateUUID());

		bikeStations.add(bikeStation);

		return bikeStation;
	}

	@Override
	public BikeStation update(BikeStation bikeStation) {
		for (BikeStation bs : bikeStations) {
			if (bs.equals(bikeStation)) {
				bikeStations.remove(bs);
				bikeStations.add(bikeStation);
				return bikeStation;
			}
		}

		return null;
	}

	@Override
	public boolean removeById(String id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
