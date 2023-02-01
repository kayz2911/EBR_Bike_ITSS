package com.ebr.components.gui.bikedock;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.ebr.bean.Address;
import com.ebr.bean.bikestation.BikeDock;
import com.ebr.bean.options.BikeStatusOption;
import com.ebr.bean.options.DistrictOption;
import com.ebr.bean.options.ProvinceOption;
import com.ebr.bean.options.WardOption;
import com.ebr.components.controller.abstractdata.IDataManageController;
import com.ebr.components.gui.abstractdata.ADataAddDialog;
import com.ebr.components.gui.abstractdata.ADataEditDialog;
import com.ebr.components.gui.utils.GUIUtils;
import com.ebr.enums.BikeStatus;
import com.ebr.utils.Utils;

@SuppressWarnings("serial")
public class BikeDockAddDialog extends ADataAddDialog<BikeDock> {

	private JTextField bikeStationIdField;
	private JTextField bikeIdField;

	public BikeDockAddDialog(BikeDock bikeDock, IDataManageController<BikeDock> controller) {
		super(bikeDock, controller);
	}

	@Override
	public void buildControls() {
		// Bike station id
		int row = getLastRowIndex();
		JLabel labelBikeStationId = new JLabel("Bike station id");
		c.gridx = 0;
		c.gridy = row;
		getContentPane().add(labelBikeStationId, c);
		bikeStationIdField = new JTextField(20);
		c.gridx = 1;
		c.gridy = row;
		getContentPane().add(bikeStationIdField, c);

		// Bike id
		row = getLastRowIndex();
		JLabel labelBikeId = new JLabel("Bike id");
		c.gridx = 0;
		c.gridy = row;
		getContentPane().add(labelBikeId, c);
		bikeIdField = new JTextField(20);
		c.gridx = 1;
		c.gridy = row;
		getContentPane().add(bikeIdField, c);
	}

	@Override
	public BikeDock getNewData() {
		t.setBikeId(bikeIdField.getText());
		t.setBikeStationId(bikeStationIdField.getText());

		return t;
	}
}
