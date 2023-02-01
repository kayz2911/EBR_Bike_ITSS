package com.ebr.serverapi.customer;

import java.util.Map;

import javax.ws.rs.Consumes;

import javax.ws.rs.GET;

import javax.ws.rs.POST;

import javax.ws.rs.Path;

import javax.ws.rs.Produces;

import javax.ws.rs.QueryParam;

import javax.ws.rs.client.Client;

import javax.ws.rs.client.ClientBuilder;

import javax.ws.rs.client.Entity;

import javax.ws.rs.client.Invocation;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;

import com.ebr.bean.customer.Card;

import com.ebr.bean.customer.Customer;

public class CardAPI {

	public static final String API_URL = "http://localhost:8080/";

	private Client client;

	public CardAPI() {

		client = ClientBuilder.newClient();

	}

	public Card getById(Map<String, String> queryParams) {

		WebTarget webTarget = client.target(API_URL).path("cards/get-card-by-id");

		if (queryParams != null) {

			for (String key : queryParams.keySet()) {

				String value = queryParams.get(key);

				webTarget = webTarget.queryParam(key, value);

			}

		}

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.get();

		Card res = response.readEntity(Card.class);

		return res;

	}

	public Card getByCustomerId(String customerId) {
		WebTarget webTarget = client.target(API_URL).path("cards/get-card-by-customer-id");

		webTarget = webTarget.queryParam("customerId", customerId);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		Card res = response.readEntity(new GenericType<Card>() {
		});

		return res;
	}

	public Card add(Card card) {

		WebTarget webTarget = client.target(API_URL).path("cards/add");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.post(Entity.entity(card, MediaType.APPLICATION_JSON));

		Card res = response.readEntity(Card.class);

		return res;

	}

	public Card update(Card card) {

		WebTarget webTarget = client.target(API_URL).path("cards/update");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.post(Entity.entity(card, MediaType.APPLICATION_JSON));

		Card res = response.readEntity(Card.class);

		return res;

	}

}