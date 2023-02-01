package com.ebr.components.gui.bikestation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.ebr.bean.bikes.Bike;
import com.ebr.bean.bikestation.BikeStation;
import com.ebr.bean.customer.Customer;
import com.ebr.components.controller.abstractdata.ADataPageController;
import com.ebr.components.controller.bike.UserBikePageController;
import com.ebr.components.controller.bikestation.UserBikeStationPageController;
import com.ebr.components.controller.payment.OrderPaymentController;
import com.ebr.components.gui.abstractdata.ADataListPane;
import com.ebr.components.gui.abstractdata.ADataSinglePane;

@SuppressWarnings("serial")
public class UserBikeStationListPane extends ADataListPane<BikeStation> {
	
	public UserBikeStationListPane(ADataPageController<BikeStation> controller) {
		this.controller = controller;
	}
	

	@Override
	public void decorateSinglePane(ADataSinglePane<BikeStation> singlePane) {
		
		JButton button = new JButton("View All Bike");
		singlePane.addDataHandlingComponent(button);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controller instanceof UserBikeStationPageController) {
					JFrame f1 = (JFrame) SwingUtilities.windowForComponent(singlePane);
					f1.setVisible(false);
					
					JFrame frame = new JFrame();
					frame.setSize(800,550);
					
					ADataPageController<Bike> control =  new UserBikePageController(singlePane.getData().getId(), ((UserBikeStationPageController) controller).getCustomer());
					
					JPanel panel = control.getDataPagePane();
					JButton back = new JButton("<");
					back.setBounds(100, 100, 0, 0);
					panel.add(back);
					
					frame.setTitle(singlePane.getData().getName());
					frame.setContentPane(panel);
					frame.setVisible(true);	
					
					back.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							f1.setVisible(true);
							frame.setVisible(false);
						}
					});
				}
			}
		});
	}
}
