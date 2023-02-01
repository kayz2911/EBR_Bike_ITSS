package com.ebr.serverapi.test.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ebr.bean.Address;
import com.ebr.bean.customer.Customer;
import com.ebr.enums.BikeStatus;
import com.ebr.serverapi.customer.CustomerAPI;
import com.ebr.utils.Utils;

public class CustomerAPITest {
	private CustomerAPI api = new CustomerAPI();

	@Test
	public void testGetAllCustomers() {
		ArrayList<Customer> list = api.getCustomers(null);
		assertEquals("Error in getCustomers API!", list.size(), 6);
	}

	@Test
	public void testGetAllCustomersWithParamId() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "06a9ea28-2997-401d-b7a5-491b3f14f768");
		ArrayList<Customer> list = api.getCustomers(params);
		assertTrue("Error in testGetAllCustomersWithParamId API!", list.size() == 1);
	}

	@Test
	public void testGetAllCustomersWithParamFirstName() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("firstName", "Huy Thăng");
		ArrayList<Customer> list = api.getCustomers(params);
		assertTrue("Error in testGetAllCustomersWithParamFirstName API!", list.size() == 1);
	}

	@Test
	public void testGetAllCustomersWithParamLastName() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("lastName", "Phan");
		ArrayList<Customer> list = api.getCustomers(params);
		assertTrue("Error in testGetAllCustomersWithParamLastName API!", list.size() == 1);
	}

	@Test
	public void testGetAllCustomersWithParamPhoneNumber() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("phoneNumber", "+1 582-282-6386");
		ArrayList<Customer> list = api.getCustomers(params);
		assertTrue("Error in testGetAllCustomersWithParamPhoneNumber API!", list.size() == 1);
	}

	@Test(timeout = 1000)
	public void testResponse() {
		api.getCustomers(null);
	}

	@Test
	public void testAddCustomer() {
		Customer customer = new Customer("Trinh", "Nguyễn Trung", "+1 483-262-2710");

		api.add(customer);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("phoneNumber", "+1 483-262-2710");
		ArrayList<Customer> list = api.getCustomers(params);
		assertTrue("Error in addCustomer API!", list.size() > 0);

		Customer newCustomer = api.getCustomers(params).get(0);
		assertEquals("Error in addCustomer API!", newCustomer.getFirstName(), customer.getFirstName());
		assertEquals("Error in addCustomer API!", newCustomer.getLastName(), customer.getLastName());
		assertEquals("Error in addCustomer API!", newCustomer.getPhoneNumber(), customer.getPhoneNumber());
	}

	@Test
	public void testUpdateCustomer() {
		ArrayList<Customer> list = api.getCustomers(null);
		assertTrue("No data", list.size() > 0);

		Customer customer = list.get(0);
		String newPhoneNumber = "+1 483-262-2718";
		customer.setPhoneNumber(newPhoneNumber);
		api.update(customer);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", customer.getId());
		list = api.getCustomers(params);
		assertTrue("Error in updateCustomer API!", list.size() > 0);

		Customer newCustomer = api.getCustomers(params).get(0);
		assertEquals("Error in updateCustomer API!", newCustomer.getPhoneNumber(), newPhoneNumber);
	}
}