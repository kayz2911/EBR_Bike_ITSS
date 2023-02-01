package com.ebr.components.gui.ebike;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.EBike;
import com.ebr.bean.bikes.EBike;
import com.ebr.components.controller.abstractdata.IDataManageController;
import com.ebr.components.controller.ebike.AdminEBikePageController;
import com.ebr.components.gui.bike.BikeAddDialog;
import com.ebr.components.gui.bike.BikeEditDialog;
import com.ebr.components.gui.ebike.EBikeAddDialog;

@SuppressWarnings("serial")
public class EBikeAddDialog extends BikeAddDialog {
	private JTextField batteryPercentageField;
	private JTextField loadCyclesField;
	private JTextField estimatedUsageTimeRemainingField;

	public EBikeAddDialog(Bike bike, IDataManageController<Bike> controller) {
		super(bike, controller);
	}

	@Override
	public void buildControls() {
		
		super.buildControls();
		if (t instanceof EBike) {
			EBike ebike = (EBike) t;

			// Battery percentage
			int row = getLastRowIndex();
			JLabel batteryPercentageLabel = new JLabel("Battery percentage");
			c.gridx = 0;
			c.gridy = row;
			getContentPane().add(batteryPercentageLabel, c);
			batteryPercentageField = new JTextField(20);
			batteryPercentageField.setText(ebike.getBatteryPercentage() + "");
			c.gridx = 1;
			c.gridy = row;
			getContentPane().add(batteryPercentageField, c);

			// Load cycles
			row = getLastRowIndex();
			JLabel loadCyclesLabel = new JLabel("Load cycles");
			c.gridx = 0;
			c.gridy = row;
			getContentPane().add(loadCyclesLabel, c);
			loadCyclesField = new JTextField(20);
			loadCyclesField.setText(ebike.getLoadCycles() + "");
			c.gridx = 1;
			c.gridy = row;
			getContentPane().add(loadCyclesField, c);

			// Estimated usage time remaining
			row = getLastRowIndex();
			JLabel estimatedUsageTimeRemainingLabel = new JLabel("Estimated usage time remaining");
			c.gridx = 0;
			c.gridy = row;
			getContentPane().add(estimatedUsageTimeRemainingLabel, c);
			estimatedUsageTimeRemainingField = new JTextField(20);
			estimatedUsageTimeRemainingField.setText(ebike.getEstimatedUsageTimeRemaining() + "");
			c.gridx = 1;
			c.gridy = row;
			getContentPane().add(estimatedUsageTimeRemainingField, c);
		}
	}

	@Override
	public Bike getNewData() {
		super.getNewData();

		if (t instanceof EBike) {
			EBike ebike = (EBike) t;

			ebike.setBatteryPercentage(Float.parseFloat(batteryPercentageField.getText()));
			ebike.setLoadCycles(Integer.parseInt(loadCyclesField.getText()));
			ebike.setEstimatedUsageTimeRemaining(Float.parseFloat(estimatedUsageTimeRemainingField.getText()));
		}

		return t;
	}
}
