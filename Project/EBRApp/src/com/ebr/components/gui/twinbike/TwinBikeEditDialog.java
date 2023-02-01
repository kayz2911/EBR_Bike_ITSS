package com.ebr.components.gui.twinbike;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.TwinBike;
import com.ebr.components.controller.abstractdata.IDataManageController;
import com.ebr.components.gui.bike.BikeEditDialog;

@SuppressWarnings("serial")
public class TwinBikeEditDialog extends BikeEditDialog {
	public TwinBikeEditDialog(Bike bike, IDataManageController<Bike> controller) {
		super(bike, controller);
	}

	@Override
	public void buildControls() {
		super.buildControls();

		if (t instanceof TwinBike) {
			TwinBike twinBike = (TwinBike) t;
		}
	}

	@Override
	public Bike getNewData() {
		super.getNewData();

		if (t instanceof TwinBike) {
			TwinBike twinBike = (TwinBike) t;
		}

		return t;
	}
}
