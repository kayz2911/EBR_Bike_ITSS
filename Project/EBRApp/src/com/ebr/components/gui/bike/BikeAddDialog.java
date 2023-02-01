package com.ebr.components.gui.bike;

import java.awt.Dimension;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.options.BikeStatusOption;
import com.ebr.components.controller.abstractdata.IDataManageController;
import com.ebr.components.gui.abstractdata.ADataAddDialog;
import com.ebr.components.gui.abstractdata.ADataEditDialog;
import com.ebr.components.gui.utils.GUIUtils;
import com.ebr.enums.BikeStatus;
import com.ebr.utils.Utils;

@SuppressWarnings("serial")
public class BikeAddDialog extends ADataAddDialog<Bike> {

	private JTextField nameField;
	private JTextField weightField;
	private JTextField licensePlateField;
	private JTextField manufacturingDateField;
	private JTextField producerField;
	private JTextField costField;
	private JTextField descriptionField;
	private JComboBox statusComboBox;

	public BikeAddDialog(Bike bike, IDataManageController<Bike> controller) {
		super(bike, controller);
	}

	@Override
	public void buildControls() {
		// bike status options
		Vector bikeStatusOptions = GUIUtils.generateBikeStatusOptions();

		// Name
		int row = getLastRowIndex();
		JLabel nameLabel = new JLabel("Name");
		c.gridx = 0;
		c.gridy = row;
		getContentPane().add(nameLabel, c);
		nameField = new JTextField(20);
//		nameField.setText(t.getName());
		c.gridx = 1;
		c.gridy = row;
		getContentPane().add(nameField, c);

		// Weight
		row = getLastRowIndex();
		JLabel weightLabel = new JLabel("Weight");
		c.gridx = 0;
		c.gridy = row;
		getContentPane().add(weightLabel, c);
		weightField = new JTextField(20);
//		weightField.setText(t.getWeight() + "");
		c.gridx = 1;
		c.gridy = row;
		getContentPane().add(weightField, c);

		// License plate
		row = getLastRowIndex();
		JLabel licensePlateLabel = new JLabel("License plate");
		c.gridx = 0;
		c.gridy = row;
		getContentPane().add(licensePlateLabel, c);
		licensePlateField = new JTextField(20);
//		licensePlateField.setText(t.getLicensePlate());
		c.gridx = 1;
		c.gridy = row;
		getContentPane().add(licensePlateField, c);

		// Manufacturing date
		row = getLastRowIndex();
		JLabel manufacturingDateLabel = new JLabel("Manufacturing date");
		c.gridx = 0;
		c.gridy = row;
		getContentPane().add(manufacturingDateLabel, c);
		manufacturingDateField = new JTextField(20);
//		manufacturingDateField.setText(Utils.dateFormat(t.getManufacturingDate()));
		c.gridx = 1;
		c.gridy = row;
		getContentPane().add(manufacturingDateField, c);

		// Producer
		row = getLastRowIndex();
		JLabel producerLabel = new JLabel("Producer");
		c.gridx = 0;
		c.gridy = row;
		getContentPane().add(producerLabel, c);
		producerField = new JTextField(20);
//		producerField.setText(t.getProducer());
		c.gridx = 1;
		c.gridy = row;
		getContentPane().add(producerField, c);

		// Cost
		row = getLastRowIndex();
		JLabel costLabel = new JLabel("Cost");
		c.gridx = 0;
		c.gridy = row;
		getContentPane().add(costLabel, c);
		costField = new JTextField(20);
//		costField.setText(t.getCost() + "");
		c.gridx = 1;
		c.gridy = row;
		getContentPane().add(costField, c);

		// Description
		row = getLastRowIndex();
		JLabel descriptionLabel = new JLabel("Description");
		c.gridx = 0;
		c.gridy = row;
		getContentPane().add(descriptionLabel, c);
		descriptionField = new JTextField(20);
//		descriptionField.setText(t.getDescription());
		c.gridx = 1;
		c.gridy = row;
		getContentPane().add(descriptionField, c);

		// Status
		row = getLastRowIndex();
		JLabel statusLabel = new JLabel("Status");
		c.gridx = 0;
		c.gridy = row;
		getContentPane().add(statusLabel, c);
		statusComboBox = new JComboBox(bikeStatusOptions);
		statusComboBox.setPreferredSize(new Dimension(185, 20));
//		statusComboBox.setSelectedItem(new BikeStatusOption(t.getStatus()));
		statusComboBox.setSelectedItem(new BikeStatusOption(BikeStatus.AVAILABLE));
		c.gridx = 1;
		c.gridy = row;
		getContentPane().add(statusComboBox, c);

	}

	@Override
	public Bike getNewData() {
		t.setName(nameField.getText());
		t.setWeight(Float.parseFloat(weightField.getText()));
		t.setLicensePlate(licensePlateField.getText());
		t.setManufacturingDate(Utils.convertStrToDate(manufacturingDateField.getText()));
		t.setProducer(producerField.getText());
		t.setCost(new BigDecimal(costField.getText()));
		t.setDescription(descriptionField.getText());
		t.setStatus(BikeStatus.valueOf(statusComboBox.getSelectedItem().toString().toUpperCase()));

		return t;
	}
}
