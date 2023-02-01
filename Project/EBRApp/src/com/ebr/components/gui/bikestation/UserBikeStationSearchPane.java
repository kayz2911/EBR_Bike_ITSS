package com.ebr.components.gui.bikestation;

import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.ebr.components.gui.abstractdata.ADataSearchPane;

@SuppressWarnings("serial")
public class UserBikeStationSearchPane extends ADataSearchPane {
	private JTextField nameField;
	private JTextField locationField;

	public UserBikeStationSearchPane() {
		super();
	}

	@Override
	public void buildControls() {
		Border emptyBorder = new EmptyBorder(15, 0, 15, 5);

		// Name
		int row = getLastRowIndex();
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBorder(emptyBorder);
		nameField = new JTextField(20);
		c.gridx = 0;
		c.gridy = row;
		add(nameLabel, c);
		c.gridx = 1;
		c.gridy = row;
		add(nameField, c);

		// Location
		row = getLastRowIndex();
		JLabel locationLabel = new JLabel("Location");
		locationLabel.setBorder(emptyBorder);
		locationField = new JTextField(20);
		c.gridx = 0;
		c.gridy = row;
		add(locationLabel, c);
		c.gridx = 1;
		c.gridy = row;
		add(locationField, c);
	}

	@Override
	public Map<String, String> getQueryParams() {
		Map<String, String> res = super.getQueryParams();

		// Name
		if (!nameField.getText().trim().equals("")) {
			res.put("name", nameField.getText().trim());
		}

		// Location
		if (!locationField.getText().trim().equals("")) {
			res.put("location", locationField.getText().trim());
		}

		return res;
	}
}
