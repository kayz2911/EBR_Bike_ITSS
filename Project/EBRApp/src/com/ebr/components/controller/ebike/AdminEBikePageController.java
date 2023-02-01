package com.ebr.components.controller.ebike;

import java.util.List;
import java.util.Map;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.EBike;
import com.ebr.components.controller.bike.AdminBikePageController;
import com.ebr.components.gui.abstractdata.ADataListPane;
import com.ebr.components.gui.bike.BikeSearchPane;
import com.ebr.components.gui.bike.BikeSinglePane;
import com.ebr.components.gui.ebike.AdminEBikeListPane;
import com.ebr.components.gui.ebike.EBikeSearchPane;
import com.ebr.components.gui.ebike.EBikeSinglePane;
import com.ebr.serverapi.bikes.EBikeApi;

public class AdminEBikePageController extends AdminBikePageController {
	@Override
	public List<? extends Bike> search(Map<String, String> searchParams) {
		return new EBikeApi().getEBikes(searchParams);
	}
	
	@Override
	public ADataListPane<Bike> createListPane() {
		return new AdminEBikeListPane(this);
	}

	@Override
	public BikeSinglePane createSinglePane() {
		return new EBikeSinglePane();
	}

	@Override
	public BikeSearchPane createSearchPane() {
		return new EBikeSearchPane();
	}

	@Override
	public Bike addBike(Bike bike) {
		return new EBikeApi().addEBike((EBike) bike);
	}

	@Override
	public Bike updateBike(Bike bike) {
		return new EBikeApi().updateEBike((EBike) bike);
	}
}
