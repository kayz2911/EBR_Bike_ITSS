package com.ebr.components.gui.regularbike;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.RegularBike;
import com.ebr.components.controller.abstractdata.IDataManageController;
import com.ebr.components.gui.bike.BikeAddDialog;
import com.ebr.components.gui.bike.BikeEditDialog;

@SuppressWarnings("serial")
public class RegularBikeAddDialog extends BikeAddDialog {
	public RegularBikeAddDialog(Bike bike, IDataManageController<Bike> controller) {
		super(bike, controller);
	}

	@Override
	public void buildControls() {
		super.buildControls();

		if (t instanceof RegularBike) {
			RegularBike regularBike = (RegularBike) t;
		}
	}

	@Override
	public Bike getNewData() {
		super.getNewData();

		if (t instanceof RegularBike) {
			RegularBike regularBike = (RegularBike) t;
		}

		return t;
	}
}
