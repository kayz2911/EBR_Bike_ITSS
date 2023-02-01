package com.ebr.components.controller.twinbike;

import java.util.List;
import java.util.Map;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.TwinBike;
import com.ebr.components.controller.bike.AdminBikePageController;
import com.ebr.components.gui.bike.BikeSearchPane;
import com.ebr.components.gui.bike.BikeSinglePane;
import com.ebr.components.gui.twinbike.TwinBikeSearchPane;
import com.ebr.components.gui.twinbike.TwinBikeSinglePane;
import com.ebr.serverapi.bikes.TwinBikeApi;

public class AdminTwinBikePageController extends AdminBikePageController {
	@Override
	public List<? extends Bike> search(Map<String, String> searchParams) {
		return new TwinBikeApi().getTwinBikes(searchParams);
	}

	@Override
	public BikeSinglePane createSinglePane() {
		return new TwinBikeSinglePane();
	}

	@Override
	public BikeSearchPane createSearchPane() {
		return new TwinBikeSearchPane();
	}

	@Override
	public Bike addBike(Bike bike) {
		return new TwinBikeApi().addTwinBike((TwinBike) bike);
	}

	@Override
	public Bike updateBike(Bike bike) {
		return new TwinBikeApi().updateTwinBike((TwinBike) bike);
	}
}
