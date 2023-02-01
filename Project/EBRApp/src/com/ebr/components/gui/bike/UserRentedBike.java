package com.ebr.components.gui.bike;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.ebr.app.user.EBRUser;
import com.ebr.app.user.EBRUserController;
import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikes.EBike;
import com.ebr.bean.bikes.RegularBike;
import com.ebr.bean.bikes.TwinBike;
import com.ebr.bean.customer.Customer;
import com.ebr.bean.order.Order;
import com.ebr.components.controller.payment.OrderPaymentController;
import com.ebr.components.gui.notification.SuccessNotification;
import com.ebr.utils.Utils;

public class UserRentedBike extends JFrame {
	private OrderPaymentController controller;

	public UserRentedBike(OrderPaymentController controller) {
		this.controller = controller;

		Order rentedOrder = controller.getRentingOrderByCustomerId();
		Bike rentedBike = rentedOrder.getBikeInfo();

		setPreferredSize(new Dimension(600, 600));
		setSize(new Dimension(600, 600));
		setTitle("EcoRentBikeInfo");

		JLabel lblNewLabel = new JLabel("EcoBikeRentalInfo");
		lblNewLabel.setFont(new Font("Untitled", Font.PLAIN, 20));
		lblNewLabel.setBounds(201, 10, 173, 25);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Current total rent time : " + String.format("%,.0f mins", (float)rentedOrder.calculateTotalRentTime()));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(84, 50, 370, 23);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1_3 = new JLabel(
				"Current total rent price : " + String.format("%,.0f", rentedOrder.calculateTotalRentPrice()) + "VNĐ");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_3.setBounds(84, 79, 370, 23);
		getContentPane().add(lblNewLabel_1_1_3);

		JLabel lblNewLabel_1_1_2 = new JLabel("Created time : " + Utils.dateFormat(rentedOrder.getCreatedTime()));
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_2.setBounds(84, 110, 370, 23);
		getContentPane().add(lblNewLabel_1_1_2);

		String type = "Unknown type";
		if (rentedBike instanceof RegularBike) {
			type = "Bike";
		} else if (rentedBike instanceof TwinBike) {
			type = "Twin bike";
		} else if (rentedBike instanceof EBike) {
			type = "Ebike";
		}

		JLabel lblNewLabel_1_1 = new JLabel("Name : " + rentedBike.getName() + "(" + type + ")");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(84, 138, 370, 23);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Weight : " + rentedBike.getWeight());
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(84, 172, 370, 23);
		getContentPane().add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel(
				"Manufacturing Date : " + Utils.dateFormat(rentedBike.getManufacturingDate()));
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(84, 240, 370, 23);
		getContentPane().add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("License : " + rentedBike.getLicensePlate());
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_4.setBounds(84, 206, 370, 23);
		getContentPane().add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("Producer : " + rentedBike.getProducer());
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_5.setBounds(84, 274, 370, 23);
		getContentPane().add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_6 = new JLabel("Cost : " + String.format("%,.0f", rentedBike.getCost()) + "VNĐ");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_6.setBounds(84, 308, 370, 23);
		getContentPane().add(lblNewLabel_1_6);

//		JLabel lblNewLabel_2 = new JLabel("Bike Station : ");
//		lblNewLabel_2.setBounds(323, 55, 84, 14);
//		getContentPane().add(lblNewLabel_2);

//		JLabel lblNewLabel_3 = new JLabel("New label");
//		lblNewLabel_3.setBounds(408, 55, 46, 14);
//		getContentPane().add(lblNewLabel_3);

		if (rentedBike instanceof EBike) {
			JLabel lblNewLabel_1_6_1 = new JLabel(
					"Battery Percentage : " + ((EBike) rentedBike).getBatteryPercentage() + "%");
			lblNewLabel_1_6_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_1_6_1.setBounds(84, 342, 370, 23);
			getContentPane().add(lblNewLabel_1_6_1);

			JLabel lblNewLabel_1_6_2 = new JLabel("Load cycle : " + ((EBike) rentedBike).getLoadCycles() + " times");
			lblNewLabel_1_6_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_1_6_2.setBounds(84, 376, 370, 23);
			getContentPane().add(lblNewLabel_1_6_2);

			JLabel lblNewLabel_1_6_3 = new JLabel("Estimated usage timeremaining : "
					+ ((EBike) rentedBike).getEstimatedUsageTimeRemaining() + " mins");
			lblNewLabel_1_6_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_1_6_3.setBounds(84, 410, 370, 23);
			getContentPane().add(lblNewLabel_1_6_3);
		}

		JLabel lblNewLabel_1_6_4 = new JLabel("Bike station id: ");
		lblNewLabel_1_6_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_6_4.setBounds(84, 440, 370, 23);
		getContentPane().add(lblNewLabel_1_6_4);
		
		JTextField textField = new JTextField(50);
		textField.setBounds(185, 440, 370, 23);
		getContentPane().add(textField);

		JButton btnNewButton = new JButton("Return Bike");
		btnNewButton.setBounds(220, 500, 126, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText() != null && !textField.getText().equals("")) {
					boolean resReturn = controller.updateOrder(textField.getText());
					if(resReturn) {
						setVisible(false);
						SuccessNotification successPane = new SuccessNotification(controller, false);
						successPane.setTitle("Return succeed");
						successPane.setVisible(true);
					}
				}else JOptionPane.showMessageDialog(null, "Must return valid bikeStation id");
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnNewButton);
		
		JButton back = new JButton("Return HomeScreen");
		back.setBounds(220, 295, 126, 23);
		back.setLocation(403, 500);
		getContentPane().add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				JFrame home = new EBRUser(new EBRUserController(new Customer(controller.getCustomer().getId())));
				home.setVisible(true);
			}
		});
	}

}
