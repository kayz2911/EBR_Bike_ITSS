package com.ebr.db.interfaces;

import java.util.ArrayList;

// enums
import com.ebr.enums.BikeStatus;
import com.ebr.bean.Address;
// beans
import com.ebr.bean.bikes.Bike;

public interface IBikeDatabase extends IDataManageDatabase<Bike>, IDataSearchDatabase<Bike> {
	public boolean checkBikeAvailableByBikeId(String bikeId);

	public boolean updateBikeStatusByBikeId(String bikeId, BikeStatus newStatus);
}
