package com.ebr.components.gui.bike;

import javax.swing.JLabel;

import com.ebr.bean.bikes.*;
import com.ebr.components.gui.abstractdata.ADataSinglePane;
import com.ebr.utils.Utils;

@SuppressWarnings("serial")
public class UserBikeSinglePane extends ADataSinglePane<Bike> {
	private JLabel labelName;
	private JLabel labelWeight;
	private JLabel labelLicensePlate;
	private JLabel labelManufacturingDate;
	private JLabel labelProducer;
	private JLabel labelCost;
	private JLabel labelBatteryPercentage;
	private JLabel labelLoadCycles;
	private JLabel labelEstimatedRemainingTime;

	public UserBikeSinglePane() {
		super();
	}

	public UserBikeSinglePane(Bike bike) {
		this();
		this.t = bike;

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

		// Weight
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelWeight = new JLabel();
		add(labelWeight, c);

		// License plate
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelLicensePlate = new JLabel();
		add(labelLicensePlate, c);

		// Manufacturing date
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelManufacturingDate = new JLabel();
		add(labelManufacturingDate, c);

		// Producer
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelProducer = new JLabel();
		add(labelProducer, c);

		// Cost
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelCost = new JLabel();
		add(labelCost, c);

		// Battery percentage
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelBatteryPercentage = new JLabel();
		add(labelBatteryPercentage, c);

		// Load cycles
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelLoadCycles = new JLabel();
		add(labelLoadCycles, c);

		// Estimate remaining time
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelEstimatedRemainingTime = new JLabel();
		add(labelEstimatedRemainingTime, c);
	}

	@Override
	public void displayData() {
		labelName.setText("Name: " + t.getName());
		labelWeight.setText("Weight: " + t.getWeight());
		labelLicensePlate.setText("LicensePlate: " + t.getLicensePlate());
		labelManufacturingDate.setText("ManufacturingDate: " + Utils.dateFormat(t.getManufacturingDate()));
		labelProducer.setText("Producer: " + t.getProducer());
		labelCost.setText("Cost: " + String.format("%,.0f VNĐ", t.getCost()));
		if (t instanceof EBike) {
			labelLoadCycles
					.setText("Load cycles: " + String.format("%,.0f times", (float) ((EBike) t).getLoadCycles()));
			labelBatteryPercentage.setText("Battery percentage: " + ((EBike) t).getBatteryPercentage());
			labelEstimatedRemainingTime.setText("Estimated remaining time: "
					+ String.format("%,.0f mins", ((EBike) t).getEstimatedUsageTimeRemaining()));
		}
	}
}
