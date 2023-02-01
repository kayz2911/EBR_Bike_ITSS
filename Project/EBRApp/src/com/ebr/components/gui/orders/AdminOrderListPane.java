package com.ebr.components.gui.orders;

import com.ebr.bean.bikestation.BikeStation;
import com.ebr.bean.order.Order;
import com.ebr.components.controller.abstractdata.ADataPageController;
import com.ebr.components.gui.abstractdata.ADataListPane;
import com.ebr.components.gui.abstractdata.ADataSinglePane;

@SuppressWarnings("serial")
public class AdminOrderListPane extends ADataListPane<Order>{

	public AdminOrderListPane(ADataPageController<Order> controller) {
		this.controller = controller;
	}
	
	@Override
	public void decorateSinglePane(ADataSinglePane<Order> singlePane) {
		// TODO Auto-generated method stub
		
	}

}
