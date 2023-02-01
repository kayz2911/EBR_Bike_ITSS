package com.ebr.db.interfaces;

import java.util.ArrayList;

// beans
import com.ebr.bean.bikestation.BikeDock;

public interface IBikeDockDatabase extends IDataManageDatabase<BikeDock>, IDataSearchDatabase<BikeDock> {
	public ArrayList<BikeDock> getBikeDocksByBikeStationId(String bikeStationId);

	public boolean removeByBikeId(String bikeId);
}
