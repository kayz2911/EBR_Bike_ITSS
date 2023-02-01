package com.ebr.components.gui.bike;

import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.ebr.bean.bikes.Bike;
import com.ebr.components.controller.abstractdata.IDataManageController;
import com.ebr.components.controller.bike.AdminBikePageController;


@SuppressWarnings("serial")
public class BikeSearchPane extends UserBikeSearchPane {
	private JTextField idField;
	
	public BikeSearchPane() {
		super();
	}

	@Override
	public void buildControls() {
	    Border emptyBorder = new EmptyBorder(0, 0, 0, 5);
		
		// Id
		int row = getLastRowIndex();
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
