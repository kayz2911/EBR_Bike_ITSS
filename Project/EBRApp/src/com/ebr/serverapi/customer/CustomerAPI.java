package com.ebr.serverapi.customer;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ebr.bean.bikestation.BikeStation;
import com.ebr.bean.customer.Customer;

public class CustomerAPI {
	public static final String API_URL = "http://localhost:8080/";

	private Client client;

	public CustomerAPI() {
		client = ClientBuilder.newClient();
	}
	
	public ArrayList<Customer> getCustomers(Map<String, String> queryParams){
		WebTarget webTarget = client.target(API_URL).path("customers");

		if (queryParams != null) {
			for (String key : queryParams.keySet()) {
				String value = queryParams.get(key);
				webTarget = webTarget.queryParam(key, value);
			}
		}

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		ArrayList<Customer> res = response.readEntity(new GenericType<ArrayList<Customer>>() {});

		return res;
	}

	public Customer getById(String customerId) {
		WebTarget webTarget = client.target(API_URL).path("customers/get-customer-by-id");

		webTarget = webTarget.queryParam("id", customerId);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		Customer res = response.readEntity(new GenericType<Customer>() {
		});

		return res;
	}

	public Customer add(Customer customer) {
		WebTarget webTarget = client.target(API_URL).path("customers/add");
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(customer, MediaType.APPLICATION_JSON));

		Customer res = response.readEntity(Customer.class);

		return res;
	}

	public Customer update(Customer customer) {
		WebTarget webTarget = client.target(API_URL).path("customers/update");
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(customer, MediaType.APPLICATION_JSON));

		Customer res = response.readEntity(Customer.class);

		return res;
	}
}
