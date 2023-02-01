package com.ebr.db;

import java.util.ArrayList;

// enums
import com.ebr.enums.BikeStatus;

// beans
import com.ebr.bean.bikes.Bike;

// db interfaces
import com.ebr.db.interfaces.IBikeDatabase;

// db seed
import com.ebr.db.seed.Seed;

// utils
import com.ebr.utils.Utils;

public class JsonBikeDatabase implements IBikeDatabase {
	private static IBikeDatabase singleton = new JsonBikeDatabase();

	private ArrayList<Bike> bikes = Seed.singleton().getBikes();

	private JsonBikeDatabase() {
	}

	public static IBikeDatabase singleton() {
		return singleton;
	}

	@Override
	public ArrayList<Bike> search(Bike bike) {
		ArrayList<Bike> res = new ArrayList<Bike>();
		for (Bike b : bikes) {
			if (b.match(bike)) {
				res.add(b);
			}
		}
		return res;
	}

	@Override
	public Bike getById(String bikeId) {
		if (bikeId != null) {
			for (Bike b : bikes) {
				if (b.getId().equals(bikeId)) {
					return b;
				}
			}
		}
		return null;
	}

	@Override
	public Bike add(Bike bike) {
		for (Bike b : bikes) {
			if (b.equals(bike)) {
				return null;
			}
		}

		if (bike.getId() == null || bike.getId().equals(""))
			bike.setId(Utils.generateUUID());
		
		bikes.add(bike);
		
		return bike;
	}

	@Override
	public Bike update(Bike bike) {
		for (Bike b : bikes) {
			if (b.equals(bike)) {
				bikes.remove(b);
				bikes.add(bike);
				return bike;
			}
		}
		
		return null;
	}

	@Override
	public boolean checkBikeAvailableByBikeId(String bikeId) {
		if (bikeId != null && !bikeId.equals("")) {
			for (Bike b : bikes) {
				if (b.getId().equals(bikeId) && b.getStatus() == BikeStatus.AVAILABLE) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean updateBikeStatusByBikeId(String bikeId, BikeStatus newStatus) {
		if (bikeId != null && !bikeId.equals("")) {
			for (Bike b : bikes) {
				if (b.getId().equals(bikeId)) {
					b.setStatus(newStatus);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean checkExistById(String id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean removeById(String id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
