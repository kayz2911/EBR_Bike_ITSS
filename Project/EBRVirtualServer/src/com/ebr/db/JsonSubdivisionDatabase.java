package com.ebr.db;

import java.util.ArrayList;

// beans
import com.ebr.bean.subdivisions.Subdivision;

// db interfaces
import com.ebr.db.interfaces.ISubdivisionDatabase;

// db seed
import com.ebr.db.seed.Seed;

public class JsonSubdivisionDatabase implements ISubdivisionDatabase {
	private static ISubdivisionDatabase singleton = new JsonSubdivisionDatabase();

	private ArrayList<Subdivision> subdivisions = Seed.singleton().getSubdivisions();

	private JsonSubdivisionDatabase() {
	}

	public static ISubdivisionDatabase singleton() {
		return singleton;
	}

	@Override
	public ArrayList<Subdivision> search(Subdivision subdivision) {
		ArrayList<Subdivision> res = new ArrayList<Subdivision>();
		for (Subdivision s : subdivisions) {
			if (s.match(subdivision)) {
				res.add(s);
			}
		}
		return res;
	}

	@Override
	public <T> T getSubdivisionByCode(String code, Class<T> cls) {
		if (code != null && !code.equals("")) {
			for (Subdivision s : subdivisions) {
				if (cls.isInstance(s) && s.getCode().equals(code)) {
					return (T) s;
				}
			}
		}
		return null;
	}

	@Override
	public Subdivision getById(String id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean checkExistById(String id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Subdivision add(Subdivision model) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Subdivision update(Subdivision model) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean removeById(String id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
