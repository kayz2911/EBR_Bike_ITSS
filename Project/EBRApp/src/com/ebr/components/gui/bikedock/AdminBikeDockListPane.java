package com.ebr.components.gui.bikedock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.ebr.bean.bikestation.BikeDock;
import com.ebr.components.controller.abstractdata.ADataPageController;
import com.ebr.components.controller.abstractdata.IDataManageController;
import com.ebr.components.controller.bikedock.AdminBikeDockPageController;
import com.ebr.components.gui.abstractdata.ADataListPane;
import com.ebr.components.gui.abstractdata.ADataSinglePane;

@SuppressWarnings("serial")
public class AdminBikeDockListPane extends ADataListPane<BikeDock> {

	public AdminBikeDockListPane(ADataPageController<BikeDock> controller) {
		this.controller = controller;
	}

	@Override
	public void decorateSinglePane(ADataSinglePane<BikeDock> singlePane) {

		IDataManageController<BikeDock> manageController = new IDataManageController<BikeDock>() {
			@Override
			public void update(BikeDock t) {
				if (controller instanceof AdminBikeDockPageController) {
					BikeDock newBikeDock = ((AdminBikeDockPageController) controller).updateBikeDock(t);
					singlePane.updateData(newBikeDock);
				}
			}

			@Override
			public void create(BikeDock t) {
				throw new UnsupportedOperationException("Not supported yet.");
			}

			@Override
			public void read(BikeDock t) {
				throw new UnsupportedOperationException("Not supported yet.");
			}

			@Override
			public void delete(BikeDock t) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		};

	}
}
