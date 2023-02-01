package com.ebr.db.interfaces;

import java.util.ArrayList;

// beans
import com.ebr.bean.subdivisions.Subdivision;

public interface ISubdivisionDatabase extends IDataManageDatabase<Subdivision> {
	public ArrayList<Subdivision> search(Subdivision subdivision);

	public <T> T getSubdivisionByCode(String code, Class<T> cls);
}
