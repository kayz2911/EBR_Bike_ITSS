package com.ebr.components.controller.bike;

// beans
import com.ebr.bean.bikes.Bike;
import com.ebr.components.controller.abstractdata.ADataPageController;
import com.ebr.components.gui.abstractdata.ADataListPane;
import com.ebr.components.gui.bike.AdminBikeListPane;

public abstract class AdminBikePageController extends ADataPageController<Bike> {
	public AdminBikePageController() {
		super();
	}
	
	@Override
	public ADataListPane<Bike> createListPane() {
		return new AdminBikeListPane(this);
	}

	public abstract Bike addBike(Bike bike);
	public abstract Bike updateBike(Bike bike);
}
