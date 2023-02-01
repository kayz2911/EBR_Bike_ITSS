package com.ebr.components.gui.orders;

import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.ebr.components.gui.abstractdata.ADataSearchPane;

public class OrderSearchPane extends ADataSearchPane{

	private JTextField idField;
	private JTextField customerIdField;
	private JTextField bikeIdField;
	
	public OrderSearchPane() {
		super();
	}
	@Override
	public void buildControls() {
		// TODO Auto-generated method stub
		Border emptyBorder = new EmptyBorder(0, 0, 0, 5);
		
		// Id
		int row = getLastRowIndex();
		JLabel idLabel = new JLabel("id");
		idLabel.setBorder(emptyBorder);
		idField = new JTextField(20);
		c.gridx = 0;
		c.gridy = row;
		add(idLabel, c);
		c.gridx = 1;
		c.gridy = row;
		add(idField, c);		
	
		// Name
		row = getLastRowIndex();
		JLabel nameLabel = new JLabel("Customer Id");
		nameLabel.setBorder(emptyBorder);
		customerIdField = new JTextField(20);
		c.gridx = 0;
		c.gridy = row;
		add(nameLabel, c);
		c.gridx = 1;
		c.gridy = row;
		add(customerIdField, c);
		
		// Location
		row = getLastRowIndex();
		JLabel locationLabel = new JLabel("Bike Id");
		locationLabel.setBorder(emptyBorder);
		bikeIdField = new JTextField(20);
		c.gridx = 0;
		c.gridy = row;
		add(locationLabel, c);
		c.gridx = 1;
		c.gridy = row;
		add(bikeIdField, c);
		
	}
	
	@Override
	public Map<String, String> getQueryParams() {
		Map<String, String> res = super.getQueryParams();

		// Id
		if (!idField.getText().trim().equals("")) {
			res.put("id", idField.getText().trim());
		}

		// Name
		if (!customerIdField.getText().trim().equals("")) {
			res.put("customerId", customerIdField.getText().trim());
		}

		// Location
		if (!bikeIdField.getText().trim().equals("")) {
			res.put("bikeId", bikeIdField.getText().trim());
		}

		return res;
	}
}
