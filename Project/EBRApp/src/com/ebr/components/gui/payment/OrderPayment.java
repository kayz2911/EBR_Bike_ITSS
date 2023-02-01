package com.ebr.components.gui.payment;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;

import com.ebr.bean.customer.Card;
import com.ebr.components.controller.bike.UserBikePageController;
import com.ebr.components.controller.payment.OrderPaymentController;
import com.ebr.components.gui.notification.SuccessNotification;
import com.ebr.constants.Constants;
import com.ebr.serverapi.customer.CardAPI;

@SuppressWarnings("serial")
public class OrderPayment extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private OrderPaymentController controller;

	public OrderPayment(OrderPaymentController controller) {
		this.controller = controller;

		Card cardInfo = controller.getCardInfo();

		setSize(new Dimension(600, 600));
		setPreferredSize(new Dimension(600, 600));
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("EcoBikeRental");
		lblNewLabel.setFont(new Font("Untitled", Font.PLAIN, 20));
		lblNewLabel.setBounds(208, 18, 173, 25);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Order payment");
		lblNewLabel_1.setBounds(231, 54, 73, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Bike: " + controller.getBike().getName());
		lblNewLabel_2.setBounds(82, 102, 146, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Desposit amount: " + Constants.SystemSettings.CURRENCY_PREFIX
				+ String.format("%,.0f", controller.getBike().getRentalDepositPrice())
				+ Constants.SystemSettings.CURRENCY_SUFFIX);
		lblNewLabel_3.setBounds(82, 137, 146, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Payment menthod : ");
		lblNewLabel_4.setBounds(82, 182, 107, 14);
		getContentPane().add(lblNewLabel_4);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Credit card");
		rdbtnNewRadioButton.setBounds(227, 178, 109, 23);
		rdbtnNewRadioButton.setSelected(true);
		getContentPane().add(rdbtnNewRadioButton);

		JLabel lblNewLabel_5 = new JLabel("Credit card number :");
		lblNewLabel_5.setBounds(82, 223, 107, 14);
		getContentPane().add(lblNewLabel_5);

		textField = new JTextField();
		textField.setBounds(82, 251, 417, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(cardInfo.getCardNumber());

		JLabel lblNewLabel_6 = new JLabel("Card holder :");
		lblNewLabel_6.setBounds(82, 295, 89, 14);
		getContentPane().add(lblNewLabel_6);

		textField_1 = new JTextField();
		textField_1.setBounds(82, 320, 417, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(cardInfo.getCardholderName());

		JLabel lblNewLabel_7 = new JLabel("Expiration date :");
		lblNewLabel_7.setBounds(82, 358, 125, 14);
		getContentPane().add(lblNewLabel_7);

		textField_2 = new JTextField();
		textField_2.setBounds(85, 383, 238, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		if (cardInfo.getExpirationDate() != null) {
			String pattern = "dd/MM/yyyy HH:mm:ss";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

			String dateStr = simpleDateFormat.format(cardInfo.getExpirationDate());
			textField_2.setText(dateStr);
		}

		JLabel lblNewLabel_8 = new JLabel("CVV :");
		lblNewLabel_8.setBounds(343, 358, 46, 14);
		getContentPane().add(lblNewLabel_8);

		textField_3 = new JTextField();
		textField_3.setBounds(343, 383, 156, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		textField_3.setEditable(false);
		textField_3.setText("****");

		JLabel lblNewLabel_9 = new JLabel("Issuing bank :");
		lblNewLabel_9.setBounds(82, 420, 89, 14);
		getContentPane().add(lblNewLabel_9);

		textField_4 = new JTextField();
		textField_4.setBounds(82, 440, 417, 20);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		textField_4.setText(cardInfo.getIssuingBank());

		JButton btnNewButton = new JButton("Pay now");
		btnNewButton.setBounds(247, 474, 89, 23);
		getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_4.getText().isEmpty()) 
					JOptionPane.showMessageDialog(null, "Form has 1 field invalid");
				else {
					Card card = new Card();
					card.setCardNumber(textField.getText());
					card.setCardholderName(textField_1.getText());
					card.setIssuingBank(textField_4.getText());
					try {
						if (textField_2.getText() != null && !textField_2.getText().equals(""))
							card.setExpirationDate(
									new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(textField_2.getText()));
						new CardAPI().update(card);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					boolean result = controller.addOrder(card);
	
					if (result == true) {
						setVisible(false);
						SuccessNotification successPane = new SuccessNotification(controller, true);
						successPane.setTitle("Rented succeed");
						successPane.setVisible(true);
					}
				}
			}
		});
	}

	public void setController(OrderPaymentController controller) {
		this.controller = controller;
	}

}