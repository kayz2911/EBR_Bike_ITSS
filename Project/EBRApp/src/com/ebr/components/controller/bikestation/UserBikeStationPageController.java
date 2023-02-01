package com.ebr.components.controller.bikestation;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import com.ebr.bean.bikestation.BikeStation;
import com.ebr.bean.customer.Customer;
import com.ebr.components.controller.abstractdata.ADataPageController;
import com.ebr.components.gui.abstractdata.ADataListPane;
import com.ebr.components.gui.bikestation.UserBikeStationSearchPane;
import com.ebr.components.gui.bikestation.UserBikeStationSinglePane;
import com.ebr.components.gui.bikestation.UserBikeStationListPane;

import com.ebr.serverapi.bikestation.BikeStationApi;


public class UserBikeStationPageController extends ADataPageController<BikeStation>{
	private Customer customer;
	
	public UserBikeStationPageController() {
		super();
	}
	
	public UserBikeStationPageController(Customer customer) {
		super();
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	@Override
	public List<? extends BikeStation> search(Map<String, String> searchParams) {
		return new BikeStationApi().getBikeStations(searchParams);
	}

	@Override
	public UserBikeStationSinglePane createSinglePane() {
		return new UserBikeStationSinglePane();
	}

	@Override
	public UserBikeStationSearchPane createSearchPane() {
		return new UserBikeStationSearchPane();
	}

	@Override
	public ADataListPane<BikeStation> createListPane() {
		return new UserBikeStationListPane(this);
	}
	
}
