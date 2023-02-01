package com.ebr.components.gui.ebike;

import javax.swing.JLabel;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.EBike;
import com.ebr.components.gui.bike.BikeSinglePane;

@SuppressWarnings("serial")
public class EBikeSinglePane extends BikeSinglePane {
	private JLabel labelBatteryPercentage;
	private JLabel labelLoadCycles;
	private JLabel labelEstimatedUsageTimeRemaining;

	public EBikeSinglePane() {
		super();
	}

	public EBikeSinglePane(Bike bike) {
		this();
		this.t = bike;

		displayData();
	}

	@Override
	public void buildControls() {
		super.buildControls();

		// Battery percentage
		int row = getLastRowIndex();
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

		// Estimated usage time remaining
		row = getLastRowIndex();
		c.gridx = 0;
		c.gridy = row;
		labelEstimatedUsageTimeRemaining = new JLabel();
		add(labelEstimatedUsageTimeRemaining, c);
	}

	@Override
	public void displayData() {
		super.displayData();

		if (t instanceof EBike) {
			EBike ebike = (EBike) t;

			labelBatteryPercentage.setText("Battery percentage: " + ebike.getBatteryPercentage());
			labelLoadCycles.setText("Load cycles: " + ebike.getLoadCycles());
			labelEstimatedUsageTimeRemaining
					.setText("Estimated usage time remaining: " + ebike.getEstimatedUsageTimeRemaining());
		}
	}
}
