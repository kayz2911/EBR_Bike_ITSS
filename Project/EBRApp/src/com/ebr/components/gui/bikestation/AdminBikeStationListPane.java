package com.ebr.components.gui.bikestation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.ebr.bean.bikestation.BikeStation;
import com.ebr.components.controller.abstractdata.ADataPageController;
import com.ebr.components.controller.abstractdata.IDataManageController;
import com.ebr.components.controller.bikestation.AdminBikeStationPageController;
import com.ebr.components.gui.abstractdata.ADataListPane;
import com.ebr.components.gui.abstractdata.ADataSinglePane;

@SuppressWarnings("serial")
public class AdminBikeStationListPane extends ADataListPane<BikeStation> {

	public AdminBikeStationListPane(ADataPageController<BikeStation> controller) {
		this.controller = controller;
	}

	@Override
	public void decorateSinglePane(ADataSinglePane<BikeStation> singlePane) {
		JButton editButton = new JButton("Edit");
		singlePane.addDataHandlingComponent(editButton);

		IDataManageController<BikeStation> manageController = new IDataManageController<BikeStation>() {
			@Override
			public void update(BikeStation t) {
				if (controller instanceof AdminBikeStationPageController) {
					BikeStation newBikeStation = ((AdminBikeStationPageController) controller).updateBikeStation(t);
					singlePane.updateData(newBikeStation);
				}
			}

			@Override
			public void create(BikeStation t) {
				throw new UnsupportedOperationException("Not supported yet.");
			}

			@Override
			public void read(BikeStation t) {
				throw new UnsupportedOperationException("Not supported yet.");
			}

			@Override
			public void delete(BikeStation t) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		};

		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new BikeStationEditDialog(singlePane.getData(), manageController);
			}
		});
	}
}
