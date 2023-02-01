package com.ebr.components.gui.bikedock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.ebr.bean.bikestation.BikeDock;
import com.ebr.bean.bikestation.BikeStation;
import com.ebr.components.controller.abstractdata.IDataManageController;
import com.ebr.components.controller.bikedock.AdminBikeDockPageController;
import com.ebr.components.controller.bikestation.AdminBikeStationPageController;
import com.ebr.components.gui.abstractdata.ADataSearchPane;
import com.ebr.components.gui.bikestation.BikeStationAddDialog;

@SuppressWarnings("serial")
public class BikeDockSearchPane extends ADataSearchPane {
	private JTextField idField;
	private JTextField bikeStationIdField;
	private JTextField bikeIdField;

	public BikeDockSearchPane() {
		super();
	}

	@Override
	public void buildControls() {
		IDataManageController<BikeDock> manageController = new IDataManageController<BikeDock>() {
			@Override
			public void update(BikeDock t) {
				throw new UnsupportedOperationException("Not supported yet.");
			}

			@Override
			public void create(BikeDock t) {
				AdminBikeDockPageController controller = new AdminBikeDockPageController();
				controller.addBikeDock(t);
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
		int row = getLastRowIndex();
		JButton addButton = new JButton("Add");
		c.gridx = 0;
		c.gridy = row;
		add(addButton, c);

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new BikeDockAddDialog(new BikeDock(), manageController);
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

		// BikeStationId
		row = getLastRowIndex();
		JLabel nameLabel = new JLabel("BikeStationId");
		nameLabel.setBorder(emptyBorder);
		bikeStationIdField = new JTextField(20);
		c.gridx = 0;
		c.gridy = row;
		add(nameLabel, c);
		c.gridx = 1;
		c.gridy = row;
		add(bikeStationIdField, c);

		// BikeId
		row = getLastRowIndex();
		JLabel locationLabel = new JLabel("BikeId");
		locationLabel.setBorder(emptyBorder);
		bikeIdField = new JTextField(20);
		c.gridx = 0;
		c.gridy = row;
		add(locationLabel, c);
		c.gridx = 1;
		c.gridy = row;
		add(bikeIdField, c);
	}

	@Override
	public Map<String, String> getQueryParams() {
		Map<String, String> res = super.getQueryParams();

		// Id
		if (!idField.getText().trim().equals("")) {
			res.put("id", idField.getText().trim());
		}

		// BikeStationId
		if (!bikeStationIdField.getText().trim().equals("")) {
			res.put("bikeStationId", bikeStationIdField.getText().trim());
		}

		// BikeId
		if (!bikeIdField.getText().trim().equals("")) {
			res.put("bikeId", bikeIdField.getText().trim());
		}

		return res;
	}
}
