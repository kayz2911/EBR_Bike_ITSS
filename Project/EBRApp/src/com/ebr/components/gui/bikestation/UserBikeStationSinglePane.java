package com.ebr.components.gui.bikestation;

import javax.swing.JLabel;

import com.ebr.bean.bikestation.BikeStation;
import com.ebr.components.gui.abstractdata.ADataSinglePane;

@SuppressWarnings("serial")
public class UserBikeStationSinglePane extends ADataSinglePane<BikeStation> {
	private JLabel labelName;
	private JLabel labelNumberOfDock;
	private JLabel labelProvinceName;
	private JLabel labelDistrictName;
	private JLabel labelWardName;

	public UserBikeStationSinglePane() {
		super();
	}

	public UserBikeStationSinglePane(BikeStation bikeStation) {
		this();
		this.t = bikeStation;

		displayData();
	}

	@Override
	public void buildControls() {
		super.buildControls();

		// Name
		int row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelName = new JLabel();
		add(labelName, c);

		// Number of dock
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelNumberOfDock = new JLabel();
		add(labelNumberOfDock, c);

		// Province name
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelProvinceName = new JLabel();
		add(labelProvinceName, c);

		// District name
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelDistrictName = new JLabel();
		add(labelDistrictName, c);

		// Ward name
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelWardName = new JLabel();
		add(labelWardName, c);

	}

	@Override
	public void displayData() {
		labelName.setText("Name: " + t.getName());
		labelNumberOfDock.setText("Number of dock: " + t.getNumberOfDock());
		labelProvinceName.setText("Province name: " + t.getAddress().getProvinceName());
		labelDistrictName.setText("District name: " + t.getAddress().getDistrictName());
		labelWardName.setText("Ward name: " + t.getAddress().getWardName());
	}
}
