package com.ebr.components.gui.customer;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.ebr.bean.customer.Customer;
import com.ebr.bean.options.DistrictOption;
import com.ebr.bean.options.ProvinceOption;
import com.ebr.bean.options.WardOption;
import com.ebr.components.controller.abstractdata.IDataManageController;
import com.ebr.components.gui.abstractdata.ADataEditDialog;
import com.ebr.components.gui.utils.GUIUtils;

@SuppressWarnings("serial")
public class CustomerEditDialog extends ADataEditDialog<Customer> {
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField phonebumber;

	public CustomerEditDialog(Customer customer, IDataManageController<Customer> controller) {
		super(customer, controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buildControls() {
		// TODO Auto-generated method stub

		// First Name
		int row = getLastRowIndex();
		JLabel firstNameLabel = new JLabel("First Name");
		c.gridx = 0;
		c.gridy = row;
		getContentPane().add(firstNameLabel, c);
		firstNameField = new JTextField(20);
		firstNameField.setText(t.getFirstName());
		c.gridx = 1;
		c.gridy = row;
		getContentPane().add(firstNameField, c);

		//Last Name
		
		row = getLastRowIndex();
		JLabel lastNameLabel = new JLabel("Last Name");
		c.gridx = 0;
		c.gridy = row;
		getContentPane().add(lastNameLabel, c);
		lastNameField = new JTextField(20);
		lastNameField.setText(t.getLastName());
		c.gridx = 1;
		c.gridy = row;
		getContentPane().add(lastNameField, c);
		
		// phone number
		row = getLastRowIndex();
		JLabel PhoneNumberLabel = new JLabel("Phone number");
		c.gridx = 0;
		c.gridy = row;
		getContentPane().add(PhoneNumberLabel, c);
		phonebumber = new JTextField(20);
		phonebumber.setText(t.getPhoneNumber() + "");
		c.gridx = 1;
		c.gridy = row;
		getContentPane().add(phonebumber, c);
	}

	@Override
	public Customer getNewData() {
		// TODO Auto-generated method stub
		t.setFirstName(firstNameField.getText());
		t.setLastName(lastNameField.getText());
		t.setPhoneNumber(phonebumber.getText());

		return t;
	}

}
