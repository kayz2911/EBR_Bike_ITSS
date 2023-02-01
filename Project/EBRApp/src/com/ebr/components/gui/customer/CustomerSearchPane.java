package com.ebr.components.gui.customer;

import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.ebr.components.gui.abstractdata.ADataSearchPane;

@SuppressWarnings("serial")
public class CustomerSearchPane extends ADataSearchPane {

	private JTextField idField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField phoneNumberField;

	public CustomerSearchPane() {
		super();
	}

	@Override
	public void buildControls() {
		// TODO Auto-generated method stub
		Border emptyBorder = new EmptyBorder(0, 0, 0, 5);

		// Id
		int row = getLastRowIndex();
		JLabel idLabel = new JLabel("Id");
		idLabel.setBorder(emptyBorder);
		idField = new JTextField(20);
		c.gridx = 0;
		c.gridy = row;
		add(idLabel, c);
		c.gridx = 1;
		c.gridy = row;
		add(idField, c);

		// First Name
		row = getLastRowIndex();
		JLabel firstNameLabel = new JLabel("FirstName");
		firstNameLabel.setBorder(emptyBorder);
		firstNameField = new JTextField(20);
		c.gridx = 0;
		c.gridy = row;
		add(firstNameLabel, c);
		c.gridx = 1;
		c.gridy = row;
		add(firstNameField, c);

		// Last Name
		row = getLastRowIndex();
		JLabel lastNameLabel = new JLabel("LastName");
		lastNameLabel.setBorder(emptyBorder);
		lastNameField = new JTextField(20);
		c.gridx = 0;
		c.gridy = row;
		add(lastNameLabel, c);
		c.gridx = 1;
		c.gridy = row;
		add(lastNameField, c);

		// Phone Number
		row = getLastRowIndex();
		JLabel phoneNumberLabel = new JLabel("PhoneNumber");
		phoneNumberLabel.setBorder(emptyBorder);
		phoneNumberField = new JTextField(20);
		c.gridx = 0;
		c.gridy = row;
		add(phoneNumberLabel, c);
		c.gridx = 1;
		c.gridy = row;
		add(phoneNumberField, c);
	}

	@Override
	public Map<String, String> getQueryParams() {
		Map<String, String> res = super.getQueryParams();

		// Id
		if (!idField.getText().trim().equals("")) {
			res.put("id", idField.getText().trim());
		}

		// Name
		if (!firstNameField.getText().trim().equals("")) {
			res.put("firstName", firstNameField.getText().trim());
		}

		// Location
		if (!lastNameField.getText().trim().equals("")) {
			res.put("lastName", lastNameField.getText().trim());
		}
		
		// Location
		if (!phoneNumberField.getText().trim().equals("")) {
			res.put("phoneNumber", phoneNumberField.getText().trim());
		}

		return res;
	}
}
