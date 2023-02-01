package com.ebr.components.gui.twinbike;

import javax.swing.JLabel;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.TwinBike;
import com.ebr.components.gui.bike.BikeSinglePane;

@SuppressWarnings("serial")
public class TwinBikeSinglePane extends BikeSinglePane {
	public TwinBikeSinglePane() {
		super();
	}

	public TwinBikeSinglePane(Bike bike) {
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

		if (t instanceof TwinBike) {
			TwinBike twinBike = (TwinBike) t;
		}
	}
}
