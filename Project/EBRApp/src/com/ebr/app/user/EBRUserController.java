package com.ebr.app.user;

import javax.swing.*;

import com.ebr.serverapi.customer.CustomerAPI;
import com.ebr.bean.bikestation.BikeStation;
import com.ebr.bean.customer.Customer;
import com.ebr.components.controller.abstractdata.ADataPageController;
import com.ebr.components.controller.bikestation.UserBikeStationPageController;

public class EBRUserController {
	private Customer customer;
	
	public EBRUserController() {
		super();
	}
	
	public EBRUserController(Customer customer) {
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public String getCustomerInfo() {
		Customer cus = new CustomerAPI().getById(customer.getId());
		return cus.getLastName();
	}
	
	public JPanel getCustomerBikeRentedInfo() {
		ADataPageController<BikeStation> controller = new UserBikeStationPageController();
		return controller.getDataPagePane();
	}
	
	public JPanel getBikeStationPage() {
		ADataPageController<BikeStation> controller = new UserBikeStationPageController(this.customer);
		return controller.getDataPagePane();
	}
}
