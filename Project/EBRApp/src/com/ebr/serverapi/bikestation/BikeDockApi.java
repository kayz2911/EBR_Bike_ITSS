package com.ebr.serverapi.bikestation;

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

import com.ebr.bean.bikestation.BikeDock;

public class BikeDockApi {

	public static final String API_URL = "http://localhost:8080/";

	private Client client;

	public BikeDockApi() {
		client = ClientBuilder.newClient();
	}

	public ArrayList<BikeDock> getBikeDocks(Map<String, String> queryParams) {
		WebTarget webTarget = client.target(API_URL).path("bikedocks");

		if (queryParams != null) {
			for (String key : queryParams.keySet()) {
				String value = queryParams.get(key);
				webTarget = webTarget.queryParam(key, value);
			}
		}
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		ArrayList<BikeDock> res = response.readEntity(new GenericType<ArrayList<BikeDock>>() {
		});

		return res;
	}

	public BikeDock addBikeDock(BikeDock bikeDock) {
		WebTarget webTarget = client.target(API_URL).path("bikedocks/add");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(bikeDock, MediaType.APPLICATION_JSON));

		BikeDock res = response.readEntity(BikeDock.class);

		return res;
	}

	public BikeDock updateBikeDock(BikeDock bikeDock) {
		WebTarget webTarget = client.target(API_URL).path("bikedocks/update");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(bikeDock, MediaType.APPLICATION_JSON));

		BikeDock res = response.readEntity(BikeDock.class);

		return res;
	}

}
