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
import com.ebr.bean.bikes.TwinBike;

public class TwinBikeApi {
	public static final String API_URL = "http://localhost:8080/";

	private Client client;

	public TwinBikeApi() {
		client = ClientBuilder.newClient();
	}

	public ArrayList<TwinBike> getTwinBikes(Map<String, String> queryParams) {
		WebTarget webTarget = client.target(API_URL).path("twinbikes");

		if (queryParams != null) {
			for (String key : queryParams.keySet()) {
				String value = queryParams.get(key);
				webTarget = webTarget.queryParam(key, value);
			}
		}

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		ArrayList<TwinBike> res = response.readEntity(new GenericType<ArrayList<TwinBike>>() {
		});

		return res;
	}

	public TwinBike addTwinBike(TwinBike twinBike) {
		WebTarget webTarget = client.target(API_URL).path("twinbikes/add");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(twinBike, MediaType.APPLICATION_JSON));

		TwinBike res = response.readEntity(TwinBike.class);

		return res;
	}

	public TwinBike updateTwinBike(TwinBike twinBike) {
		WebTarget webTarget = client.target(API_URL).path("twinbikes/update");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(twinBike, MediaType.APPLICATION_JSON));

		TwinBike res = response.readEntity(TwinBike.class);

		return res;
	}
}
