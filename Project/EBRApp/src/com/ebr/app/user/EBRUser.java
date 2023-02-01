package com.ebr.app.user;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.ebr.bean.bikes.RegularBike;
import com.ebr.bean.customer.Customer;
import com.ebr.components.controller.payment.OrderPaymentController;
import com.ebr.components.gui.bike.UserRentedBike;

@SuppressWarnings("serial")
public class EBRUser extends JFrame {

	public static final int WINDOW_WIDTH = 600;
	public static final int WINDOW_HEIGHT = 550;

	public EBRUser(EBRUserController controller) {
		setTitle("EcoBikeRent");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Welcome, " + controller.getCustomerInfo());
		lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblNewLabel.setBounds(29, 56, 180, 50);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("EcoBikeRental");
		lblNewLabel_1.setFont(new Font("Untitled", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(220, 21, 180, 25);
		getContentPane().add(lblNewLabel_1);

		try {
			BufferedImage homeIcon = ImageIO.read(this.getClass().getResource("home_icon_trans.png"));
			Image resizedIcon = homeIcon.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
			JLabel picLabel = new JLabel(new ImageIcon(resizedIcon));
			picLabel.setBounds(90, 100, 400, 300);
			getContentPane().add(picLabel);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JButton btnNewButton = new JButton("View Bike Stations");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				JFrame newFrame = new JFrame();
				JPanel newPanel = controller.getBikeStationPage();

				JButton back = new JButton("<");
				back.setBounds(100, 100, 0, 0);

				newPanel.add(back);

				newFrame.setContentPane(newPanel);
				newFrame.setTitle("Bike Station");
				newFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
				newFrame.setVisible(true);
				back.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(true);
						newFrame.setVisible(false);
					}
				});

			}
		});
		btnNewButton.setBounds(210, 420, 153, 23);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("View rented bike info");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OrderPaymentController control = new OrderPaymentController(new RegularBike(),controller.getCustomer());
				if (control.getRentingOrderByCustomerId() != null) {
					setVisible(false);
					JFrame rentedBikeFrame = new UserRentedBike(control);
					rentedBikeFrame.setSize(WINDOW_WIDTH, 700);
					rentedBikeFrame.setVisible(true);
				} else
					JOptionPane.showMessageDialog(null, "Customer not rented bike yet");

			}
		});
		btnNewButton_1.setBounds(210, 450, 153, 23);
		getContentPane().add(btnNewButton_1);
		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EBRUser(new EBRUserController(new Customer("06a9ea28-2997-401d-b7a5-491b3f14f768")));
			}
		});
	}
}