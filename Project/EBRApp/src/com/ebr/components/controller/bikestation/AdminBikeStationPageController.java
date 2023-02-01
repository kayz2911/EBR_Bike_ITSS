package com.ebr.components.controller.bikestation;

import java.util.List;
import java.util.Map;

import com.ebr.bean.bikestation.BikeStation;
import com.ebr.components.controller.abstractdata.ADataPageController;
import com.ebr.components.gui.abstractdata.ADataListPane;
import com.ebr.components.gui.bikestation.AdminBikeStationListPane;
import com.ebr.components.gui.bikestation.BikeStationSearchPane;
import com.ebr.components.gui.bikestation.BikeStationSinglePane;
import com.ebr.serverapi.bikestation.BikeStationApi;

public class AdminBikeStationPageController extends ADataPageController<BikeStation> {
	@Override
	public List<? extends BikeStation> search(Map<String, String> searchParams) {
		return new BikeStationApi().getBikeStations(searchParams);
	}

	@Override
	public ADataListPane<BikeStation> createListPane() {
		return new AdminBikeStationListPane(this);
	}

	@Override
	public BikeStationSinglePane createSinglePane() {
		return new BikeStationSinglePane();
	}

	@Override
	public BikeStationSearchPane createSearchPane() {
		return new BikeStationSearchPane();
	}

	public BikeStation addBikeStation(BikeStation bikeStation) {
		return new BikeStationApi().addBikeStation((BikeStation) bikeStation);
	}

	public BikeStation updateBikeStation(BikeStation bikeStation) {
		return new BikeStationApi().updateBikeStation((BikeStation) bikeStation);
	}
}
