package com.ebr.components.controller.order;

import java.util.List;
import java.util.Map;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.order.Order;
import com.ebr.components.controller.abstractdata.ADataPageController;
import com.ebr.components.gui.abstractdata.ADataListPane;
import com.ebr.components.gui.abstractdata.ADataSearchPane;
import com.ebr.components.gui.abstractdata.ADataSinglePane;
import com.ebr.components.gui.bikestation.AdminBikeStationListPane;
import com.ebr.components.gui.bikestation.BikeStationSinglePane;
import com.ebr.components.gui.orders.AdminOrderListPane;
import com.ebr.components.gui.orders.OrderSearchPane;
import com.ebr.components.gui.orders.OrderSinglePane;
import com.ebr.serverapi.orders.OrderApi;

public class AdminOrderPageController extends ADataPageController<Order>{

	@Override
	public ADataSearchPane createSearchPane() {
		// TODO Auto-generated method stub
		return new OrderSearchPane();
	}

	@Override
	public List<? extends Order> search(Map<String, String> searchParams) {
		// TODO Auto-generated method stub
		return new OrderApi().getOrders(searchParams);
	}

	@Override
	public OrderSinglePane createSinglePane() {
		// TODO Auto-generated method stub
		return new OrderSinglePane();
	}


	@Override
	public ADataListPane<Order> createListPane() {
		// TODO Auto-generated method stub
		return new AdminOrderListPane(this);
	}
}
