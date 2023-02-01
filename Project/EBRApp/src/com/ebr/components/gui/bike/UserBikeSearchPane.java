package com.ebr.components.gui.bike;

import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.ebr.components.gui.abstractdata.ADataSearchPane;

@SuppressWarnings("serial")
public class UserBikeSearchPane extends ADataSearchPane {
	private JTextField nameField;
	private JTextField licensePlateField;
	private JTextField producerField;
	private JTextField descriptionField;

	public UserBikeSearchPane() {
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

		// License plate
		row = getLastRowIndex();
		JLabel licensePlateLabel = new JLabel("License plate");
		licensePlateLabel.setBorder(emptyBorder);
		licensePlateField = new JTextField(20);
		c.gridx = 0;
		c.gridy = row;
		add(licensePlateLabel, c);
		c.gridx = 1;
		c.gridy = row;
		add(licensePlateField, c);

		// Producer
		row = getLastRowIndex();
		JLabel producerLabel = new JLabel("Producer");
		producerLabel.setBorder(emptyBorder);
		producerField = new JTextField(20);
		c.gridx = 0;
		c.gridy = row;
		add(producerLabel, c);
		c.gridx = 1;
		c.gridy = row;
		add(producerField, c);

		// Description
		row = getLastRowIndex();
		JLabel descriptionLabel = new JLabel("Description");
		descriptionLabel.setBorder(emptyBorder);
		descriptionField = new JTextField(20);
		c.gridx = 0;
		c.gridy = row;
		add(descriptionLabel, c);
		c.gridx = 1;
		c.gridy = row;
		add(descriptionField, c);
	}

	@Override
	public Map<String, String> getQueryParams() {
		Map<String, String> res = super.getQueryParams();

		// Name
		if (!nameField.getText().trim().equals("")) {
			res.put("bikeName", nameField.getText().trim());
		}

		// LicensePlate
		if (!licensePlateField.getText().trim().equals("")) {
			res.put("bikeLicensePlate", licensePlateField.getText().trim());
		}

		// Producer
		if (!producerField.getText().trim().equals("")) {
			res.put("bikeProducer", producerField.getText().trim());
		}

		// Description
		if (!descriptionField.getText().trim().equals("")) {
			res.put("bikeDescription", descriptionField.getText().trim());
		}

		return res;
	}
}
