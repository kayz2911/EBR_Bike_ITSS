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

// beans
import com.ebr.bean.bikestation.BikeStation;

public class BikeStationApi {
	public static final String API_URL = "http://localhost:8080/";

	private Client client;

	public BikeStationApi() {
		client = ClientBuilder.newClient();
	}

	public ArrayList<BikeStation> getBikeStations(Map<String, String> queryParams) {
		WebTarget webTarget = client.target(API_URL).path("bikestations");

		if (queryParams != null) {
			for (String key : queryParams.keySet()) {
				String value = queryParams.get(key);
				webTarget = webTarget.queryParam(key, value);
			}
		}
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		ArrayList<BikeStation> res = response.readEntity(new GenericType<ArrayList<BikeStation>>() {
		});

		return res;
	}

	public BikeStation getById(String bikeStationId) {
		WebTarget webTarget = client.target(API_URL).path("bikestations/get-bike-station-by-id");

		webTarget = webTarget.queryParam("id", bikeStationId);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		BikeStation res = response.readEntity(new GenericType<BikeStation>() {
		});

		return res;
	}

	public BikeStation addBikeStation(BikeStation bikeStation) {
		WebTarget webTarget = client.target(API_URL).path("bikestations/add");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(bikeStation, MediaType.APPLICATION_JSON));

		BikeStation res = response.readEntity(BikeStation.class);

		return res;
	}

	public BikeStation updateBikeStation(BikeStation bikeStation) {
		WebTarget webTarget = client.target(API_URL).path("bikestations/update");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(bikeStation, MediaType.APPLICATION_JSON));

		BikeStation res = response.readEntity(BikeStation.class);

		return res;
	}
}
