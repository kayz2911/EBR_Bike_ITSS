package com.ebr.components.gui.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.ebr.bean.customer.Customer;
import com.ebr.components.controller.abstractdata.ADataPageController;
import com.ebr.components.controller.abstractdata.IDataManageController;
import com.ebr.components.controller.customer.*;
import com.ebr.components.gui.abstractdata.ADataListPane;
import com.ebr.components.gui.abstractdata.ADataSinglePane;
@SuppressWarnings("serial")
public class AdminCustomerListPane extends ADataListPane<Customer>{
	public AdminCustomerListPane(ADataPageController<Customer> controller) {
		this.controller = controller;
	}
	@Override
	public void decorateSinglePane(ADataSinglePane<Customer> singlePane) {
		// TODO Auto-generated method stub
		JButton editButton = new JButton("Edit");
		singlePane.addDataHandlingComponent(editButton);

		IDataManageController<Customer> manageController = new IDataManageController<Customer>() {

			@Override
			public void create(Customer t) {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException("Not supported yet.");
			}

			@Override
			public void read(Customer t) {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException("Not supported yet.");
			}

			@Override
			public void delete(Customer t) {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException("Not supported yet.");
			}

			@Override
			public void update(Customer t) {
				// TODO Auto-generated method stub
				if (controller instanceof AdminCustomerPageController) {
					Customer newCustomer = ((AdminCustomerPageController) controller).updateCustomer(t);
					singlePane.updateData(newCustomer);
				}
			}
		};

		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CustomerEditDialog(singlePane.getData(), manageController);
			}
		});
	}

}
