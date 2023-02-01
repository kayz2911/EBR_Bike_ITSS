package com.ebr.components.gui.notification;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.ebr.app.user.EBRUser;
import com.ebr.app.user.EBRUserController;
import com.ebr.bean.customer.Customer;
import com.ebr.components.controller.payment.OrderPaymentController;
import com.ebr.components.gui.bike.UserRentedBike;

public class SuccessNotification extends JFrame {
	private OrderPaymentController controller;

	public SuccessNotification(OrderPaymentController controller, boolean isRentSuccess) {
		this.controller = controller;

		setSize(new Dimension(600, 600));
		setPreferredSize(new Dimension(600, 600));
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("EcoBikeRental");
		lblNewLabel.setFont(new Font("Untitled", Font.PLAIN, 20));
		lblNewLabel.setBounds(208, 18, 173, 25);
		getContentPane().add(lblNewLabel);

		// JLabel lblNewLabel_1 = new JLabel("Order payment");
		// lblNewLabel_1.setBounds(231, 54, 73, 14);
		// getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2;
		if (isRentSuccess) {
			lblNewLabel_2 = new JLabel("Rent bike succeed!");
		} else {
			lblNewLabel_2 = new JLabel("Return bike succeed!");
		}

		lblNewLabel_2.setFont(new Font("Untitled", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(190, 200, 300, 25);
		getContentPane().add(lblNewLabel_2);

		if (isRentSuccess) {
			JButton button = new JButton("View bike");
			button.setBounds(220, 295, 89, 25);
			getContentPane().add(button);

			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					UserRentedBike rentedBike = new UserRentedBike(controller);
					rentedBike.setTitle("Bike Rented Info");
					rentedBike.setVisible(true);
				}
			});
		} else {
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

}
