package com.ebr.components.gui.regularbike;

import javax.swing.JLabel;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.RegularBike;
import com.ebr.components.gui.bike.BikeSinglePane;

@SuppressWarnings("serial")
public class RegularBikeSinglePane extends BikeSinglePane {
	public RegularBikeSinglePane() {
		super();
	}

	public RegularBikeSinglePane(Bike bike) {
		this();
		this.t = bike;

		displayData();
	}

	@Override
	public void buildControls() {
		super.buildControls();
	}

	@Override
	public void displayData() {
		super.displayData();

		if (t instanceof RegularBike) {
			RegularBike regularBike = (RegularBike) t;
		}
	}
}
