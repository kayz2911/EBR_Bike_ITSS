package com.ebr.components.controller.payment;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.customer.Card;
import com.ebr.bean.customer.Customer;
import com.ebr.bean.order.Order;
import com.ebr.enums.OrderStatus;
import com.ebr.serverapi.customer.CardAPI;
import com.ebr.serverapi.orders.OrderApi;

public class OrderPaymentController {
	Bike bike;
	Customer customer;
	private CardAPI cardApi = new CardAPI();
	private OrderApi orderApi = new OrderApi();

	public OrderPaymentController(Bike bike,Customer customer) {
		this.bike = bike;
		this.customer = customer;
	}

	public Bike getBike() {
		return bike;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setBike(Bike bike,Customer customer) {
		this.bike = bike;
		this.customer = customer;
	}

	public Card getCardInfo() {
		return cardApi.getByCustomerId(customer.getId());
	}

	public boolean addOrder(Card card) {
		Order newOrder = new Order();
		newOrder.setBikeId(this.bike.getId());
		newOrder.setCustomerId(customer.getId());
		Order resOrder = orderApi.addOrder(newOrder);
		if (resOrder != null)
			return true;
		return false;
	}

	public Order getRentingOrderByCustomerId() {
		return orderApi.getRentingOrderByCustomerId(customer.getId());
	}

	public boolean updateOrder(String bikeStationId) {
		Order rentedOrder = getRentingOrderByCustomerId();

		Order updatedOrder = new Order();
		updatedOrder.setId(rentedOrder.getId());
		updatedOrder.setBikeId(rentedOrder.getBikeId());
		updatedOrder.setCustomerId(customer.getId());
		updatedOrder.setStatus(OrderStatus.SUCCEED);
		Order resOrder = orderApi.updateOrder(updatedOrder, bikeStationId);
		if (resOrder != null)
			return true;
		return false;
	}
}
