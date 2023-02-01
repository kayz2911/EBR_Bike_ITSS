package com.ebr.service.customer;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// beans
import com.ebr.bean.customer.Customer;

// db interfaces
import com.ebr.db.interfaces.ICustomerDatabase;
import com.ebr.db.interfaces.ICardDatabase;

// dbs
import com.ebr.db.JsonCustomerDatabase;
import com.ebr.db.JsonCardDatabase;

@Path("/customers")
public class CustomerService {
	private ICustomerDatabase customerDatabase;
	private ICardDatabase cardDatabase;

	public CustomerService() {
		customerDatabase = JsonCustomerDatabase.singleton();
		cardDatabase = JsonCardDatabase.singleton();
	}

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Customer> getCustomers(@QueryParam("id") String id, @QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName, @QueryParam("phoneNumber") String phoneNumber) {
		Customer customer = new Customer(id, firstName, lastName, phoneNumber);
		ArrayList<Customer> res = customerDatabase.search(customer);

		for (Customer c : res) {
			c = setCustomerAdditionalInfo(c);
		}

		return res;
	}

	@GET
	@Path("/get-customer-by-id")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getById(@QueryParam("id") String id) {
		Customer res = customerDatabase.getById(id);

		// get card info
		res = setCustomerAdditionalInfo(res);

		return res;
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer add(Customer customer) {
		return customerDatabase.add(customer);
	}

	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer update(Customer customer) {
		return customerDatabase.update(customer);
	}

	//pragma region PRIVATE METHODS
	/**
	 * set customer additional info: card
	 * @param {Customer} customer
	 * 
	*/
	private Customer setCustomerAdditionalInfo(Customer customer) {
		if (customer == null)
			return null;

		// get card info
		customer.setCard(cardDatabase.getById(customer.getCardId()));

		return customer;
	}
	//pragma endregion 
}