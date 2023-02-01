package com.ebr.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {
	public static String generateUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static <T1, T2> ArrayList<T2> filterArrayListByType(ArrayList<T1> list, Class<T2> cls) {
		ArrayList<T2> filteredList = new ArrayList<T2>();

		for (T1 item : list) {
			if (cls.isInstance(item)) {
				filteredList.add((T2) item);
			}
		}

		return filteredList;
	}

	public static BigDecimal convertDoubleToBigDecimal(double num) {
		return new BigDecimal(Double.toString(num));
	}

	public static String convertObjectToJsonString(Object obj) throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		try {
			// convert object to json string and return it
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			// catch various errors
			throw e;
		}
	}

	public static Object makeRequest(String requestUrl, String method, Object requestParams) throws IOException {
		// Create a neat value object to hold the URL
		URL url = new URL(requestUrl);

		// Open a connection(?) on the URL(?) and cast the response(??)
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// set the request method, headers etc.
		connection.setRequestMethod(method);
		connection.setRequestProperty("Accept", "application/json");
		
		if (method.equals("POST")) {
			connection.setRequestProperty("Content-Type", "application/json; utf-8");

			String jsonInputString = convertObjectToJsonString(requestParams);

			connection.setDoOutput(true);
			try (OutputStream os = connection.getOutputStream()) {
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input, 0, input.length);
			}
		}

		// This line makes the request
		InputStream responseStream = connection.getInputStream();

		// Manually converting the response body InputStream to APOD using Jackson
		ObjectMapper mapper = new ObjectMapper();

		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.setSerializationInclusion(Include.NON_EMPTY);

		Object objResponse = mapper.readValue(responseStream, Object.class);

		return objResponse;
	}
}
