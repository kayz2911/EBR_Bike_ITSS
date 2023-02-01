package com.ebr.components.gui.twinbike;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.TwinBike;
import com.ebr.bean.bikes.TwinBike;
import com.ebr.components.controller.abstractdata.IDataManageController;
import com.ebr.components.controller.twinbike.AdminTwinBikePageController;
import com.ebr.components.gui.bike.BikeAddDialog;
import com.ebr.components.gui.bike.BikeEditDialog;
import com.ebr.components.gui.twinbike.TwinBikeAddDialog;

@SuppressWarnings("serial")
public class TwinBikeAddDialog extends BikeAddDialog {
	public TwinBikeAddDialog(Bike bike, IDataManageController<Bike> controller) {
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
