package com.ebr.serverapi.bikes;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// beans
import com.ebr.bean.bikes.Bike;

public class BikeApi {
	public static final String API_URL = "http://localhost:8080/";

	private Client client;

	public BikeApi() {
		client = ClientBuilder.newClient();
	}

	public ArrayList<Bike> getAllBikes() {
		WebTarget webTarget = client.target(API_URL).path("bikes");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		ArrayList<Bike> res = response.readEntity(new GenericType<ArrayList<Bike>>() {
		});

		return res;
	}

	public ArrayList<Bike> getBikesByBikeStationId(String bikeStationId, Map<String,String> searchParams) {
		WebTarget webTarget = client.target(API_URL).path("bikes/get-bikes-by-bike-station-id");

		webTarget = webTarget.queryParam("bikeStationId", bikeStationId);
		if (searchParams != null) {
			for (String key : searchParams.keySet()) {
				String value = searchParams.get(key);
				webTarget = webTarget.queryParam(key, value);
			}
		}

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		ArrayList<Bike> res = response.readEntity(new GenericType<ArrayList<Bike>>() {
		});
		
		return res;
	}

	public ArrayList<Bike> getBikeById(String bikeId) {
		WebTarget webTarget = client.target(API_URL).path("bikes/get-bike-by-id");

		webTarget = webTarget.queryParam("bikeId", bikeId);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		ArrayList<Bike> res = response.readEntity(new GenericType<ArrayList<Bike>>() {
		});

		return res;
	}
}
