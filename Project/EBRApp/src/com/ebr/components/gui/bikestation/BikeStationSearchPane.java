package com.ebr.components.gui.bikestation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.EBike;
import com.ebr.bean.bikestation.BikeStation;
import com.ebr.components.controller.abstractdata.IDataManageController;
import com.ebr.components.controller.bikestation.AdminBikeStationPageController;
import com.ebr.components.controller.ebike.AdminEBikePageController;
import com.ebr.components.gui.ebike.EBikeAddDialog;

@SuppressWarnings("serial")
public class BikeStationSearchPane extends UserBikeStationSearchPane {
	private JTextField idField;

	public BikeStationSearchPane() {
		super();
	}

	@Override
	public void buildControls() {
		IDataManageController<BikeStation> manageController = new IDataManageController<BikeStation>() {
			@Override
			public void update(BikeStation t) {
				throw new UnsupportedOperationException("Not supported yet.");
			}

			@Override
			public void create(BikeStation t) {
				AdminBikeStationPageController controller = new AdminBikeStationPageController();
				controller.addBikeStation(t);
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
		int row = getLastRowIndex();
		JButton addButton = new JButton("Add");
		c.gridx = 0;
		c.gridy = row;
		add(addButton, c);

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new BikeStationAddDialog(new BikeStation(), manageController);
			}
		});

		Border emptyBorder = new EmptyBorder(0, 0, 0, 5);

		// Id
		row = getLastRowIndex();
		JLabel idLabel = new JLabel("Id");
		idLabel.setBorder(emptyBorder);
		idField = new JTextField(20);
		c.gridx = 0;
		c.gridy = row;
		add(idLabel, c);
		c.gridx = 1;
		c.gridy = row;
		add(idField, c);

		super.buildControls();
	}

	@Override
	public Map<String, String> getQueryParams() {
		Map<String, String> res = super.getQueryParams();

		// Id
		if (!idField.getText().trim().equals("")) {
			res.put("id", idField.getText().trim());
		}

		return res;
	}
}
