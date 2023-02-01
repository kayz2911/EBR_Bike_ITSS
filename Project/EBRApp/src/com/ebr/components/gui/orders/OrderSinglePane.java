package com.ebr.components.gui.orders;

import javax.swing.JLabel;

import com.ebr.bean.order.Order;
import com.ebr.components.gui.abstractdata.ADataSinglePane;
import com.ebr.constants.Constants;

@SuppressWarnings("serial")
public class OrderSinglePane extends ADataSinglePane<Order> {

	private JLabel labelId;
	private JLabel labelCreatedTime;
	private JLabel labelUpdatedTime;
	private JLabel labelStatus;
	private JLabel labelCustomerName;
	private JLabel labelCustomerPhone;
	private JLabel labelBikeName;
	private JLabel labelTotalRentTime;
	private JLabel labelTotalRentPrice;

	public OrderSinglePane() {
		super();
	}

	public OrderSinglePane(Order order) {
		this();
		this.t = order;

		displayData();
	}

	@Override
	public void buildControls() {
		super.buildControls();

		// Name
		int row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelId = new JLabel();
		add(labelId, c);

		// Created time
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelCreatedTime = new JLabel();
		add(labelCreatedTime, c);

		// Updated time
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelUpdatedTime = new JLabel();
		add(labelUpdatedTime, c);

		// Status
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelStatus = new JLabel();
		add(labelStatus, c);

		// Customer name
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelCustomerName = new JLabel();
		add(labelCustomerName, c);

		// Customer phone
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelCustomerPhone = new JLabel();
		add(labelCustomerPhone, c);

		// Bike name
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelBikeName = new JLabel();
		add(labelBikeName, c);

		// Total rent time
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelTotalRentTime = new JLabel();
		add(labelTotalRentTime, c);

		// Total rent price
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelTotalRentPrice = new JLabel();
		add(labelTotalRentPrice, c);
	}

	@Override
	public void displayData() {
		labelId.setText("Id: " + t.getId());
		labelCreatedTime.setText("Created time: " + t.getCreatedTime());
		labelUpdatedTime.setText("Updated time: " + t.getUpdatedTime());
		labelStatus.setText("Status: " + t.getStatus().getStatusStr());
		labelCustomerName.setText(
				"Customer name: " + t.getCustomerInfo().getFirstName() + " " + t.getCustomerInfo().getLastName());
		labelCustomerPhone.setText("Customer phone: " + t.getCustomerInfo().getPhoneNumber());
		labelBikeName.setText("Bike name: " + t.getBikeInfo().getName());
		labelTotalRentTime.setText("Total rent time: " + String.format("%,.0f mins", (float)t.calculateTotalRentTime()));
		labelTotalRentPrice.setText("Total rent price: " + Constants.SystemSettings.CURRENCY_PREFIX
				+ String.format("%,.0f", t.calculateTotalRentPrice()) + Constants.SystemSettings.CURRENCY_SUFFIX);
	}

}
