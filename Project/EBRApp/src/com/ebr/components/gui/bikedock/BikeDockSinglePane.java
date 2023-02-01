package com.ebr.components.gui.bikedock;

import javax.swing.JLabel;

import com.ebr.bean.bikestation.BikeDock;
import com.ebr.components.gui.abstractdata.ADataSinglePane;

@SuppressWarnings("serial")
public class BikeDockSinglePane extends ADataSinglePane<BikeDock> {
	private JLabel labelId;
	private JLabel labelBikeStationId;
	private JLabel labelBikeId;
	private JLabel labelBikeStationName;
	private JLabel labelBikeName;
	private JLabel labelBikeLicensePlate;

	public BikeDockSinglePane() {
		super();
	}

	public BikeDockSinglePane(BikeDock bikeDock) {
		this();
		this.t = bikeDock;

		displayData();
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

		// BikeStationId
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelBikeStationId = new JLabel();
		add(labelBikeStationId, c);

		// BikeId
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelBikeId = new JLabel();
		add(labelBikeId, c);

		// BikeStationName
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelBikeStationName = new JLabel();
		add(labelBikeStationName, c);

		// BikeName
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelBikeName = new JLabel();
		add(labelBikeName, c);

		// BikeLicensePlate
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelBikeLicensePlate = new JLabel();
		add(labelBikeLicensePlate, c);
	}

	@Override
	public void displayData() {
		labelId.setText("Id: " + t.getId());
		labelBikeStationId.setText("BikeStationId: " + t.getBikeStationId());
		labelBikeId.setText("BikeId: " + t.getBikeId());
		if (t.getBikeStation() != null) {
			labelBikeStationName.setText("BikeStationName: " + t.getBikeStation().getName());
		}
		if (t.getBike() != null) {
			labelBikeName.setText("BikeName: " + t.getBike().getName());
			labelBikeLicensePlate.setText("BikeLicensePlate: " + t.getBike().getLicensePlate());
		}
	}
}
