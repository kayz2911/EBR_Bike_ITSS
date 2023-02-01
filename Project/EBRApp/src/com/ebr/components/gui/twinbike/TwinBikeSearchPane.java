package com.ebr.components.gui.twinbike;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.TwinBike;
import com.ebr.components.controller.abstractdata.IDataManageController;
import com.ebr.components.controller.twinbike.AdminTwinBikePageController;
import com.ebr.components.gui.bike.BikeSearchPane;

@SuppressWarnings("serial")
public class TwinBikeSearchPane extends BikeSearchPane {
	public TwinBikeSearchPane() {
		super();
	}

	@Override
	public void buildControls() {
		IDataManageController<Bike> manageController = new IDataManageController<Bike>() {
			@Override
			public void update(Bike t) {
				throw new UnsupportedOperationException("Not supported yet.");
			}

			@Override
			public void create(Bike t) {
				AdminTwinBikePageController controller  = new AdminTwinBikePageController();
				controller.addBike(t);
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
		int row = getLastRowIndex();
		JButton addButton = new JButton("Add");
		c.gridx = 0;
		c.gridy = row;
		add(addButton, c);
		
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new TwinBikeAddDialog(new TwinBike(), manageController);
			}
		});
		super.buildControls();
	}

	@Override
	public Map<String, String> getQueryParams() {
		Map<String, String> res = super.getQueryParams();

		return res;
	}
}
