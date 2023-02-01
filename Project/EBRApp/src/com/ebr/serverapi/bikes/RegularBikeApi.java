package com.ebr.serverapi.bikes;

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

// beans
import com.ebr.bean.bikes.RegularBike;

public class RegularBikeApi {
	public static final String API_URL = "http://localhost:8080/";

	private Client client;

	public RegularBikeApi() {
		client = ClientBuilder.newClient();
	}

	public ArrayList<RegularBike> getRegularBikes(Map<String, String> queryParams) {
		WebTarget webTarget = client.target(API_URL).path("regularbikes");

		if (queryParams != null) {
			for (String key : queryParams.keySet()) {
				String value = queryParams.get(key);
				webTarget = webTarget.queryParam(key, value);
			}
		}

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		System.out.println(response);
		try {
			ArrayList<RegularBike> res = response.readEntity(new GenericType<ArrayList<RegularBike>>() {
			});
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public RegularBike addRegularBike(RegularBike regularBike) {
		WebTarget webTarget = client.target(API_URL).path("regularbikes/add");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(regularBike, MediaType.APPLICATION_JSON));

		RegularBike res = response.readEntity(RegularBike.class);

		return res;
	}

	public RegularBike updateRegularBike(RegularBike regularBike) {
		WebTarget webTarget = client.target(API_URL).path("regularbikes/update");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(regularBike, MediaType.APPLICATION_JSON));

		RegularBike res = response.readEntity(RegularBike.class);

		return res;
	}
}
