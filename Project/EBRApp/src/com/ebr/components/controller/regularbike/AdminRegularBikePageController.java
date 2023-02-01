package com.ebr.components.controller.regularbike;

import java.util.List;
import java.util.Map;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.RegularBike;
import com.ebr.components.controller.bike.AdminBikePageController;
import com.ebr.components.gui.bike.BikeSearchPane;
import com.ebr.components.gui.bike.BikeSinglePane;
import com.ebr.components.gui.regularbike.RegularBikeSearchPane;
import com.ebr.components.gui.regularbike.RegularBikeSinglePane;
import com.ebr.serverapi.bikes.RegularBikeApi;

public class AdminRegularBikePageController extends AdminBikePageController {
	@Override
	public List<? extends Bike> search(Map<String, String> searchParams) {
		return new RegularBikeApi().getRegularBikes(searchParams);
	}

	@Override
	public BikeSinglePane createSinglePane() {
		return new RegularBikeSinglePane();
	}

	@Override
	public BikeSearchPane createSearchPane() {
		return new RegularBikeSearchPane();
	}

	@Override
	public Bike addBike(Bike bike) {
		return new RegularBikeApi().addRegularBike((RegularBike) bike);
	}

	@Override
	public Bike updateBike(Bike bike) {
		return new RegularBikeApi().updateRegularBike((RegularBike) bike);
	}
}
