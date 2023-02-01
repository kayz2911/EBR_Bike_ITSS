package com.ebr.serverapi.test.bike;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

import com.ebr.bean.bikes.RegularBike;
import com.ebr.enums.BikeStatus;
import com.ebr.serverapi.bikes.RegularBikeApi;
import com.ebr.serverapi.bikes.RegularBikeApi;
import com.ebr.utils.Utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class RegularBikeApiTest {
	private RegularBikeApi api = new RegularBikeApi();

	@Test
	public void testGetAllRegularBikes() {
		ArrayList<RegularBike> list = api.getRegularBikes(null);
		assertEquals("Error in getRegularBikes API!", list.size(), 5);
	}

	@Test
	public void testGetAllRegularBikesWithParamId() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "262c91bd-ce00-4e62-aad6-dbc1bbfdcaa3");
		ArrayList<RegularBike> list = api.getRegularBikes(params);
		assertTrue("Error in testGetAllRegularBikesWithParamId API!", list.size() == 1);
	}

	@Test
	public void testGetAllRegularBikesWithParamName() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("name", "Regular bike 01");
		ArrayList<RegularBike> list = api.getRegularBikes(params);
		assertTrue("Error in testGetAllRegularBikesWithParamName API!", list.size() == 1);
	}

	@Test
	public void testGetAllRegularBikesWithParamLicensePlate() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("licensePlate", "29-B-262c91bd");
		ArrayList<RegularBike> list = api.getRegularBikes(params);
		assertTrue("Error in testGetAllRegularBikesWithParamLicensePlate API!", list.size() == 1);
	}

	@Test
	public void testGetAllRegularBikesWithParamProducer() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("producer", "Windmill factory");
		ArrayList<RegularBike> list = api.getRegularBikes(params);
		assertTrue("Error in testGetAllRegularBikesWithParamProducer API!", list.size() == 4);
	}

	@Test
	public void testGetAllRegularBikesWithParamDescription() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("description", "a good description");
		ArrayList<RegularBike> list = api.getRegularBikes(params);
		assertTrue("Error in getAllRegularBikesWithParams API!", list.size() == 0);
	}

	@Test(timeout = 1000)
	public void testResponse() {
		api.getRegularBikes(null);
	}

	@Test
	public void testAddRegularBike() {
		RegularBike regularBike = new RegularBike("Name test", (float) 3.3, "License plate test", new Date(),
				Utils.convertDoubleToBigDecimal(1000000), "Producer test", "Description test", BikeStatus.AVAILABLE);

		api.addRegularBike(regularBike);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", regularBike.getId());
		ArrayList<RegularBike> list = api.getRegularBikes(params);
		assertTrue("Error in addRegularBike API!", list.size() > 0);

		RegularBike newRegularBike = api.getRegularBikes(params).get(0);
		regularBike.setId(newRegularBike.getId());
		assertEquals("Error in updateRegularBike API!", newRegularBike, regularBike);
	}

	@Test
	public void testUpdateRegularBike() {
		ArrayList<RegularBike> list = api.getRegularBikes(null);
		assertTrue("No data", list.size() > 0);

		RegularBike regularBike = list.get(0);
		String newDescription = "New description set by test";
		regularBike.setDescription(newDescription);
		api.updateRegularBike(regularBike);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("description", newDescription);
		list = api.getRegularBikes(params);
		assertTrue("Error in updateRegularBike API!", list.size() > 0);

		RegularBike newRegularBike = api.getRegularBikes(params).get(0);
		assertEquals("Error in updateRegularBike API!", newRegularBike.getDescription(), newDescription);
	}
}