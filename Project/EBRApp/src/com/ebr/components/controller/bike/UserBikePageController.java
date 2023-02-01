package com.ebr.components.controller.bike;

import java.util.List;
import java.util.Map;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.customer.Customer;
import com.ebr.bean.order.Order;
import com.ebr.components.controller.abstractdata.ADataPageController;
import com.ebr.components.controller.abstractdata.IDataSearchController;
import com.ebr.components.controller.payment.OrderPaymentController;
import com.ebr.components.gui.abstractdata.ADataListPane;
import com.ebr.components.gui.abstractdata.ADataSearchPane;
import com.ebr.components.gui.abstractdata.DataPagePane;
import com.ebr.components.gui.bike.UserBikeSearchPane;
import com.ebr.components.gui.bike.UserBikeSinglePane;
import com.ebr.components.gui.bike.UserBikeListPane;
import com.ebr.serverapi.bikes.BikeApi;
import com.ebr.serverapi.orders.OrderApi;

public class UserBikePageController extends ADataPageController<Bike> {
	private OrderPaymentController orderPaymentController;
	private Customer customer;

	public UserBikePageController() {
		super();
	}

	public UserBikePageController(String bikeStationId,Customer customer) {
		ADataSearchPane searchPane = createSearchPane();
		ADataListPane<Bike> listPane = createListPane();
		this.customer = customer;

		searchPane.setController(new IDataSearchController() {
			@Override
			public void search(Map<String, String> searchParams) {
				List<? extends Bike> list = UserBikePageController.this.searchByStationId(bikeStationId, searchParams);
				listPane.updateData(list);
			}
		});

		searchPane.fireSearchEvent();
		pagePane = new DataPagePane<Bike>(searchPane, listPane);
	}

	public UserBikePageController(OrderPaymentController orderPaymentController,Customer customer) {
		this();
		setOrderPaymentController(orderPaymentController);
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setOrderPaymentController(OrderPaymentController orderPaymentController) {
		this.orderPaymentController = orderPaymentController;
	}
	
	public boolean getRentingOrderByCustomerId() {
		Order order = new OrderApi().getRentingOrderByCustomerId(customer.getId());
		if(order != null ) return true;
		else return false;
	}

	public List<? extends Bike> searchByStationId(String bikeStationId, Map<String, String> searchParams) {
		return new BikeApi().getBikesByBikeStationId(bikeStationId, searchParams);
	}

	@Override
	public List<? extends Bike> search(Map<String, String> searchParams) {
		return new BikeApi().getAllBikes();
	}

	@Override
	public ADataListPane<Bike> createListPane() {
		return new UserBikeListPane(this);
	}

	@Override
	public UserBikeSinglePane createSinglePane() {
		return new UserBikeSinglePane();
	}

	@Override
	public UserBikeSearchPane createSearchPane() {
		return new UserBikeSearchPane();
	}

}
