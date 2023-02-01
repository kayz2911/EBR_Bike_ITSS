package com.ebr.db;

import java.util.ArrayList;

// beans
import com.ebr.bean.customer.Customer;

// db interfaces
import com.ebr.db.interfaces.ICustomerDatabase;

// db seed
import com.ebr.db.seed.Seed;

// utils
import com.ebr.utils.Utils;

public class JsonCustomerDatabase implements ICustomerDatabase {
	private static ICustomerDatabase singleton = new JsonCustomerDatabase();

	private ArrayList<Customer> customers = Seed.singleton().getCustomers();

	private JsonCustomerDatabase() {
	}

	public static ICustomerDatabase singleton() {
		return singleton;
	}

	@Override
	public ArrayList<Customer> search(Customer customer) {
		ArrayList<Customer> res = new ArrayList<Customer>();
		for (Customer c : customers) {
			if (c.match(customer)) {
				res.add(c);
			}
		}
		return res;
	}

	@Override
	public Customer getById(String customerId) {
		if (customerId != null) {
			for (Customer c : customers) {
				if (c.getId().equals(customerId)) {
					return c;
				}
			}
		}
		return null;
	}

	@Override
	public Customer add(Customer customer) {
		for (Customer c : customers) {
			if (c.equals(customer)) {
				return null;
			}
		}

		if (customer.getId() == null || customer.getId().equals(""))
			customer.setId(Utils.generateUUID());

		customers.add(customer);

		return customer;
	}

	@Override
	public Customer update(Customer customer) {
		for (Customer c : customers) {
			if (c.equals(customer)) {
				customers.remove(c);
				customers.add(customer);
				return customer;
			}
		}
		return null;
	}

	@Override
	public boolean checkExistById(String id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean removeById(String id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
