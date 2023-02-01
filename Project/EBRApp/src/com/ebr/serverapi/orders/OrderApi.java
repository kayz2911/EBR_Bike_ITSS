package com.ebr.serverapi.orders;

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

import com.ebr.bean.bikes.RegularBike;
import com.ebr.bean.order.Order;

public class OrderApi {
	public static final String API_URL = "http://localhost:8080/";

	private Client client;

	public OrderApi() {
		client = ClientBuilder.newClient();
	}

	public ArrayList<Order> getOrders(Map<String, String> queryParams) {
		WebTarget webTarget = client.target(API_URL).path("orders");

		if (queryParams != null) {
			for (String key : queryParams.keySet()) {
				String value = queryParams.get(key);
				webTarget = webTarget.queryParam(key, value);
			}
		}

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		ArrayList<Order> res = response.readEntity(new GenericType<ArrayList<Order>>() {
		});

		return res;
	}

	public Order getById(String OrderId) {
		WebTarget webTarget = client.target(API_URL).path("orders/get-order-by-id");

		webTarget = webTarget.queryParam("id", OrderId);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		Order res = response.readEntity(new GenericType<Order>() {
		});

		return res;
	}

	public ArrayList<Order> getByCustomerId(String CustomerId) {
		WebTarget webTarget = client.target(API_URL).path("orders/get-orders-by-customer-id");

		webTarget = webTarget.queryParam("customerId", CustomerId);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		ArrayList<Order> res = response.readEntity(new GenericType<ArrayList<Order>>() {
		});

		return res;
	}

	public Order getRentingOrderByCustomerId(String CustomerId) {
		WebTarget webTarget = client.target(API_URL).path("orders/get-renting-order-by-customer-id");

		webTarget = webTarget.queryParam("customerId", CustomerId);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		Order res = response.readEntity(new GenericType<Order>() {
		});

		return res;
	}

	public Order getByBikeId(String BikeId) {
		WebTarget webTarget = client.target(API_URL).path("orders/get-order-by-bike-id");

		webTarget = webTarget.queryParam("bikeId", BikeId);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		Order res = response.readEntity(new GenericType<Order>() {
		});

		return res;
	}

	public Order addOrder(Order order) {
		WebTarget webTarget = client.target(API_URL).path("orders/add");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(order, MediaType.APPLICATION_JSON));

		Order res = response.readEntity(Order.class);

		return res;
	}

	public Order updateOrder(Order order, String bikeStationId) {
		WebTarget webTarget = client.target(API_URL).path("orders/update/" + bikeStationId);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(order, MediaType.APPLICATION_JSON));

		Order res = response.readEntity(Order.class);

		return res;
	}
}
