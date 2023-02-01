package com.ebr.components.gui.bikestation;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.ebr.bean.bikestation.BikeStation;
import com.ebr.bean.options.BikeStatusOption;
import com.ebr.bean.options.DistrictOption;
import com.ebr.bean.options.ProvinceOption;
import com.ebr.bean.options.WardOption;
import com.ebr.components.controller.abstractdata.IDataManageController;
import com.ebr.components.gui.abstractdata.ADataEditDialog;
import com.ebr.components.gui.utils.GUIUtils;
import com.ebr.enums.BikeStatus;
import com.ebr.utils.Utils;

@SuppressWarnings("serial")
public class BikeStationEditDialog extends ADataEditDialog<BikeStation> {

	private JTextField nameField;
	private JComboBox provinceComboBox;
	private JComboBox districtComboBox;
	private JComboBox wardComboBox;
	private JTextField numberOfDockField;

	private Vector provinceOptions;
	private Vector districtOptions;
	private Vector wardOptions;

	public BikeStationEditDialog(BikeStation bikeStation, IDataManageController<BikeStation> controller) {
		super(bikeStation, controller);
	}

	@Override
	public void buildControls() {
		// province options
		provinceOptions = GUIUtils.generateProvinceOptions();

		// district options
		districtOptions = GUIUtils.generateDistrictOptions(t.getAddress().getProvinceCode());

		// ward options
		wardOptions = GUIUtils.generateWardOptions(t.getAddress().getDistrictCode());

		// Name
		int row = getLastRowIndex();
		JLabel nameLabel = new JLabel("Name");
		c.gridx = 0;
		c.gridy = row;
		getContentPane().add(nameLabel, c);
		nameField = new JTextField(20);
		nameField.setText(t.getName());
		c.gridx = 1;
		c.gridy = row;
		getContentPane().add(nameField, c);

		// Province
		row = getLastRowIndex();
		JLabel provinceLabel = new JLabel("Province");
		c.gridx = 0;
		c.gridy = row;
		getContentPane().add(provinceLabel, c);
		provinceComboBox = new JComboBox(provinceOptions);
		provinceComboBox.setPreferredSize(new Dimension(185, 20));
		provinceComboBox.setSelectedItem(new ProvinceOption(t.getAddress().getProvinceCode()));
		provinceComboBox.addItemListener(event -> {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				// update district options
				districtOptions = GUIUtils.generateDistrictOptions(((ProvinceOption) event.getItem()).getKey());
				districtComboBox.removeAllItems();
				for (Integer i = 0; i < districtOptions.size(); i++) {
					districtComboBox.addItem((DistrictOption) districtOptions.get(i));
				}

				// update ward options
				wardComboBox.removeAllItems();
			}
		});
		c.gridx = 1;
		c.gridy = row;
		getContentPane().add(provinceComboBox, c);

		// District
		row = getLastRowIndex();
		JLabel districtLabel = new JLabel("District");
		c.gridx = 0;
		c.gridy = row;
		getContentPane().add(districtLabel, c);
		districtComboBox = new JComboBox(districtOptions);
		districtComboBox.setPreferredSize(new Dimension(185, 20));
		districtComboBox.setSelectedItem(new DistrictOption(t.getAddress().getDistrictCode()));
		districtComboBox.addItemListener(event -> {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				// update ward options
				wardOptions = GUIUtils.generateWardOptions(((DistrictOption) event.getItem()).getKey());
				wardComboBox.removeAllItems();
				for (Integer i = 0; i < wardOptions.size(); i++) {
					wardComboBox.addItem((WardOption) wardOptions.get(i));
				}
			}
		});
		c.gridx = 1;
		c.gridy = row;
		getContentPane().add(districtComboBox, c);

		// Ward
		row = getLastRowIndex();
		JLabel wardLabel = new JLabel("Ward");
		c.gridx = 0;
		c.gridy = row;
		getContentPane().add(wardLabel, c);
		wardComboBox = new JComboBox(wardOptions);
		wardComboBox.setPreferredSize(new Dimension(185, 20));
		wardComboBox.setSelectedItem(new WardOption(t.getAddress().getWardCode()));
		c.gridx = 1;
		c.gridy = row;
		getContentPane().add(wardComboBox, c);

		// Number of dock
		row = getLastRowIndex();
		JLabel numberOfDockLabel = new JLabel("Number of dock");
		c.gridx = 0;
		c.gridy = row;
		getContentPane().add(numberOfDockLabel, c);
		numberOfDockField = new JTextField(20);
		numberOfDockField.setText(t.getNumberOfDock() + "");
		c.gridx = 1;
		c.gridy = row;
		getContentPane().add(numberOfDockField, c);
	}

	@Override
	public BikeStation getNewData() {
		t.setName(nameField.getText());
		t.getAddress().setProvinceCode(((ProvinceOption) provinceComboBox.getSelectedItem()).getKey());
		t.getAddress().setProvinceName(((ProvinceOption) provinceComboBox.getSelectedItem()).getValue());
		t.getAddress().setDistrictCode(((DistrictOption) districtComboBox.getSelectedItem()).getKey());
		t.getAddress().setDistrictName(((DistrictOption) districtComboBox.getSelectedItem()).getValue());
		t.getAddress().setWardCode(((WardOption) wardComboBox.getSelectedItem()).getKey());
		t.getAddress().setWardName(((WardOption) wardComboBox.getSelectedItem()).getValue());
		t.setNumberOfDock(Integer.parseInt(numberOfDockField.getText()));

		return t;
	}
}
