package com.ebr.db;

import java.util.ArrayList;

// beans
import com.ebr.bean.bikestation.BikeDock;

// db interfaces
import com.ebr.db.interfaces.IBikeDockDatabase;

// db seed
import com.ebr.db.seed.Seed;

// utils
import com.ebr.utils.Utils;

public class JsonBikeDockDatabase implements IBikeDockDatabase {
	private static IBikeDockDatabase singleton = new JsonBikeDockDatabase();

	private ArrayList<BikeDock> bikeDocks = Seed.singleton().getBikeDocks();

	private JsonBikeDockDatabase() {
	}

	public static IBikeDockDatabase singleton() {
		return singleton;
	}

	@Override
	public ArrayList<BikeDock> search(BikeDock bikeDock) {
		ArrayList<BikeDock> res = new ArrayList<BikeDock>();
		for (BikeDock bd : bikeDocks) {
			if (bd.match(bikeDock)) {
				res.add(bd);
			}
		}
		return res;
	}

	@Override
	public ArrayList<BikeDock> getBikeDocksByBikeStationId(String bikeStationId) {
		ArrayList<BikeDock> res = new ArrayList<BikeDock>();
		for (BikeDock bd : bikeDocks) {
			if (bd.getBikeStationId().equals(bikeStationId)) {
				res.add(bd);
			}
		}
		return res;
	}

	@Override
	public BikeDock add(BikeDock bikeDock) {
		if(bikeDock != null) {
			// check bike dock exist
			for (BikeDock bd : bikeDocks) {
				if (bd.equals(bikeDock)) {
					return null;
				}
			}

			if (bikeDock.getId() == null || bikeDock.getId().equals(""))
				bikeDock.setId(Utils.generateUUID());

			bikeDocks.add(bikeDock);
		}
		return bikeDock;
	}

	@Override
	public BikeDock update(BikeDock bikeDock) {
		for (BikeDock bd : bikeDocks) {
			if (bd.equals(bikeDock)) {
				bikeDocks.remove(bd);
				
				bikeDocks.add(bikeDock);
				return bikeDock;
			}
		}

		return null;
	}

	@Override
	public boolean removeByBikeId(String bikeId) {
		if (bikeId != null) {
			for (BikeDock bd : bikeDocks) {
				if (bd.getBikeId().equals(bikeId)) {
					bikeDocks.remove(bd);
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public BikeDock getById(String id) {
		throw new UnsupportedOperationException("Not supported yet.");
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
