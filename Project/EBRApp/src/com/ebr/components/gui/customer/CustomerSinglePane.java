package com.ebr.components.gui.customer;

import javax.swing.JLabel;

import com.ebr.bean.bikestation.BikeStation;
import com.ebr.bean.customer.Customer;
import com.ebr.components.gui.abstractdata.ADataSinglePane;
@SuppressWarnings("serial")
public class CustomerSinglePane extends ADataSinglePane<Customer>{
	private JLabel labelId;
	private JLabel labelFirstName;
	private JLabel labelLastName;
	private JLabel labelPhoneNumber;
	private JLabel labelCardId;
	
	public CustomerSinglePane() {
		super();
	}

	public CustomerSinglePane(Customer customer) {
		this();
		this.t = customer;

		displayData();
	}
	@Override
	public void buildControls() {
		super.buildControls();

		// ID
		int row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelId = new JLabel();
		add(labelId, c);

		// first name
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelFirstName = new JLabel();
		add(labelFirstName, c);

		// last name
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelLastName = new JLabel();
		add(labelLastName, c);

		// Phone number
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelPhoneNumber = new JLabel();
		add(labelPhoneNumber, c);

		// Card Id
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelCardId = new JLabel();
		add(labelCardId, c);

	}
	@Override
	public void displayData() {
		// TODO Auto-generated method stub
		labelId.setText("Id : " + t.getId());
		labelFirstName.setText("First name : " + t.getFirstName());
		labelLastName.setText("Last name : "+ t.getLastName());
		labelPhoneNumber.setText("Phone number : " + t.getPhoneNumber());
		labelCardId.setText("Card Id : " + t.getCardId());
	}

}
