package com.ebr.components.gui.bikedock;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.ebr.bean.bikes.Bike;
import com.ebr.components.controller.abstractdata.ADataPageController;
import com.ebr.components.controller.bike.UserBikePageController;
import com.ebr.components.gui.abstractdata.ADataListPane;
import com.ebr.components.gui.abstractdata.ADataSinglePane;

@SuppressWarnings("serial")
public class UserBikeDockListPane extends ADataListPane<Bike>{
	
	public UserBikeDockListPane(ADataPageController<Bike> controller) {
		this.controller = controller;
	}
	

	@Override
	public void decorateSinglePane(ADataSinglePane<Bike> singlePane) {
		JSpinner spin = new JSpinner();
		spin.setModel(new SpinnerNumberModel(1, 0, null, 1));
		singlePane.addDataHandlingComponent(spin);
		spin.setPreferredSize(new Dimension(100, 20));
		
		JButton button = new JButton("Add to cart");
		singlePane.addDataHandlingComponent(button);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controller instanceof UserBikePageController) {
//					((UserBikePageController) controller).addToCart(singlePane.getData().getId(), singlePane.getData().getName(), singlePane.getData().getCost(), (int)spin.getValue());
				}
			}
		});
	}
}
