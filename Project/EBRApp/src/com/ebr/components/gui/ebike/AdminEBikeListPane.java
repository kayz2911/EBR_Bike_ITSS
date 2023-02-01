package com.ebr.components.gui.ebike;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.ebr.bean.bikes.Bike;
import com.ebr.components.controller.abstractdata.ADataPageController;
import com.ebr.components.controller.abstractdata.IDataManageController;
import com.ebr.components.controller.ebike.AdminEBikePageController;
import com.ebr.components.gui.abstractdata.ADataListPane;
import com.ebr.components.gui.abstractdata.ADataSinglePane;
import com.ebr.components.gui.ebike.EBikeEditDialog;

@SuppressWarnings("serial")
public class AdminEBikeListPane extends ADataListPane<Bike> {

	public AdminEBikeListPane(ADataPageController<Bike> controller) {
		this.controller = controller;
	}

	@Override
	public void decorateSinglePane(ADataSinglePane<Bike> singlePane) {
		JButton editButton = new JButton("Edit");
		singlePane.addDataHandlingComponent(editButton);

		IDataManageController<Bike> manageController = new IDataManageController<Bike>() {
			@Override
			public void update(Bike t) {
				if (controller instanceof AdminEBikePageController) {
					Bike newBike = ((AdminEBikePageController) controller).updateBike(t);
					singlePane.updateData(newBike);
				}
			}

			@Override
			public void create(Bike t) {
				throw new UnsupportedOperationException("Not supported yet.");
			}

			@Override
			public void read(Bike t) {
				throw new UnsupportedOperationException("Not supported yet.");
			}

			@Override
			public void delete(Bike t) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		};

		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EBikeEditDialog(singlePane.getData(), manageController);
			}
		});
	}
}
