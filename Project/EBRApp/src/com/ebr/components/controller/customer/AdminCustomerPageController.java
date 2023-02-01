package com.ebr.components.controller.customer;

import java.util.List;
import java.util.Map;

import com.ebr.bean.customer.Customer;
import com.ebr.components.controller.abstractdata.ADataPageController;
import com.ebr.components.gui.abstractdata.ADataListPane;
import com.ebr.components.gui.abstractdata.ADataSearchPane;
import com.ebr.components.gui.abstractdata.ADataSinglePane;
import com.ebr.components.gui.customer.AdminCustomerListPane;
import com.ebr.components.gui.customer.CustomerSearchPane;
import com.ebr.components.gui.customer.CustomerSinglePane;
import com.ebr.serverapi.customer.CustomerAPI;

public class AdminCustomerPageController extends ADataPageController<Customer>{

	@Override
	public ADataSearchPane createSearchPane() {
		// TODO Auto-generated method stub
		return new CustomerSearchPane();
	}

	@Override
	public List<? extends Customer> search(Map<String, String> searchParams) {
		// TODO Auto-generated method stub
		return new CustomerAPI().getCustomers(searchParams);
	}

	@Override
	public ADataSinglePane<Customer> createSinglePane() {
		// TODO Auto-generated method stub
		return new CustomerSinglePane();
	}

	@Override
	public ADataListPane<Customer> createListPane() {
		// TODO Auto-generated method stub
		return new AdminCustomerListPane(this);
	}

	public Customer addCustomer(Customer customer) {
		return new CustomerAPI().add(customer);
	}

	public Customer updateCustomer(Customer customer) {
		return new CustomerAPI().update(customer);
	}

}
