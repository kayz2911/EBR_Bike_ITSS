package com.ebr.components.gui.bike;

import javax.swing.JLabel;

import com.ebr.bean.bikes.Bike;


@SuppressWarnings("serial")
public class BikeSinglePane extends UserBikeSinglePane {
	private JLabel labelId;
	private JLabel labelDescription;
	private JLabel labelStatus;

	public BikeSinglePane() {
		super();
	}

	public BikeSinglePane(Bike bike) {
		super();
	}

	@Override
	public void buildControls() {
		super.buildControls();

		// Id
		int row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelId = new JLabel();
		add(labelId, c);
		
		// Description
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelDescription = new JLabel();
		add(labelDescription, c);
		
		// Status
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelStatus = new JLabel();
		add(labelStatus, c);

	}

	@Override
	public void displayData() {
		labelId.setText("Id: " + t.getId());
		super.displayData();
		labelDescription.setText("Description: " + t.getDescription());
		labelStatus.setText("Status: " + t.getStatus().getStatusStr());
	}
}
