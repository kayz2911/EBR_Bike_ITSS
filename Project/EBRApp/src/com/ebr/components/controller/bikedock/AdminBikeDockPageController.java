package com.ebr.components.controller.bikedock;

import java.util.List;
import java.util.Map;

import com.ebr.bean.bikestation.BikeDock;
import com.ebr.components.controller.abstractdata.ADataPageController;
import com.ebr.components.gui.abstractdata.ADataListPane;
import com.ebr.components.gui.bikedock.AdminBikeDockListPane;
import com.ebr.components.gui.bikedock.BikeDockSearchPane;
import com.ebr.components.gui.bikedock.BikeDockSinglePane;
import com.ebr.serverapi.bikestation.BikeDockApi;

public class AdminBikeDockPageController extends ADataPageController<BikeDock> {
	@Override
	public List<? extends BikeDock> search(Map<String, String> searchParams) {
		return new BikeDockApi().getBikeDocks(searchParams);
	}

	@Override
	public ADataListPane<BikeDock> createListPane() {
		return new AdminBikeDockListPane(this);
	}

	@Override
	public BikeDockSinglePane createSinglePane() {
		return new BikeDockSinglePane();
	}

	@Override
	public BikeDockSearchPane createSearchPane() {
		return new BikeDockSearchPane();
	}

	public BikeDock addBikeDock(BikeDock bikeDock) {
		return new BikeDockApi().addBikeDock((BikeDock) bikeDock);
	}

	public BikeDock updateBikeDock(BikeDock bikeDock) {
		return new BikeDockApi().updateBikeDock((BikeDock) bikeDock);
	}
}
