package com.ebr.serverapi.subdivision;

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
import com.ebr.bean.subdivisions.Subdivision;

public class SubdivisionApi {
	public static final String API_URL = "http://localhost:8080/";

	private Client client;

	public SubdivisionApi() {
		client = ClientBuilder.newClient();
	}

	public ArrayList<Subdivision> getProvinces(Map<String, String> queryParams) {
		WebTarget webTarget = client.target(API_URL).path("provinces");

		if (queryParams != null) {
			for (String key : queryParams.keySet()) {
				String value = queryParams.get(key);
				webTarget = webTarget.queryParam(key, value);
			}
		}
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		ArrayList<Subdivision> res = response.readEntity(new GenericType<ArrayList<Subdivision>>() {
		});

		return res;
	}

	public ArrayList<Subdivision> getDistricts(Map<String, String> queryParams) {
		WebTarget webTarget = client.target(API_URL).path("districts");

		if (queryParams != null) {
			for (String key : queryParams.keySet()) {
				String value = queryParams.get(key);
				webTarget = webTarget.queryParam(key, value);
			}
		}
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		ArrayList<Subdivision> res = response.readEntity(new GenericType<ArrayList<Subdivision>>() {
		});

		return res;
	}

	public ArrayList<Subdivision> getWards(Map<String, String> queryParams) {
		WebTarget webTarget = client.target(API_URL).path("wards");

		if (queryParams != null) {
			for (String key : queryParams.keySet()) {
				String value = queryParams.get(key);
				webTarget = webTarget.queryParam(key, value);
			}
		}
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();

		ArrayList<Subdivision> res = response.readEntity(new GenericType<ArrayList<Subdivision>>() {
		});

		return res;
	}
}
