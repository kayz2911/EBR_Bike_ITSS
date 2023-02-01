package com.ebr.components.gui.bikestation;

import javax.swing.JLabel;

import com.ebr.bean.bikestation.BikeStation;

@SuppressWarnings("serial")
public class BikeStationSinglePane extends UserBikeStationSinglePane {
	private JLabel labelId;

	public BikeStationSinglePane() {
		super();
	}

	public BikeStationSinglePane(BikeStation bikeStation) {
		super();
	}

	@Override
	public void buildControls() {
		super.buildControls();

		// id
		int row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelId = new JLabel();
		add(labelId, c);
	}

	@Override
	public void displayData() {
		labelId.setText("Id: " + t.getId());
		super.displayData();;
	}
}
