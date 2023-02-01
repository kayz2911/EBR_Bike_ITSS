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
import com.ebr.bean.bikes.EBike;

public class EBikeApi {
	public static final String API_URL = "http://localhost:8080/";

	private Client client;

	public EBikeApi() {
		client = ClientBuilder.newClient();
	}

	public ArrayList<EBike> getEBikes(Map<String, String> queryParams) {
		WebTarget webTarget = client.target(API_URL).path("ebikes");

		if (queryParams != null) {
			for (String key : queryParams.keySet()) {
				String value = queryParams.get(key);
				webTarget = webTarget.queryParam(key, value);
			}
		}

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		ArrayList<EBike> res = response.readEntity(new GenericType<ArrayList<EBike>>() {
		});

		return res;
	}

	public EBike addEBike(EBike ebike) {
		WebTarget webTarget = client.target(API_URL).path("ebikes/add");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(ebike, MediaType.APPLICATION_JSON));

		EBike res = response.readEntity(EBike.class);

		return res;
	}

	public EBike updateEBike(EBike ebike) {
		WebTarget webTarget = client.target(API_URL).path("ebikes/update");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(ebike, MediaType.APPLICATION_JSON));

		EBike res = response.readEntity(EBike.class);

		return res;
	}
}
