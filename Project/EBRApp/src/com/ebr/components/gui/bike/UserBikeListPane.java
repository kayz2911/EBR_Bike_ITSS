package com.ebr.components.gui.bike;

import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.customer.Customer;
import com.ebr.components.controller.abstractdata.ADataPageController;
import com.ebr.components.controller.bike.UserBikePageController;
import com.ebr.components.controller.bikestation.UserBikeStationPageController;
import com.ebr.components.controller.payment.OrderPaymentController;
import com.ebr.components.gui.abstractdata.ADataListPane;
import com.ebr.components.gui.abstractdata.ADataSinglePane;
import com.ebr.components.gui.payment.OrderPayment;

@SuppressWarnings("serial")
public class UserBikeListPane extends ADataListPane<Bike>{
	public UserBikeListPane(ADataPageController<Bike> controller) {
		this.controller = controller;
	}
	

	@Override
	public void decorateSinglePane(ADataSinglePane<Bike> singlePane) {
		
		JButton button = new JButton("Rent Now");
		singlePane.addDataHandlingComponent(button);
		
		singlePane.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (controller instanceof UserBikePageController) {
					if(singlePane.getData().getStatus().getStatusStr() == "Available")
						button.setVisible(true);
					else button.setVisible(false);
				}
			}
			
		});
		
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controller instanceof UserBikePageController) {
					if(((UserBikePageController) controller).getRentingOrderByCustomerId()) JOptionPane.showMessageDialog(null, "User rented bike");
					else {
						JFrame f1 = (JFrame) SwingUtilities.windowForComponent(singlePane);
						f1.setVisible(false);
						OrderPayment orderPayment =  new OrderPayment(new OrderPaymentController(singlePane.getData(),((UserBikePageController) controller).getCustomer()));
						orderPayment.setTitle("Rent Bike: " + singlePane.getData().getName());
						orderPayment.setVisible(true);
					}
							
				}
			}
		});

	}
}
